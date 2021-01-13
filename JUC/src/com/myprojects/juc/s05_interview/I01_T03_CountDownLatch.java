package com.myprojects.juc.s05_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 面试题：实现一个容器，提供两个方法add，size，写两个线程，线程1添加10个元素到容器中，
 * 线程2实现监控元素的个数，当个数到5个时，线程2给出提示结束
 * CountDownLatch:不太好实现，线程1只能通知线程2是否锁，但是线程1还在继续运行，那么线程2结束的时间就不固定
 */
public class I01_T03_CountDownLatch {
    volatile List<Object> list=new ArrayList<>();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }
    public static void main(String[] args) {
        I01_T03_CountDownLatch test=new I01_T03_CountDownLatch();
        CountDownLatch lock=new CountDownLatch(1);
        new Thread(()->{
            System.out.println("t2启动");
                if (test.size() != 5) {
                    try {
                        //等待count=0唤醒线程
                        lock.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            System.out.println("t2结束");
        },"t2").start();

        new Thread(()->{
            System.out.println("t1启动");
                for (int i = 1; i <= 10; i++) {
                    test.add(new Object());
                    System.out.println(i);

                    if (test.size() == 5) {
                        lock.countDown();
                        /*try {
                            lock.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    }
                }
            System.out.println("t1结束");
        },"t1").start();


    }
}
