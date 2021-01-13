package com.myprojects.juc.s09_ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class T11_ParallelStreamAPI {
    static int count=100000;
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Random().nextInt(count));
        }
        long start=System.currentTimeMillis();
        list.forEach(v->isPrime(v));
        long end=System.currentTimeMillis();
        System.out.println("normal:"+(end-start)+"ms");

        start=System.currentTimeMillis();
        list.parallelStream().forEach(v->isPrime(v));
        end=System.currentTimeMillis();
        System.out.println("parallelStream:"+(end-start)+"ms");
    }

    public static boolean isPrime(int num){
        for (int i = 2; i < num/2; i++) {
            if(num % i==0) return false;
        }
        return true;
    }
}
