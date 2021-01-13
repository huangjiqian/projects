package com.myprojects.juc.s01_sync;
/**
 * 一个线程正在执行同步方法，另一个线程可以执行非同步方法
 */
import java.util.concurrent.TimeUnit;

public class T01_Synchronized01 {
    public synchronized void m1(){
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1");
    }

    public void m2(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }
    public static void main(String[] args) {
        T01_Synchronized01 s01=new T01_Synchronized01();
        new Thread(s01::m1).start();
        new Thread(s01::m2).start();
    }
}
