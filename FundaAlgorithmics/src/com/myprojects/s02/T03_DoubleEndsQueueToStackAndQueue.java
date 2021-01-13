package com.myprojects.s02;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class T03_DoubleEndsQueueToStackAndQueue {
    public static class Node<T>{
        public Node<T> prev;
        public Node<T> next;
        public T value;

        public Node(T value){
            this.value=value;
        }
    }

    public static class DoubleEndsQueue<T>{
        Node<T> head;
        Node<T> tail;

        public void addFromHead(T value){
            Node<T> node=new Node<>(value);
            if(head==null){
                head=node;
                tail=node;
            }else{
                node.next=head;
                head.prev=node;
                head=node;
            }
        }

        public void addFromTail(T value){
            Node<T> node=new Node<>(value);
            if(tail==null){
                head=node;
                tail=node;
            }else{
                tail.next=node;
                node.prev=tail;
                tail=node;
            }
        }

        public T popFromHead(){
            if(head==null) return null;
            Node<T> node=head;
            if(head.next==null){
                head=null;
                tail=null;
            }else{
                head=head.next;
                node.next=null;
                head.prev=null;
            }
            return node.value;
        }

        public T popFromTail(){
            if(tail==null) return null;
            Node<T> node=tail;
            if(tail.prev==null){
                head=null;
                tail=null;
            }else{
                tail=tail.prev;
                node.prev=null;
                tail.next=null;
            }
            return node.value;
        }

        public boolean isEmpty(){
            return head==null;
        }
    }

    public static class MyStack<T>{
        private DoubleEndsQueue<T> queue;
        public MyStack(){
            queue=new DoubleEndsQueue<>();
        }

        public void push(T value){
            queue.addFromHead(value);
        }

        public T pop(){
            return queue.popFromHead();
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    public static class MyQueue<T>{
        private DoubleEndsQueue<T> queue;
        public MyQueue(){
            queue=new DoubleEndsQueue<>();
        }

        public void push(T value){
            queue.addFromHead(value);
        }

        public T poll(){
            return queue.popFromTail();
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    public static boolean isEquals(Double value1,Double value2){
        return value1.equals(value2);
    }
    public static void main(String[] args) {
        int size=1000;
        int maxValue=1000;
        int testTimes=1000000;
        boolean success=true;
        label:
        for (int i = 0; i < testTimes; i++) {
            MyStack<Double> myStack=new MyStack<>();
            MyQueue<Double> myQueue=new MyQueue<>();

            Stack<Double> stack=new Stack<>();
            Queue<Double> queue=new LinkedList<>();

            for (int j = 0; j < size; j++) {
                double num= Math.random() * maxValue;
                if(stack.isEmpty()){
                    myStack.push(num);
                    stack.push(num);
                }else{
                    if(num<(maxValue * 0.05)){
                        myStack.push(num);
                        stack.push(num);
                    }else{
                        if(!isEquals(myStack.pop(),stack.pop())){
                            System.out.println("fail my stack");
                            success=false;
                            break label;
                        }
                    }
                }
                num= Math.random() * maxValue;
                if(queue.isEmpty()){
                    myQueue.push(num);
                    queue.offer(num);
                }else{
                    if(num<(maxValue * 0.05)){
                        myQueue.push(num);
                        queue.offer(num);
                    }else {
                        if(!isEquals(myQueue.poll(),queue.poll())){
                            System.out.println("fail my queue");
                            success=false;
                            break label;
                        }
                    }
                }
            }
        }
        System.out.println(success ? "success":"fail");
    }
}
