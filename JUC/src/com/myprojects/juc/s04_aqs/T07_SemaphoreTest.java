package com.myprojects.juc.s04_aqs;

import java.util.concurrent.Semaphore;

/**
 * permits参数:相当于锁的数量，获得允许的线程相当于获得锁可以继续执行。
 * 可用于限流
 */
public class T07_SemaphoreTest {
    static Semaphore semaphore=new Semaphore(10);

    public void m(){
        try {
            //线程申请许可，获得后permits--
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+"--执行中...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放线程，permits++
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        T07_SemaphoreTest test=new T07_SemaphoreTest();
        for (int i = 0; i < 100; i++) {
            new Thread(test::m).start();
        }
    }
}
