package com.myprojects.juc.s05_interview;


public class I03_T01_WaitNotify {
    private static int num=1;
    private static char letter='A';
    private static volatile boolean flag=false;
   /* private static char[] aI;
    private static char[] aC;

    static {
        aI=new char[26];
        aC=new char[26];
        for (int i = 0; i < ; i++) {

        }
    }*/
    //private final Object lock=new Object();
    public synchronized void printNum(){
        if(num>26) return;
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.print(num++);
        //能运行到这，说明flag=true了
        flag=false;
        try {
            this.notify();
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.notify();//必须，否则无法停止程序
        printLetter();
    }
    public synchronized void printLetter(){
        if(letter>'Z') return;
        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(letter++);
        flag=true;
        try {
            this.notify();
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.notify();//必须，否则无法停止程序
        printNum();
    }

    public static void main(String[] args) {
        I03_T01_WaitNotify test=new I03_T01_WaitNotify();
        new Thread(test::printNum).start();
        new Thread(test::printLetter).start();
    }
}
