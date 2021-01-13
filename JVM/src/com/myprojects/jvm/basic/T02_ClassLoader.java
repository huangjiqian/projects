package com.myprojects.jvm.basic;

public class T02_ClassLoader {
    public static void main(String[] args) {
        System.out.println(T02_ClassLoader.class.getClass().getClassLoader());
        System.out.println(T02_ClassLoader.class.getClassLoader());
        System.out.println(T02_ClassLoader.class.getClassLoader().getParent());
        System.out.println(T02_ClassLoader.class.getClassLoader().getParent().getParent());
    }
}
