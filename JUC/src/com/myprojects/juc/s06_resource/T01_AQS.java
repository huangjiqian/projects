package com.myprojects.juc.s06_resource;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

public class T01_AQS {
    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock();
        lock.lock();
        lock.isLocked();
        lock.unlock();
    }
}
