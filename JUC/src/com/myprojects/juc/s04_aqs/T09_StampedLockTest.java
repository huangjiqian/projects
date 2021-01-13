package com.myprojects.juc.s04_aqs;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock:升级版ReadWriteLock
 * ①.对于悲观读和悲观写的方法与ReentranReadWriteLock读写锁效果一样
 * ②.对于乐观读（如果没有进入写模式）可以减少一次读锁的性能消耗，并且不会阻塞写入的操作（乐观读遇到写后转化为悲观，相当于滞后一步）
 *
 * 1、进入悲观读锁前先看下有没有进入写模式（说白了就是有没有已经获取了悲观写锁）
 * 2、如果其他线程已经获取了悲观写锁，那么就只能老老实实的获取悲观读锁（这种情况相当于退化成了读写锁）
 * 3、如果其他线程没有获取悲观写锁，那么就不用获取悲观读锁了，减少了一次获取悲观读锁的消耗和避免了因为读锁导致写锁阻塞的问题，
 * 直接返回读的数据即可（必须再tryOptimisticRead和validate之间获取好数据，否则数据可能会不一致了，试想如果过了validate再获取数据，
 * 这时数据可能被修改并且读操作也没有任何保护措施）
 */
public class T09_StampedLockTest {
    //使用StampedLock类中的读写锁
    static StampedLock lock=new StampedLock();
    static Lock readLockStamp=lock.asReadLock();
    static Lock writeLockStamp=lock.asWriteLock();

    static ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    static Lock readLock=readWriteLock.readLock();
    static Lock writeLock=readWriteLock.writeLock();

    static int value;

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
            System.out.println("write...");
            value=val;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        //Runnable readR=()->read(lock);
        //Runnable readR=()->read(readLock);
        Runnable readR=()->read(readLockStamp);

        //Runnable writeR=()->write(lock,new Random().nextInt());
        //Runnable writeR=()->write(writeLock,new Random().nextInt());
        Runnable writeR=()->write(writeLockStamp,new Random().nextInt());

        for (int i = 0; i < 18; i++) new Thread(readR).start();
        for (int i = 0; i < 2; i++) new Thread(writeR).start();
    }
}
