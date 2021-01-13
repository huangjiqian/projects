package com.myprojects.utils;

import java.util.Arrays;
import java.util.Random;

/**
 *
 */
public class SortArrComparator {
    private int maxSize;
    private long maxValue;
    private long[] arr1;
    private long[] arr2;
    public SortArrComparator(int maxSize,long maxValue){
        this.maxSize=maxSize;
        this.maxValue=maxValue;
        arr1=new long[maxSize];
        arr2=new long[maxSize];
    }

    public long[] getArr2(){
        return arr2;
    }
    public void generateArr(){
        Random r=new Random();
        for (int i = 0; i < maxSize; i++) {
            long value= r.nextInt()*maxValue - r.nextInt()*maxValue;
            arr1[i]=value;
            arr2[i]=value;
        }
    }
    //对arr1使用很容易实现的排序，结果一定是正确的
    public void comparator(){
        Arrays.sort(arr1);
    }

    public long[] sort(long[] arr2){return arr2;}

    public void compare(){
        boolean success=true;
        for (int i = 0; i < maxValue; i++) {
            generateArr();
            comparator();
            arr2=sort(arr2);
            for (int j = 0; j < arr1.length; j++) {
                if(arr1[j]!=arr2[j]){
                    success=false;
                    break;
                }
            }
        }
        System.out.println(success ? "成功了":"失败了");

    }
}
