package com.myprojects.jvm.bytecode;

public class T01_ByteCode1 {
    public static void main(String[] args) {
        T01_ByteCode1 test=new T01_ByteCode1();
        test.m();
    }

    /**
     *  0 ldc #5 <张三> //从常量池取出item
     *  2 astore_1 //name弹出栈
     *  3 getstatic #6 <java/lang/System.out> //调用static方法
     *  6 aload_1 //name压栈
     *  7 invokevirtual #7 <java/io/PrintStream.println> //调用打印方法
     * 10 bipush 12 // i赋值为12
     * 12 istore_2 //将i弹出栈
     * 13 ldc2_w #8 <10000> //Push long or double from run-time constant pool
     * 16 lstore_3 //将l弹出栈
     * 17 ldc2_w #10 <1000000.0>//Push long or double from run-time constant pool
     * 20 dstore 5 //将double值弹出栈存储到局部变量表为5的位置
     * 22 return
     */
    void m(){
        String name="张三";
        System.out.println(name);
        int i=12;
        long l=10000L;
        double d=1000000D;

    }

}
