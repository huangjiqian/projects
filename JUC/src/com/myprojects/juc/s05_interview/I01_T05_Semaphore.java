package com.myprojects.juc.s05_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

/**
 * 面试题：实现一个容器，提供两个方法add，size，写两个线程，线程1添加10个元素到容器中，
 * 线程2实现监控元素的个数，当个数到5个时，线程2给出提示结束
 * Semaphore:permits=1，线程1先添加前5个，启动线程2，然后在添加后5个
 */
public class I01_T05_Semaphore {
    volatile List<Object> list=new ArrayList<>();
    static Thread t1=null,t2=null;

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }
    public static void main(String[] args) {
        I01_T05_Semaphore test=new I01_T05_Semaphore();
        Semaphore lock=new Semaphore(1);
        t2=new Thread(()->{
            System.out.println("t2启动");
                if (test.size() == 5) {
                    try {
                        lock.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.release();
            System.out.println("t2结束");
        },"t2");

        t1=new Thread(()->{
            System.out.println("t1启动");
            try {
                lock.acquire();
                for (int i = 1; i <= 5; i++) {

                    test.add(new Object());
                    System.out.println(i);
                }
                lock.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //启动线程2
            try {
                t2.start();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                lock.acquire();
                for (int i = 6; i <= 10; i++) {

                    test.add(new Object());
                    System.out.println(i);
                }
                lock.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1结束");
        },"t1");
        //t2.start();
        t1.start();

    }
}
