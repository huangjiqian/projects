package com.company01;

import java.util.*;

public class MyListTest {
    public static void main(String[] args) {
        MyList myList=new MyList();
//        myList.add(2);
//        myList.add(5);
//        myList.add(6);
//        myList.add(4);
//        myList.add(1);
//        myList.add(3);
        Random random=new Random();
        List list =new ArrayList();
        for (int i = 0; i <1000000; i++) {
            list.add(random.nextInt(100000));
        }
        System.out.println(myList.add(list));
        //myList.show();
        long start=System.currentTimeMillis();
        //myList.bubbleSort();//10万数据排序所用时间：18646 100万数据太慢
        //myList.selectionSort();//10万数据排序所用时间：5346 100万数据太慢
        //myList.shellSort();//10万数据排序所用时间：24  100万数据排序所用时间：257
        //myList.mergeSort();//10万数据排序所用时间：19  100万数据排序所用时间：159 1000万数据排序所用时间：1814
        //myList.quickSort();//10万数据排序所用时间：24  100万数据排序所用时间：131  1000万数据排序所用时间：1364
        //myList.insertionSort();//10万数据排序所用时间：1438
        //myList.heapSort();//递归->1万数据排序所用时间：146
        //myList.countingSort();//1亿数据排序所用时间：369
        //myList.bucketSort();//10万数据排序所用时间：57 100万数据排序所用时间：332
        myList.basketSort();//100万数据排序所用时间：625
        //myList.show();
        long end=System.currentTimeMillis();
        System.out.println("10万数据排序所用时间："+(end-start));
//        Stack<Integer> stack=new Stack<>();
//        stack.push();
//        stack.size();
//        stack.pop();
//        LinkedList linkedList=new LinkedList();
//        linkedList.add();
//        linkedList.isEmpty();
//        linkedList.removeLast();
//        linkedList.removeFirst();
//        linkedList.getFirst();
//        Map<Integer,Integer> map=new HashMap<>();
//        map.get(1);
//        map.containsKey();
//        map.put();
//        map.size();
//        map.values();
//        Arrays.asList();
//        Set set=new HashSet();
        Queue<int[]> priorityQueue=new PriorityQueue<>();
    }
}
