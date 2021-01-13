package com.myprojects.juc.s05_interview;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题：实现一个容器，提供两个方法add，size，写两个线程，线程1添加10个元素到容器中，
 * 线程2实现监控元素的个数，当个数到5个时，线程2给出提示结束
 *
 * notify/wait:给两个线程执行的方法加锁，线程2在第5个数时调用wait方法进入持锁休眠，等待线程1添加元素个数达到5个时调用notify方法唤醒线程1
 * 然后线程1调用wait方法持锁休眠，线程2运行完调用notify方法唤醒线程1
 */
public class I01_T02_WaitNotify {
    volatile List<Object> list=new ArrayList<>();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }
    public static void main(String[] args) {
        I01_T02_WaitNotify test=new I01_T02_WaitNotify();
        final Object lock=new Object();
        new Thread(()->{
            System.out.println("t2启动");
            synchronized (lock) {
                if (test.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
            System.out.println("t2结束");
        },"t2").start();

        new Thread(()->{
            System.out.println("t1启动");
            synchronized (lock) {
                for (int i = 1; i <= 10; i++) {
                    test.add(new Object());
                    System.out.println(i);

                    if (test.size() == 5) {
                        //唤醒线程1
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            System.out.println("t1结束");
        },"t1").start();


    }
}
