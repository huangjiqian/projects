package com.myprojects.juc.s04_aqs;

import java.util.concurrent.Exchanger;

/**
 * Exchanger:要求2个线程运行才能交换值，一个线程会出现堵塞
 */
public class T05_ExchangerTest {
    static Exchanger<String> exchanger=new Exchanger<>();
    public void m1(){
        String s="t1";
        try {
            s=exchanger.exchange(s);
            //Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" "+s);
    }
    public void m2(){
        String s="t2";
        try {
            s=exchanger.exchange(s);
            //Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" "+s);
    }

    public static void main(String[] args) {
        T05_ExchangerTest test=new T05_ExchangerTest();
        new Thread(test::m1,"T1").start();
        new Thread(test::m2,"T2").start();
    }
}
