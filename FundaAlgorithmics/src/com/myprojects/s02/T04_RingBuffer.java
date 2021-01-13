package com.myprojects.s02;

public class T04_RingBuffer {
    public static class MyQueue{
        public int[] arr;
        public int size;//queue中元素的数量
        public int iPush;
        public int iPop;
        public int len;

        public MyQueue(int len){
            this.arr=new int[len];
            this.size=0;
            this.iPush=0;
            this.iPop=0;
            this.len=len;
        }

        public void push(int value){
            if(size==len){
                throw new RuntimeException("栈满了...");
            }
            size++;
            arr[iPush]=value;
            iPush=nextIndex(iPush);
        }
        public int pop(){
            if(size==0){
                throw new RuntimeException("栈空了...");
            }
            size--;
            int value=arr[iPop];
            iPop=nextIndex(iPop);
            return value;
        }
        public boolean isEmpty(){
            return size==0;
        }
        //判断index是否等于数组长度
        public int nextIndex(int i){
            return i<len-1?i+1:0;
        }

    }
}
