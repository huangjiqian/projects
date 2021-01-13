package com.myprojects.jvm.basic;

public class T03_IdentityHashCode {
    public static void main(String[] args) {
        Object o=new Object();
        System.out.println(System.identityHashCode(o));
        System.out.println(o.hashCode());
        Object o1=new Object();
        System.out.println(System.identityHashCode(o1));
    }
}
