package com.myprojects.juc.s04_aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T02_ReentrantLockTest {
    T02_ReentrantLockTest rt=new T02_ReentrantLockTest();

    public static void main(String[] args) {
        Lock lock=new ReentrantLock();

        Thread t1=new Thread(()->{
            try {
                System.out.println("t1..准备拿锁");
                lock.lock();
                TimeUnit.SECONDS.sleep(1);
                System.out.println("t1..拿到锁");
            } catch (InterruptedException e) {
                System.out.println("t1..拿锁失败");
            } finally {
                System.out.println("t1..释放锁");
                lock.unlock();
            }
        });
        t1.start();

        /**
         * 当获取锁时，锁资源可用，那么当前线程成功获得锁，同时持有count设置为1，返回true.
         * 当获取锁时，锁资源可用，当前线程已持有该锁，它成功获取该锁，同时持有count增加1，返回true.
         * 当获取锁时，锁资源不可用，那么该线程开始阻塞休眠等待，但是等待过程中如果有中断事件，那么会停止等待，立即返回.
         * 当获取锁时，锁资源不可用，线程开始阻塞休眠等待，如果等待过程中锁资源变为可用，那么当前线程成功获得锁，同时持有count设置为1，返回true.
         */
        Thread t2=new Thread(()->{
            try {
                System.out.println("t2..准备拿锁");
                //lock.lock();
                lock.lockInterruptibly();
                //TimeUnit.SECONDS.sleep(3);
                System.out.println("t2..拿到锁");
            } catch (InterruptedException e) {
                System.out.println("t2..拿锁失败");
            } finally {
                System.out.println("t2..释放锁");
                lock.unlock();
            }
        });
        t2.start();
        /*try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();//打断t2线程的等待*/
    }
}
