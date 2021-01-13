package com.myprojects.jvm.classloader;

import com.myprojects.jvm.Hello;
import com.myprojects.jvm.basic.T02_ClassLoader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 重写ClassLoader的loadClass方法能够打破双亲委派机制，但是不建议打破，风险很大
 */
public class T02_ClassLoader1 extends ClassLoader{

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        FileInputStream fis=null;
        ByteArrayOutputStream baos=null;
        //File file=new File("c:/Java/Projects/JVM/"+name.replaceAll(".","/").concat(".class"));
        File file=new File(name);
        try {
            fis=new FileInputStream(file);
            baos=new ByteArrayOutputStream();
            int b=0;
            while ((b=fis.read())!=0){
                baos.write(b);
            }
            byte[] bytes=baos.toByteArray();
            //return defineClass(name,bytes,0,bytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis!=null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(baos!=null){
                    try {
                        baos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return super.findClass(name);
    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        ClassLoader cl= new T02_ClassLoader1();
        Class clazz1=cl.loadClass("com.myprojects.jvm.Hello");
        Class clazz2=cl.loadClass("com.myprojects.jvm.Hello");
        System.out.println(clazz1==clazz2);

        Hello hello= (Hello) clazz1.newInstance();
        //Method method=clazz1.getDeclaredMethod("m",null);
        //System.out.println(methods[0]);
        //methods[0].invoke(clazz1,Void.class);
        hello.m();

        System.out.println(clazz1.getClassLoader());
        System.out.println(clazz1.getClassLoader().getParent());
        System.out.println(getSystemClassLoader());
    }
}
