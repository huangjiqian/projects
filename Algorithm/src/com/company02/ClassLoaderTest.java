package com.company02;

import java.util.Arrays;

public class ClassLoaderTest {
    public static void main(String[] args) {
        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);//jdk.internal.loader.ClassLoaders$AppClassLoader@2f0e140b

        //获取扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);//jdk.internal.loader.ClassLoaders$PlatformClassLoader@16b98e56

        //获取引导类加载器
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);//null

        //获取的是系统类加载器
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);//jdk.internal.loader.ClassLoaders$AppClassLoader@2f0e140b

        //获取引导类加载器-->只加载核心类库-->jdk api
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);//null

        ClassLoader classLoader2 = Arrays.class.getClassLoader();
        System.out.println(classLoader2);//null

//        Runnable runnable=() -> {
//            System.out.println("我只管线程");
//        };
//        runnable.run();
//        new Thread(()-> System.out.println("线程1")).start();
    }
}
