package com.myprojects.juc.s01_sync;

import java.util.concurrent.TimeUnit;

/**
 * synchronized既能保证原子性，又能保证可见性。若变量修改时已经用synchronized修饰，就不用volatile修饰变量了
 */
public class T04_Synchronized04 implements Runnable {
    private /*volatile*/ static int count=0;
    public synchronized void run(){

        try {
            TimeUnit.SECONDS.sleep(1);
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "count=" + count);
    }

    public static void main(String[] args) {
        T04_Synchronized04 s04=new T04_Synchronized04();
        for (int i = 0; i < 100; i++) {
            new Thread(s04,"thread"+i).start();
        }
    }
}
