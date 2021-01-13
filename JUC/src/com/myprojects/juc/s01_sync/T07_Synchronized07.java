package com.myprojects.juc.s01_sync;

import java.util.concurrent.TimeUnit;

/**
 * m2同步方法能够在m1同步代码中执行，说明锁的是父类，但实际是子类的对象
 */
public class T07_Synchronized07 extends Parent {
    public void m1(){
        //锁的是父类对象
        synchronized (Parent.class){
            m2();
            super.parentMethod();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m1");
        }
    }
    //锁的是当前类对象
    public synchronized void m2(){
        System.out.println("m2");
    }
    public static void main(String[] args) {
        T07_Synchronized07 s07=new T07_Synchronized07();
        new Thread(s07::m1).start();
    }
}

class Parent{
    public void parentMethod(){
        System.out.println("parentMethod");
    }
}