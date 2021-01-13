package com.myprojects.juc.s07_RefTypeAndThreadLocal;

import java.lang.ref.WeakReference;

/**
 * WeakReference will be collected when meet with GC.But WeakReference inner object has a normal reference,GC will not collect it.
 */
public class T05_WeakReference {
    public static void main(String[] args) {
        //T t=new T();
        WeakReference<T> ref=new WeakReference(new T());
        System.out.println("before object T set null,ref.var="+ref.get());
        System.gc();
        System.out.println("after object T set null,ref.var="+ref.get());

        //When threadLocal is not usage,must call remove method to remove the value of key
        ThreadLocal<T> threadLocal=new ThreadLocal<>();
        threadLocal.set(new T());
        threadLocal.remove();
    }
}
