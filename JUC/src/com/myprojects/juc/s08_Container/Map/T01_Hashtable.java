package com.myprojects.juc.s08_Container.Map;

import javax.sound.midi.Soundbank;
import java.util.Hashtable;
import java.util.Random;
import java.util.UUID;

public class T01_Hashtable {

    static Hashtable<UUID,UUID> map=new Hashtable<>();
    static UUID[] keys=new UUID[Constants.UUID_COUNT];
    static UUID[] values=new UUID[Constants.UUID_COUNT];
    static {
        for (int i = 0; i < Constants.UUID_COUNT; i++) {
            keys[i]=UUID.randomUUID();
            values[i]=UUID.randomUUID();
        }
    }
    
    public void add(){
        for (int i = 0; i < Constants.THREAD_COUNT; i++) {
            for (int j = 0; j < Constants.UUID_COUNT; j++) {
                map.put(keys[i],values[i]);
            }
        }
    }
    public static void main(String[] args) {
        T01_Hashtable test=new T01_Hashtable();
        Thread[] threads=new Thread[Constants.THREAD_COUNT];
        long start=System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(test::add);
        }
        for (int i = 0; i < threads.length; i++) threads[i].start();
        try {
            for (int i = 0; i < threads.length; i++) threads[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end=System.currentTimeMillis();
        System.out.println("write time="+ (end-start));

        //------------------------------------------------------

        threads=new Thread[Constants.THREAD_COUNT];
        start=System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(()->{
                for (int j = 0; j < Constants.READ_COUNT; j++) {
                    map.get(1000);
                }
            });
        }
        for (int i = 0; i < threads.length; i++) threads[i].start();
        try {
            for (int i = 0; i < threads.length; i++) threads[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end=System.currentTimeMillis();
        System.out.println("read time="+ (end-start));
    }
}
