package com.myprojects.s01;

import java.util.Arrays;

public class T04_BSNearRight {

    //找到<=value的最右位置
    public static int nearestIndex(int[] arr,int value){
        int L=0;
        int R=arr.length-1;
        int index=-1;
        while (L <= R){
            int mid=L+((R-L)>>1);
            if(arr[mid]<=value){
                index=mid;
                L=mid+1;
            }else{
                R=mid-1;
            }
        }
        return index;
    }

    public static int getIndex(int[] arr, int value){
        for (int i = arr.length-1; i >=0; i--) {
            if(arr[i]<=value){
                return i;
            }
        }
        return -1;
    }

    public static int[] generateRandomArray(int maxSize,int maxValue){
        int[] arr=new int[(int) ((maxSize+1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int) ((maxValue+1)*Math.random()) - (int) (maxValue*Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr){
        if(arr==null) return null;
        int[] result=new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i]=arr[i];
        }
        return result;
    }
    public static void comparator(int[] arr){
        Arrays.sort(arr);
    }

    public static void printArr(int[] arr){
        System.out.print("arr[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+",");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int testTimes=1000000;
        int maxSize=100;
        int maxValue=100;
        boolean success=true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr=generateRandomArray(maxSize,maxValue);
            int value= (int) (((maxValue+1)*Math.random())-(maxValue*Math.random()));
            Arrays.sort(arr);
            if(nearestIndex(arr,value)!=getIndex(arr,value)){
                System.out.println("i="+i);
                printArr(arr);
                System.out.println(value);
                System.out.println(nearestIndex(arr,value) + "," + arr[nearestIndex(arr,value)]);
                System.out.println(getIndex(arr,value) + "," + arr[getIndex(arr,value)]);
                success=false;
                break;
            }
        }
        System.out.println(success ? "Good":"Bad");
    }
}
