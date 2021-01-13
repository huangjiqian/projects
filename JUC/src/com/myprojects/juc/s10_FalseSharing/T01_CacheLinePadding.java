package com.myprojects.juc.s10_FalseSharing;

import jdk.internal.vm.annotation.Contended;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * cache line为64位，arr数组两个值在同一个cache line，两个线程分别修改数组中两个数时会发生伪共享，效率偏低
 */
public class T01_CacheLinePadding {
    static final long COUNT=1000_10000L;

    @Contended//可以避免伪共享
    private static class T{
        public volatile long x=0L;
    }
    static T[] arr=new T[2];
    static {
        arr[0]=new T();
        arr[1]=new T();
    }

    public static void main(String[] args) {
        Thread t1=null,t2=null;
        t1=new Thread(()->{
            for (long i = 0; i < COUNT; i++) {
                arr[0].x=i;
            }
        });
        t2=new Thread(()->{
            for (long i = 0; i < COUNT; i++) {
                arr[1].x=i;
            }
        });

        long start=System.currentTimeMillis();
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end=System.currentTimeMillis();

        System.out.println(end-start);
    }

}
