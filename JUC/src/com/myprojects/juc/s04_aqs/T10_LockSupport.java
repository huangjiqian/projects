package com.myprojects.juc.s04_aqs;

import java.util.concurrent.locks.LockSupport;

/**
 * park方法不需要获得某个对象的锁就可以锁住线程
 */
public class T10_LockSupport {
    public static void main(String[] args) {
        Thread t=new Thread(()->{
            for (int i = 0; i <10; i++) {
                if (i==5) {
                    LockSupport.park();//阻塞线程
                    System.out.println("thread is parked...");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println(i);
            }
        });
        t.start();
        LockSupport.unpark(t);//唤醒线程
    }
}
