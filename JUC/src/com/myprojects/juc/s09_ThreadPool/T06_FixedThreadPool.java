package com.myprojects.juc.s09_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * FixedThreadPool:只能设置固定线程数量
 */
public class T06_FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(2);
        System.out.println(service);
        for (int i = 0; i < 10; i++) {
            service.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);
        service.shutdown();
    }
}
