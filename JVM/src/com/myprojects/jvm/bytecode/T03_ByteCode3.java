package com.myprojects.jvm.bytecode;

public class T03_ByteCode3 {
    public static void main(String[] args) {
        int i=8;
        /**
         * 0 bipush 8
         * 2 istore_1
         * 3 iload_1
         * 4 iinc 1 by 1
         * 7 istore_1
         * 8 return
         */
        i=i++;

        /**
         * 0 bipush 8
         * 2 istore_1
         * 3 iinc 1 by 1
         * 6 iload_1
         * 7 istore_1
         * 8 return
         */
        //i=++i;
    }
}
