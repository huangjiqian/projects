package com.myprojects.juc.s07_RefTypeAndThreadLocal;

import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * jvm args config:-Xms25m -Xmx25m.When memory exceeds 25m,the object will be GC.
 * result:
 * first add bytes,object ref=[B@16b98e56
 * after gc,ref=[B@16b98e56
 * second add bytes,object ref=null
 *
 */
public class T04_SoftReference {
    static SoftReference<byte[]> ref=new SoftReference<>(new byte[1024*1024*15]);//15M memory
    //static List<byte[]> list=new LinkedList<>();
    //static byte[] bytes=new byte[1024*1024*15];//15M memory

    public static void main(String[] args) {
        //list.add(bytes);
        //now memory is 15M
        System.out.println("first add bytes,object ref="+ref.get());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //list.add(bytes);
        System.gc();
        System.out.println("after gc,ref="+ref.get());
        //memory is 30M
        byte[] bytes=new byte[1024*1024*15];
        System.out.println("second add bytes,object ref="+ref.get());
    }
}
