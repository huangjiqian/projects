package com.myprojects.juc.s08_Container.Collection.Queue.BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 类似Exchanger，比较好用
 */
public class T05_SynchronousQueue {//容量为0
    static BlockingQueue<String> queue=new SynchronousQueue<>();
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            try {
                System.out.println(queue.take());//没有生产者提供一个元素，线程阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        queue.put("aaa");//只有put方法才能放入元素。若没有消费者线程消费，线程阻塞
        //queue.offer("aa");
    }
}
