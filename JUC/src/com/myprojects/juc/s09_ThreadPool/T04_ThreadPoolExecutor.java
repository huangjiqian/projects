package com.myprojects.juc.s09_ThreadPool;

import java.io.IOException;
import java.util.concurrent.*;

public class T04_ThreadPoolExecutor {

    static class Task implements Runnable{

        int i;
        public Task(int i){
            this.i=i;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" Task"+i);
            try {
                TimeUnit.SECONDS.sleep(10);
                System.in.read();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task {i="+i+"}";
        }
    }
    public static void main(String[] args) {
        ThreadPoolExecutor tpe=new ThreadPoolExecutor(2,4,60
                ,TimeUnit.SECONDS
                ,new ArrayBlockingQueue<Runnable>(4)
                ,Executors.defaultThreadFactory()
                ,new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 8; i++) {
            tpe.execute(new Task(i));
        }

        System.out.println(tpe.getQueue());
        tpe.execute(new Task(100));
        System.out.println(tpe.getQueue());
        tpe.shutdown();
    }
}
