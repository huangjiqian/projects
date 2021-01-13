package com.myprojects.juc.s09_ThreadPool;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T08_ScheduledPool {
    public static void main(String[] args) {
        ScheduledExecutorService service= Executors.newScheduledThreadPool(4);
        Random r=new Random();
        for (int i = 0; i < 100; i++) {
            service.scheduleAtFixedRate(()->{
                int time=r.nextInt(1000);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" sleep "+time+"ms");
            },0,10, TimeUnit.MILLISECONDS);
        }
        System.out.println(service);
        //service.shutdown();
    }
}
