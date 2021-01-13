package com.company02;

public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            Class<?> c=findLoadedClass(name);
            if(c==null){
                long t0=System.nanoTime();
                c=findLoadedClass(name);
            }
            long t1=System.nanoTime();
            return c;
        }
    }
}
