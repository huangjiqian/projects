package com.myprojects.juc.s05_interview;


public class I03_T03_State {
    private static int num=1;
    private static char letter='A';
    private volatile static int state=1;//1代表执行printLetter,0代表执行printNum
    public synchronized void printNum(){
        if(num > 26) return;
        if(state==0) {
            System.out.print(num);
            state++;
            num++;
        }
        printLetter();
    }
    public synchronized void printLetter(){
        if(letter>'Z') return;
        if(state==1) {
            System.out.print(letter);
            state--;
            letter++;
        }
        printNum();
    }

    public static void main(String[] args) {
        I03_T03_State test=new I03_T03_State();
        new Thread(test::printNum).start();
        new Thread(test::printLetter).start();
    }
}
