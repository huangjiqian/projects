package com.myprojects.juc.s08_Container.Collection.Queue;

import java.util.PriorityQueue;

/**
 * 优先队列：内部对元素进行排序，利用Comparator排序
 */
public class T01_PriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<String> queue=new PriorityQueue<>();
        queue.add("z");
        queue.add("a");
        queue.add("c");
        queue.add("b");
        queue.add("r");

        for (int i = 0; i < 5; i++) {
            System.out.println(queue.poll());
        }
    }
}
