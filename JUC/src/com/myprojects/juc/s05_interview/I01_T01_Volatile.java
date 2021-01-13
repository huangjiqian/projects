package com.myprojects.juc.s05_interview;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题：实现一个容器，提供两个方法add，size，写两个线程，线程1添加10个元素到容器中，
 * 线程2实现监控元素的个数，当个数到5个时，线程2给出提示结束
 *
 * volatile只能保证修饰的变量可见性，不能保证变量内部属性的可见性
 */
public class I01_T01_Volatile {
    volatile List<Object> list=new ArrayList<>();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }
    public static void main(String[] args) {
        I01_T01_Volatile test=new I01_T01_Volatile();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                test.add(new Object());
                System.out.println(i);
                //sleep过程把CPU资源让出，那么T2线程肯定可以拿到list对象
                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        },"t1").start();

        new Thread(()->{
            while (true) {
                if (test.size() == 5) break;
            }
            System.out.println("t2结束");
        },"t2").start();
    }
}
