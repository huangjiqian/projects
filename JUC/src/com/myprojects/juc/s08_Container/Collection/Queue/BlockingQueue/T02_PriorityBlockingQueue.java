package com.myprojects.juc.s08_Container.Collection.Queue.BlockingQueue;

import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 按照定义的Comparable中compareTo方法，可以用大顶堆或小顶堆来实现排序，若直接打印队列，会以堆(树)的前序遍历打印。调用take方法一个个取出，
 * 打印出来的顺序就正确了
 */
public class T02_PriorityBlockingQueue {
    static BlockingQueue<Person> queue=new PriorityBlockingQueue<>(5);
    public static void main(String[] args) throws InterruptedException {
        queue.add(new Person(4,"a"));
        queue.add(new Person(1,"b"));
        queue.add(new Person(2,"c"));
        queue.add(new Person(3,"d"));
        queue.add(new Person(5,"e"));
        System.out.println(queue);
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
    }

    static class Person implements Comparable<Person>{
        String name;
        int id;
        public Person(int id,String name){
            this.id=id;
            this.name=name;
        }
        @Override
        public int compareTo(Person o) {
            if(o.id>this.id) return 1;
            else if(o.id<this.id) return -1;
            return 0;
        }

        @Override
        public String toString() {
            return id+":"+name;
        }
    }
}
