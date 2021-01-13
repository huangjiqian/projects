package com.myprojects.juc.s02_volat;

/**
 * 在单例的懒汉模式中，在对象实例创建时，DCL保证对象创建过程的线程安全。但是DCL在极高的访问中，可能出现问题
 * 对象创建：1.jvm给对象分配内存-2.给对象初始化值（基本数据类型0，引用类型null）-3.赋值-4.栈进行访问
 * 高并发时会出现对象刚初始化完成，就被栈访问-指令顺序1243
 * 给变量添加volatile，禁止指令重排
 * 原理：①对volatile变量写操作时，会在写操作后加一个store屏障指令，将工作内存中的变量值刷新到主内存内。
 * ②对volatile变量进行读操作时，会在读操作前加入一条load屏障指令，从主内存中读取变量
 *
 */
public class T03_Volatile03 {
    private /*volatile*/ static T03_Volatile03 instance;
    public static T03_Volatile03 getInstance(){
        if(instance==null){
            //双重检查锁-Double Check Lock
            synchronized (T03_Volatile03.class){
                if(instance==null){
                    instance=new T03_Volatile03();
                }
            }
        }
        return instance;
    }

    public static void show(){
        System.out.println(getInstance());
    }
    public static void main(String[] args) {
        //Thread[] threads=new Thread[500];
        for (int i = 0; i < 500; i++) {
            new Thread(T03_Volatile03::show).start();
        }

    }
}
