package com.myprojects.juc.s08_Container.Collection.Queue;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 *
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？
 *
 * 就算操作A和B都是同步的，但A和B组成的复合操作也未必是同步的，仍然需要自己进行同步
 * 就像这个程序，判断size和进行remove必须是一整个的原子操作
 *
 * 使用ConcurrentQueue提高并发性
 */
public class T02_ConcurrentLinkedQueue {
    //static int tickets=1000;
    static int windows=10;
    static Queue<String> tickets=new PriorityQueue<>();
    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("编号"+i);
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < windows; i++) {
            new Thread(()->{
                while(true){
                    String s=tickets.poll();//获取不到值，返回为null
                    if(s==null) break;
                    else System.out.println(Thread.currentThread().getName()+" sell "+ s);
                }
            }).start();
        }
    }
}
