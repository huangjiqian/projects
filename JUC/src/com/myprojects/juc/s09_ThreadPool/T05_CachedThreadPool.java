package com.myprojects.juc.s09_ThreadPool;

import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * CachedThreadPool:所有线程都不会进入阻塞队列
 */
public class T05_CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService service= Executors.newCachedThreadPool();
        System.out.println(service);
        for (int i = 0; i < 10; i++) {
            service.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
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
