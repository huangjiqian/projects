package com.myprojects.juc.s08_Container.Collection.Queue.BlockingQueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T01_ArrayBlockingQueue {
    static BlockingQueue<String> queue=new ArrayBlockingQueue<>(10);
    //static Random random=new Random();
    public static void main(String[] args) throws InterruptedException {
        //先把队列装满
        for (int i = 0; i < 10; i++) {
            queue.add("aa");
        }
        //queue.put("aa");//队列满了，再添加元素就会等待，程序阻塞
        //queue.add("aa");//队列满了，再添加元素报错IllegalStateException: Queue full
        queue.offer("aa", 1, TimeUnit.SECONDS);//队列满了，队列等待设置的时间，时间过后还不能加入元素，就会直接略过
        System.out.println(queue);
    }
}
