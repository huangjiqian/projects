package com.myprojects.juc.s09_ThreadPool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * WorkStealPool:每个线程都有队列，若线程执行完任务，会从其他线程偷任务来执行，属于ForkJoinPool
 */
public class T09_WorkStealingPool {
    public static void main(String[] args) {
        ExecutorService service= Executors.newWorkStealingPool();
        service.execute(new M(1000));
        service.execute(new M(2000));
        service.execute(new M(2000));
        service.execute(new M(2000));

        try {
            //由于产生的是精灵线程（守护线程、后台线程），主线程不阻塞的话，看不到输出
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class M implements Runnable{
        int time;
        public M(int time){
            this.time=time;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" sleep " +time+"ms");
        }
    }
}
