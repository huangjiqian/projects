package com.myprojects.juc.s07_RefTypeAndThreadLocal;


import java.util.concurrent.TimeUnit;

/**
 * 线程2创建Person对象，线程2无法访问，获取的值为null
 */
public class T02_ThreadLocal2 {
    volatile static ThreadLocal<Person> person=new ThreadLocal<>();
    public static void main(String[] args) {
        new Thread(()->{
                System.out.println(person.get());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(person.get());
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            person.set(new Person());
        }).start();
    }
    static class Person {
        String name="张三";
    }
}



