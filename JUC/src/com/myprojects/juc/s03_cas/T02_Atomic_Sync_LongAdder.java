package com.myprojects.juc.s03_cas;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Atomic:1000000000--18367ms
 * Sync:1000000000--1957ms //for循环外加锁，反而效率更高
 * LongAdder:1000000000--4437ms //更适合高并发的访问
 *
 * synchronized在for循环外比for循环内效率高几倍
 * for循环外：Sync:1000000000--2267ms
 * for循环内：Sync:1000000000--9522ms
 *
 */
public class T02_Atomic_Sync_LongAdder {
    static AtomicLong count1=new AtomicLong();
    static long count2=0;
    static LongAdder count3=new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        int size=100000;
        Thread[] threads=new Thread[10000];
        //-----------------------------------------------
        //Atomic
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for(int j=0;j<size;j++) count1.getAndIncrement();
            });
        }

        long start=System.currentTimeMillis();
        for(Thread t : threads) t.start();
        for(Thread t : threads) t.join();
        long end=System.currentTimeMillis();
        System.out.println("Atomic:" + count1 + "--" + (end-start) + "ms");

        //-----------------------------------------------
        //Sync
        Object lock=new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                //Sync:1000000000--9522ms
                /*for(int j=0;j<size;j++) {
                    synchronized (lock) {
                        count2++;
                    }
                }*/
                //Sync:1000000000--2267ms
                synchronized (lock){
                    for (int j = 0; j < size; j++) {
                        count2++;
                    }
                }
            });
        }

        start=System.currentTimeMillis();
        for(Thread t : threads) t.start();
        for(Thread t : threads) t.join();
        end=System.currentTimeMillis();
        System.out.println("Sync:" + count2 + "--" + (end-start) + "ms");

        //-----------------------------------------------
        //LongAdder
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for(int j=0;j<size;j++) count3.increment();
            });
        }

        start=System.currentTimeMillis();
        for(Thread t : threads) t.start();
        for(Thread t : threads) t.join();
        end=System.currentTimeMillis();
        System.out.println("LongAdder:" + count3 + "--" + (end-start) + "ms");
    }
}
