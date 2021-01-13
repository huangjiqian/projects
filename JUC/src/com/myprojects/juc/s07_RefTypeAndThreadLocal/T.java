package com.myprojects.juc.s07_RefTypeAndThreadLocal;

public class T {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("object is finalize...");
    }
}
