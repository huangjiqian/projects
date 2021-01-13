package com.myprojects.juc.s01_sync;

import java.util.concurrent.TimeUnit;

/**
 * synchronized属于互斥锁，若锁的不是一个对象，那么不能产生互斥。
 */
public class T03_Synchronized03 {

    public void m1(){
        synchronized (T01_Synchronized01.class){
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m1");
        }
    }

    public void m2(){
        synchronized (T03_Synchronized03.class){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m2");
        }
    }

    public static void main(String[] args) {
        T03_Synchronized03 s03=new T03_Synchronized03();
        new Thread(s03::m1).start();
        new Thread(s03::m2).start();
    }
}
