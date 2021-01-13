package com.myprojects.juc.s05_interview;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * @param <T>
 *
 */
public class I02_T02_Condition<T> {
    final private LinkedList<T> list=new LinkedList<>();
    final static private int MAX=10;
    static int count=0;

    private Lock lock=new ReentrantLock();
    private Condition producer=lock.newCondition();
    private Condition consumer=lock.newCondition();
    public T get(){
        T t=null;
        try{
            lock.lock();
            while(count==0){
                consumer.await();//阻塞消费者线程
            }
            t=list.removeFirst();
            count--;
            producer.signalAll();//通知生产者线程进行生产
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public void put(T t){
        try{
            lock.lock();
            while(count==MAX){//为什么使用while？防止线程刚被唤醒，条件又不符合
                    producer.await();//阻塞生产者线程
            }
            list.add(t);
            count++;
            consumer.signalAll();//通知消费者线程进行消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
    public static void main(String[] args) {
        I02_T02_Condition<String> test=new I02_T02_Condition();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) System.out.println(test.get());
            },"consumer"+i).start();
        }
        /*try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 20; j++) test.put(Thread.currentThread().getName() + " "+j);
            },"producer"+i).start();
        }
    }
}
