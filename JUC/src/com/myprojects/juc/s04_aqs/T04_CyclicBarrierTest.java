package com.myprojects.juc.s04_aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier:指定线程数达到，释放锁
 */
public class T04_CyclicBarrierTest {
    private static CyclicBarrier barrier=new CyclicBarrier(10,()-> System.out.println("线程组结束完毕"));

    public static void main(String[] args) {
        T04_CyclicBarrierTest test=new T04_CyclicBarrierTest();
        Thread[] threads=new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(()->{
                try {
                    barrier.await();
                    //barrier.reset();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}
