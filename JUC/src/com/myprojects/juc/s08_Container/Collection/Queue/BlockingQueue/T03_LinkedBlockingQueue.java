package com.myprojects.juc.s08_Container.Collection.Queue.BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class T03_LinkedBlockingQueue {
    static BlockingQueue<String> queue=new LinkedBlockingDeque<>();

    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                queue.add("编号"+i);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);//如果满了，线程阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (;;) {
                    try {
                        System.out.println(Thread.currentThread().getName()+"-take-"+queue.take());//队列空了，线程阻塞
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"t"+i).start();
        }
    }
}
