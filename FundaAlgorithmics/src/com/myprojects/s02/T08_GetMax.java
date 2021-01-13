package com.myprojects.s02;

public class T08_GetMax {

    //递归获取arr中最大值
    public static int getMax(int[] arr){
        return process(arr,0,arr.length-1);
    }

    public static int process(int[] arr,int L,int R){
        if(L==R) {
            return arr[L];
        }
        int mid=L+((R-L)>>1);
        int leftMax=process(arr,L,mid);
        int rightMax=process(arr,mid+1,R);
        return Math.max(leftMax,rightMax);
    }

    public static void main(String[] args) {
        int[] arr={2,1,3,4,5,2};
        System.out.println(getMax(arr));
    }
}
