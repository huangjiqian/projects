package com.myprojects.s01;

import java.util.Arrays;

/**
 * 对数器：完成算法，同时用一个很容易实现的算法，两个方法用同一组数进行比较，有错误说明算法不对。
 */
public class T01_BubbleSort{

    public static void bubbleSort(int[] arr){
        if(arr==null || arr.length<2) return;
        for(int i=arr.length-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    private static void swap(int[] arr, int j1, int j2) {
        int temp=arr[j1];
        arr[j1]=arr[j2];
        arr[j2]=temp;
    }

    //随机生成数组
    public static int[] generateRandomArray(int maxSize,int maxValue){
        int[] arr=new int[(int) ((maxSize+1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int) ((maxValue+1)*Math.random()) - (int) (maxValue*Math.random());
        }
        return arr;
    }

    //copy数组用于容易实现的算法
    public static int[] copyArray(int[] arr){
        if(arr==null) return null;
        int[] result=new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i]=arr[i];
        }
        return result;
    }
    //判断两个算法排序后的数组是否一样
    public static boolean equals(int[] arr1,int[] arr2){
        if((arr1==null && arr2!=null) || (arr1!=null && arr2==null)) return false;
        if(arr1==null && arr2==null) return true;
        if(arr1.length!=arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            //if(arr1[i] != arr2[i]) return false;
            if((arr1[i] ^ arr2[i]) != 0) return false;
        }
        return true;
    }

    //容易实现的算法
    public static void comparator(int[] arr){
        Arrays.sort(arr);
    }

    //打印数组
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
            int[] arr1=generateRandomArray(maxSize,maxValue);
            //printArr(arr1);
            //System.out.println("==============================================");
            int[] arr2=copyArray(arr1);
            bubbleSort(arr1);
            comparator(arr2);
            //printArr(arr1);
            //printArr(arr2);
            //System.out.println(i);
            if(!equals(arr1,arr2)){
                success=false;
                break;
            }
        }
        System.out.println(success ? "Good":"Bad");
    }
}
