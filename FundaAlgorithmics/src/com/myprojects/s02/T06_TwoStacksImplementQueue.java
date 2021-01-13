package com.myprojects.s02;

import java.util.Stack;

//两栈实现队列
public class T06_TwoStacksImplementQueue {
    public static class TwoStacksImplementQueue<T> {
        private Stack<T> stackPush;
        private Stack<T> stackPop;

        public TwoStacksImplementQueue(){
            stackPush = new Stack<>();
            stackPop = new Stack<>();
        }
        public void push(T value) {
            pushToPop();
            stackPush.push(value);
        }

        public T poll() {
            if(stackPush.isEmpty() && stackPop.isEmpty()){
                throw new RuntimeException("队列空了");
            }
            pushToPop();
            return stackPop.pop();
        }

        public T peek(){
            if(stackPush.isEmpty() && stackPop.isEmpty()){
                throw new RuntimeException("队列空了");
            }
            pushToPop();
            return stackPop.peek();
        }
        //stackPop空了把stackPush全部放进去
        public void pushToPop() {
            if (stackPop.size() == 0) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }
    }

    public static void main(String[] args) {
        TwoStacksImplementQueue<Integer> test=new TwoStacksImplementQueue<>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        test.push(6);

        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());

    }
}
