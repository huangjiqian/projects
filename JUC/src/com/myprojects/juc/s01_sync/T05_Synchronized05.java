package com.myprojects.juc.s01_sync;

import java.util.concurrent.TimeUnit;

/**
 * 脏读：在业务写的过程中，同时进行业务读的操作，可能会读出还未执行操作完的值。
 * 如果脏读不影响业务，那么业务读没必要也加锁，会降低效率。
 * 面试题：模拟银行账户，对业务写方法加锁，对业务读方法不加锁，这样行不行？
 */
public class T05_Synchronized05 {
    private static int price=0;
    //业务写方法加锁
    public synchronized void modify(){
        System.out.println("modify before price="+price);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        price=50;
        System.out.println("modify after price="+price);
    }
    //业务读方法不加锁
    public void read(){
        System.out.println("read price="+price);
    }

    public static void main(String[] args) {
        T05_Synchronized05 s05=new T05_Synchronized05();
        new Thread(s05::modify).start();
        new Thread(s05::read).start();
    }
}
