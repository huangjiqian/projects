package com.myprojects.juc.s08_Container.Collection.Set;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 类似ReadWriteLock
 * 写时复制容器 copy on write
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境
 */
public class T01_CopyOnWriteArraySet {
    static CopyOnWriteArraySet<String> set=new CopyOnWriteArraySet<>();

    public static void main(String[] args) {
        Thread[] threads=new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    set.add("编号:"+j);
                }
            });
        }
        //计算写的时间:4411
        runAndCalcTime(threads);

        //-------------------------------

        threads=new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(()->{
                for (int j = 0; j < 100000; j++) {
                    set.iterator();
                }
            });
        }
        //计算读的时间:285
        runAndCalcTime(threads);
    }

    public static void runAndCalcTime(Thread[] threads){
        long start=System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) threads[i].start();
        try {
            for (int i = 0; i < threads.length; i++) threads[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
