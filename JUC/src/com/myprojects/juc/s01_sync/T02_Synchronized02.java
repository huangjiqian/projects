package com.myprojects.juc.s01_sync;
/**
 * 一个线程正在执行同步方法，另一个线程不可以执行同步方法。因为它锁的是当前对象，不是代码块。synchronized属于互斥锁
 */
import java.util.concurrent.TimeUnit;

public class T02_Synchronized02 {
    public synchronized void m1(){
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1");
    }

    public synchronized void m2(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }
    public static void main(String[] args) {
        T02_Synchronized02 s01=new T02_Synchronized02();
        new Thread(s01::m1).start();
        new Thread(s01::m2).start();
    }
}
