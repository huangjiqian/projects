package com.myprojects.s02;

import java.util.LinkedList;
import java.util.Queue;

//双队列实现栈
public class T07_TwoQueuesImplementStack {

    public static class TwoQueuesImplementStack<T>{
        private Queue<T> queue;
        private Queue<T> help;

        public TwoQueuesImplementStack(){
            queue=new LinkedList<>();
            help=new LinkedList<>();
        }

        public void push(T value){
            queue.offer(value);
        }

        public T pop(){
            //将queue剩最后一个元素，其他都移到help中
            while (queue.size()>1){
                help.offer(queue.poll());
            }
            T value=queue.poll();
            Queue<T> empty=queue;//此时queue是空
            queue=help;
            help=empty;
            return value;
        }

        public T peek(){
            //将queue剩最后一个元素，其他都移到help中
            while (queue.size()>1){
                help.offer(queue.poll());
            }
            T value=queue.peek();
            help.offer(queue.poll());//将queue中剩下的元素转到help中
            Queue<T> empty=queue;//此时queue是空
            queue=help;
            help=empty;
            return value;
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    public static void main(String[] args) {
        TwoQueuesImplementStack<Integer> test=new TwoQueuesImplementStack<>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);

        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
    }

}
