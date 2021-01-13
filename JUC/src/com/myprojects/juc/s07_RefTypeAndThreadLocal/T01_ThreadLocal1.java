package com.myprojects.juc.s07_RefTypeAndThreadLocal;


import java.util.concurrent.TimeUnit;

public class T01_ThreadLocal1 {
    volatile static Person person=new Person();
    volatile static Object lock=new Object();
    public static void main(String[] args) {
        new Thread(()->{
            //synchronized (lock) {
                System.out.println(person.name);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(person.name);
            //}
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            person.name = "李四";

        }).start();
    }
}

class Person {
    String name="张三";
}
