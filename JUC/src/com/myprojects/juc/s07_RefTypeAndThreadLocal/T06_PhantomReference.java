package com.myprojects.juc.s07_RefTypeAndThreadLocal;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * must be collected by GC
 */
public class T06_PhantomReference {
    public static void main(String[] args) {
        ReferenceQueue<T> queue=new ReferenceQueue<>();
        PhantomReference<T> ref=new PhantomReference(new T(), queue);
        System.out.println(ref.get());

    }
}
