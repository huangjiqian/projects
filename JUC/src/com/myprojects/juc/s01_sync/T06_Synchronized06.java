package com.myprojects.juc.s01_sync;

import java.util.concurrent.TimeUnit;

/**
 * synchronized属于可重入锁，一个同步方法可以执行另一个同步方法。前提是锁的是同一个对象。
 */
public class T06_Synchronized06 {
    public synchronized void m1(){
        m2();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1");
    }
    public synchronized void m2(){
        System.out.println("m2");
    }

    public static void main(String[] args) {
        T06_Synchronized06 s06=new T06_Synchronized06();
        new Thread(s06::m1).start();
    }
}
