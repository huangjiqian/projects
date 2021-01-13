package com.myprojects.juc.s04_aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock与Synchronized一样，都是可重入锁，互斥锁
 * fair:true表示公平锁，false表示非公平锁。
 * 公平锁：会让每个线程都有机会拿到锁。原理是用线程队列实现，FIFO
 * 属于CAS
 * lock必须用finally释放锁。
 */
public class T01_ReentrantLockTest {
    private Lock lock=new ReentrantLock(true);
    private static int count=1;
    public void m1(){
       try {
           lock.lock();
           for (int i = 0; i < 10; i++) {
               TimeUnit.MILLISECONDS.sleep(10);
               System.out.println(Thread.currentThread().getName()+"--m1--"+count++);
           }
       } catch (InterruptedException e) {
           e.printStackTrace();
       } finally {
            lock.unlock();
       }
    }

    /**
     * tryLock:
     * 当获取锁时，锁资源在超时时间之内变为可用，并且在等待时没有被中断，那么当前线程成功获取锁，返回true，同时当前线程持有锁的count设置为1.
     * 当获取锁时，在超时时间之内没有锁资源可用，那么当前线程获取失败，不再继续等待，返回false.
     * 当获取锁时，在超时等待时间之内，被中断了，那么抛出InterruptedException，不再继续等待.
     * 当获取锁时，在超时时间之内锁可用，并且当前线程之前已持有该锁，那么成功获取锁，同时持有count加1.
     */
    public void m2(){
        boolean locked=false;
        try {
            locked=lock.tryLock(2,TimeUnit.MILLISECONDS);
            System.out.println("m2---"+locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(locked) lock.unlock();
        }
    }
    public static void main(String[] args) {
        T01_ReentrantLockTest rt=new T01_ReentrantLockTest();
        /*Thread[] threads=new Thread[100];
        for (int i = 0; i < threads.length; i++) threads[i]=new Thread(rt::m1);
        for(Thread t:threads) t.start();
        for(Thread t:threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        new Thread(rt::m1).start();
        new Thread(rt::m2).start();
    }
}
