package com.company02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeapTest {
    Byte[] arr=new Byte[new Random().nextInt(1024*100)];
    public static void main(String[] args) {
        List<HeapTest> list=new ArrayList<>();
        while (true){
            list.add(new HeapTest());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
