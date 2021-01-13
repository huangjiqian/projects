package com.myprojects.juc.s05_interview;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//A1B2C3D4E5F6G7H8I9J10K11L12M13N14O15P16Q17R18S19T20U21V22W23X2425YZ
public class I03_T02_CountDownLatch {
    private static int num=1;
    private static char letter='A';
    //private final Object lock=new Object();
    private Lock lock=new ReentrantLock();
    private CountDownLatch latch=new CountDownLatch(1);
    public void printNum(){
        if(num>=26) return;
        try {
            latch.await();
            lock.lock();
            System.out.print(num);
            num++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        printLetter();
    }
    public synchronized void printLetter(){
        if(letter>'Z') return;
        try {
            lock.lock();
            System.out.print(letter);
            letter++;

        } finally {
            lock.unlock();
        }
        latch.countDown();
        printNum();
    }

    public static void main(String[] args) {
        I03_T02_CountDownLatch test=new I03_T02_CountDownLatch();
        new Thread(test::printNum).start();
        new Thread(test::printLetter).start();
    }
}
