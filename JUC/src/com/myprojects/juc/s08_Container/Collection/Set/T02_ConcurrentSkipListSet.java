package com.myprojects.juc.s08_Container.Collection.Set;

import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 是线程安全的有序的集合
 */
public class T02_ConcurrentSkipListSet {
    static ConcurrentSkipListSet<Integer> set=new ConcurrentSkipListSet<>();

    public static void main(String[] args) {
        Thread[] threads=new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    set.add(j);
                }
            });
        }
        //计算写的时间:302
        runAndCalcTime(threads);
        Iterator<Integer> iterator=set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        //-------------------------------

        threads=new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(()->{
                for (int j = 0; j < 100000; j++) {
                    set.iterator();
                }
            });
        }
        //计算读的时间:810
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
