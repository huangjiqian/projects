package com.myprojects.s02;

import java.util.Stack;

//添加getMin方法获取最小值
public class T05_GetMinStack {
    public static class GetMinStack {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public GetMinStack(){
            stackData = new Stack<>();
            stackMin = new Stack<>();
        }

        public void push(int value) {
            if (stackData.size() == 0) {
                stackData.push(value);
                //第一次push元素不需要比较
                stackMin.push(value);
            } else {
                //取minStack栈顶的值与value中的最小值放入
                stackData.push(value);
                int minValue = stackMin.peek();
                stackMin.push(minValue < value ? minValue : value);
            }
        }

        public int pop() {
            if (stackData.isEmpty()) {
                throw new RuntimeException("栈空了...");
            } else {
                stackMin.pop();
                return stackData.pop();
            }
        }

        public int getMin() {
            if (stackData.size() == 0) {
                throw new RuntimeException("栈空了...");
            } else {
                return stackMin.peek();
            }
        }

        public boolean isEmpty() {
            return stackData.isEmpty();
        }
    }
    public static void main(String[] args) {
        //T05_GetMinStack test=new T05_GetMinStack();
        GetMinStack test=new GetMinStack();
        test.push(3);
        test.push(4);
        test.push(2);
        test.push(1);
        test.push(6);
        test.push(7);

        System.out.println(test.getMin());
        test.pop();
        System.out.println(test.getMin());
        System.out.println(test.getMin());
        System.out.println(test.getMin());
    }
}
