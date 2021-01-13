package com.myprojects.juc.s05_interview;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * @param <T>
 *
 */
public class I02_T01_WaitNotifyAll<T> {
    //static I02_T01_WaitNotifyAll producer=new I02_T01_WaitNotifyAll();
    final private LinkedList<T> list=new LinkedList<>();
    final static private int MAX=10;
    static int count=0;

    public synchronized T get(){
        while(count==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        T t=list.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }

    public synchronized void put(T t){
        while(count==MAX){//为什么使用while？防止线程刚被唤醒，条件又不符合
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list.add(t);
        count++;
		this.notifyAll();
    }

    public int getCount() {
        return count;
    }
    public static void main(String[] args) {
        I02_T01_WaitNotifyAll<String> test=new I02_T01_WaitNotifyAll();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) System.out.println(test.get());
            },"consumer"+i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 20; j++) test.put(Thread.currentThread().getName() + " "+j);
            },"producer"+i).start();
        }
    }
}
