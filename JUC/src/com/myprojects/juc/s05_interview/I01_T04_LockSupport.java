package com.myprojects.juc.s05_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * 面试题：实现一个容器，提供两个方法add，size，写两个线程，线程1添加10个元素到容器中，
 * 线程2实现监控元素的个数，当个数到5个时，线程2给出提示结束
 * LockSupport:park/unpark类似wait/notify
 */
public class I01_T04_LockSupport {
    volatile List<Object> list=new ArrayList<>();
    static Thread t1=null,t2=null;

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }
    public static void main(String[] args) {
        I01_T04_LockSupport test=new I01_T04_LockSupport();
        t2=new Thread(()->{
            System.out.println("t2启动");
                if (test.size() != 5) {
                    LockSupport.park();
                }
                LockSupport.unpark(t1);
            System.out.println("t2结束");
        },"t2");

        t1=new Thread(()->{
            System.out.println("t1启动");
                for (int i = 1; i <= 10; i++) {
                    test.add(new Object());
                    System.out.println(i);

                    if (test.size() == 5) {
                        LockSupport.unpark(t2);
                        LockSupport.park();
                    }
                }
            System.out.println("t1结束");
        },"t1");
        t2.start();
        t1.start();

    }
}
