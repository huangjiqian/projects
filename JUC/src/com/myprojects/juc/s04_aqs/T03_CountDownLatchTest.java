package com.myprojects.juc.s04_aqs;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch:是一个计数器，传入参数count，运行一个线程count--，线程处于休眠状态，当count=0时，释放锁。
 * 可以模拟并发请求的真实性
 * 如果线程数未达到count数量，线程阻塞
 */
public class T03_CountDownLatchTest {
    private static CountDownLatch cdl=new CountDownLatch(20);
    private static T03_CountDownLatchTest test=new T03_CountDownLatchTest();

    public static void usingCountDownLatch(){
        Thread[] threads=new Thread[20];

        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"执行");
                cdl.countDown();
            });
        }
        for(int i=0;i<threads.length;i++) threads[i].start();
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end latch");
    }

    public static void usingJoin(){
        Thread[] threads=new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"执行");
            });
        }
        for(int i=0;i<threads.length;i++) threads[i].start();
        try {
            for(int i=0;i<threads.length;i++) threads[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end join");
    }
    public static void main(String[] args) {
        usingCountDownLatch();
        usingJoin();
    }
}
