package com.myprojects.jvm.classloader;

/**
 * 严格讲应该叫lazy initialzing，因为java虚拟机规范并没有严格规定什么时候必须loading,
 * 但严格规定了什么时候initialzing
 */
public class T03_LazyLoading {
    public static void main(String[] args) throws ClassNotFoundException {
        //Parent parent;
        //Child child=new Child();
        //System.out.println(Parent.i);
        //System.out.println(Child.i);
        //System.out.println(new Parent());
        //System.out.println(T03_LazyLoading.class.getClassLoader().loadClass("com.myprojects.jvm.classloader.T03_LazyLoading$Parent"));
        //Class.forName("com.myprojects.jvm.classloader.T03_LazyLoading$Parent");
        Class.forName("com.myprojects.jvm.classloader.T03_LazyLoading$Child");
    }
    static class Parent{
        final static int i=100;
        static {
            System.out.println("Parent i="+i);
        }
    }

    static class Child extends Parent{
        static {
            System.out.println("Child i="+i);
        }
    }
}
