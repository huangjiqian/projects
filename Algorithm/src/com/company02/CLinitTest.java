package com.company02;

public class CLinitTest {
    private static int a=1;

    static {
        a=2;
        b=2;
    }
    private static int b=1;
    public static void main(String[] args) {
        System.out.println(a);
        System.out.println(b);
    }
}
