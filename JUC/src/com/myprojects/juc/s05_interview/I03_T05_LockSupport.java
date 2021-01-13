package com.myprojects.juc.s05_interview;


import java.util.concurrent.locks.LockSupport;

public class I03_T05_LockSupport {
    private static volatile boolean t2Started = false;
//    private static Lock lock=new ReentrantLock();
//    private static Condition printNum=lock.newCondition();
//    private static Condition printLetter=lock.newCondition();
    //private static CountDownLatch latch = new C(1);
    static Thread t1=null,t2=null;
    public static void main(String[] args) {

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        t1=new Thread(()->{

            for(char c : aI) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t2);

            }
        }, "t1");

        t2=new Thread(()->{
            for(char c : aC) {

                System.out.print(c);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
