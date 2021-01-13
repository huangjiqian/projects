package com.myprojects.juc.s10_FalseSharing;

/**
 * cache line为64位，先定义7个long变量，保证arr数组两个不在同一个cache line，两个线程分别修改数组中两个数时不会发生伪共享，效率较高
 */
public class T02_CacheLinePadding {
    static final long COUNT=1000_10000L;
    private static class Padding {
        static volatile long l1, l2, l3, l4, l5, l6, l7;
    }
    private static class T extends Padding{
        public volatile long x=0L;
    }
    static T[] arr=new T[2];
    static {
        arr[0]=new T();
        arr[1]=new T();
    }

    public static void main(String[] args) {
        Thread t1,t2;
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
