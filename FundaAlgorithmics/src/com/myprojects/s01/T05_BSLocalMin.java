package com.myprojects.s01;

import java.util.Arrays;

/**
 * 利用二分法找到局部最小值
 */
public class T05_BSLocalMin {

    public static int localMinIndex(int[] arr){
        if(arr.length==0 || arr==null){
            return -1;
        }
        if(arr.length==1 || arr[0]<arr[1]){
            return 0;
        }
        if(arr[arr.length-1]<arr[arr.length-2]){
            return arr.length-1;
        }
        //if(arr.length==1) return 0;
        //int index=0;
        int L=1;
        int R=arr.length-1;
        //int mid=-1;
        while (L < R){
            /*if(arr[0]<=arr[1]){
                return 0;
            }else if(arr[arr.length-1]<=arr[arr.length-2]){
                return arr.length-1;
            }else{
                mid=L+((R-L)>>1);
                if(arr[mid]>arr[mid+1]){

                }
            }*/
            int mid=L+((R-L)>>1);
            if(arr[mid]>arr[mid+1]){
                L=mid+1;
            }else if(arr[mid]>arr[mid-1]){
                R=mid-1;
            }else{
                //index=mid;
                return mid;
            }
        }
        return L;
    }

    public static int[] generateRandomArray(int maxSize,int maxValue){
        int[] arr=new int[(int) ((maxSize+1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int) ((maxValue+1)*Math.random()) - (int) (maxValue*Math.random());
        }
        return arr;
    }

    public static void printArr(int[] arr){
        System.out.print("arr[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+",");
        }
        System.out.println("]");
    }

    public static boolean isLocalMin(int[] arr, int index){
        if(arr.length==0 || arr==null){
            return false;
        }
        if(arr.length==1 || arr[0]<arr[1]){
            return true;
        }
        if(arr[arr.length-1]<arr[arr.length-2]){
            return true;
        }
        if(index == 0){
            return arr[index]<arr[index+1];
        }else if(index==arr.length-1){
            return arr[index]<arr[index-1];
        }else{
            return (arr[index]<arr[index-1]) && (arr[index]<arr[index+1]);
        }
    }

    public static void main(String[] args) {
        int testTimes=1000000;
        int maxSize=100;
        int maxValue=100;
        boolean success=true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr=generateRandomArray(maxSize,maxValue);
            if(!isLocalMin(arr,localMinIndex(arr))){
                if(localMinIndex(arr) < 0) break;
                System.out.println("i="+i);
                printArr(arr);
                System.out.println(localMinIndex(arr) + "," + arr[localMinIndex(arr)]);
                success=false;
                break;
            }
        }
        System.out.println(success ? "Good":"Bad");
    }
}
