package com.myprojects.juc.s07_RefTypeAndThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * StrongRef--NormalRef
 */
public class T03_NormalReference {
    public static void main(String[] args) {
        T t=new T();
        System.out.println(t);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t=null;
        System.gc();//full gc
        System.out.println(t);

    }
}
