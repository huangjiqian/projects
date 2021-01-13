package com.company02;

public class LocalVariablesTest {
    public static void main(String[] args) {
        int count=10;
        LocalVariablesTest test=new LocalVariablesTest();
        System.out.println(test);
    }

    public void test1(){
        String str="abc";
        double d=123;
        char c= 'a';
    }

    public  void test2(){
        int a=1;
        {
            int b=2;
//            b=a+b;
//            double d=123;
//            System.out.println(d);
//            float f=345;
//            System.out.println(f);
        }
        int c=a+1;
        System.out.println(c);
    }
}
