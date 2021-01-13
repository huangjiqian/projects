package com.myprojects.juc.s02_volat;

import java.util.concurrent.TimeUnit;

/**
 * 难道现在jdk版本已经默认变量实现线程可见性？--在方法执行时使用了sleep方法，这会让线程处于wait状态
 *
 * volatile保证线程的可见性，对变量修饰，会让多个线程都能看见此变量的值。
 * 但只能保证可见性，不能保证原子性。
 * 原理：使用缓存一致性协议实现
 * A B线程都用到一个变量，java默认是A线程中保留一份copy，这样如果B线程修改了该变量，则A线程未必知道
 * 使用volatile关键字，会让所有线程都会读到变量的修改值
 *
 * 在下面的代码中，running是存在于堆内存的t对象中
 * 当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，在运行过程中直接使用这个copy，并不会每次都去
 * 读取堆内存，这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行
 *
 * 使用volatile，将会强制所有线程都去堆内存中读取running的值

 */
public class T01_Volatile01 {
    volatile boolean flag=true;
    //static int count=1;
    public void m1(){
        System.out.println("start...");
        while (flag){
            //Thread.sleep会触发OS立刻重新进行一次CPU竞争，定义1S，那么当前线程在这个一秒内不会参与到CPU竞争
            /*try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            //System.out.println(Thread.currentThread().getName()+ "--" +count++);
        }
        System.out.println("end...");
    }

    public static void main(String[] args) {
        T01_Volatile01 v01=new T01_Volatile01();
        new Thread(v01::m1).start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        v01.flag=false;

    }
}
