package com.myprojects.jvm.classloader;

public class T01_LoadClassByHand {
    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz=T01_LoadClassByHand.class.getClassLoader().loadClass("com.myprojects.jvm.bytecode.T01_ByteCode1");
        System.out.println(clazz.getName());//com.myprojects.jvm.bytecode.T01_ByteCode1
    }
}
