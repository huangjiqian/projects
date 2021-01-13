package com.myprojects.juc.s04_aqs;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * WriteLock:排他锁，只允许一个线程执行
 * ReadLock:共享锁，多个线程可以使用
 */
public class T06_ReadWriteLockTest {
    static Lock lock=new ReentrantLock();
    static int value=0;

    static ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    static Lock readLock=readWriteLock.readLock();
    static Lock writeLock=readWriteLock.writeLock();

    public static void read(Lock lock){
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("read...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock,int val){
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            value=val;
            System.out.println("write...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        //Runnable readR=()->read(lock);
        Runnable readR=()->read(readLock);

        //Runnable writeR=()->write(lock,new Random().nextInt());
        Runnable writeR=()->write(writeLock,new Random().nextInt());

        for (int i = 0; i < 18; i++) new Thread(readR).start();
        for (int i = 0; i < 2; i++) new Thread(writeR).start();
    }
}
