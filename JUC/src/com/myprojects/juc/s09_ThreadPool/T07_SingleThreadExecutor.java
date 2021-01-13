package com.myprojects.juc.s09_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * SingleThreadExecutor:只有单线程的线程池
 * 为什么要有单线程的线程池呢？
 * 可以用于线程池的任务队列和生命周期管理
 */
public class T07_SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService service= Executors.newSingleThreadExecutor();
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
