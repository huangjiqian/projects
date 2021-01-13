package com.myprojects.juc.s01_sync;

import java.util.concurrent.TimeUnit;

/**
 * 同步代码在执行过程中如果出现异常，默认情况会释放锁
 * 如果不想被释放，在异常周围加上try-catch，会输出异常信息，但方法不会中断执行
 */
public class T08_Synchronized08 {
    private static int count=1;
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName());
        while (true){
            for (int i = 0; i < 50; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(count++);
            }

            try {
                int j=1/0;//异常
            } catch (Exception e){
                e.printStackTrace();
            }


            /*for (int i = 0; i < 50; i++) {
                System.out.println(count++);
            }*/
        }
    }

    public static void main(String[] args) {
        T08_Synchronized08 s08=new T08_Synchronized08();
        new Thread(s08::m1).start();
        new Thread(s08::m1).start();
    }
}
