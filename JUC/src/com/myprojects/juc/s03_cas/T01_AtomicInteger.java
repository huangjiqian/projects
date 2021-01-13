package com.myprojects.juc.s03_cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决同样的问题的更高效的方法，是使用AtomicXXX类
 * AtomicXXX类本身方法都是原子性的，但不能保证多个方法连续调用时原子性的。
 */
public class T01_AtomicInteger {
    AtomicInteger ai=new AtomicInteger();
    public void increment(){
        /*while (true) {
            System.out.println(Thread.currentThread().getName() + "--" + ai.incrementAndGet());
        }*/
        for (int i = 0; i < 100000; i++) {
            ai.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T01_AtomicInteger a01=new T01_AtomicInteger();
        Thread[] threads=new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(a01::increment);
        }
        /*for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }*/
        for (Thread t :
                threads) {
            t.start();
        }
        for (Thread t :
                threads) {
            t.join();
        }
        System.out.println(a01.ai.get());
    }
}
