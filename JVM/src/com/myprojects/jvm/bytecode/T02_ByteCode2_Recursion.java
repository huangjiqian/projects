package com.myprojects.jvm.bytecode;

public class T02_ByteCode2_Recursion {
    public static void main(String[] args) {
        recursion(3);
    }

    public static int recursion(int num){
        if(num==1) return 1;
        return num*recursion(num-1);
    }
}
