package com.myprojects.s03;

/**
 * @author HenryHuang
 * 归并排序时间复杂度为O(N * logN)
 */
public class T01_MergeSort {

    //递归方法实现
    public static void mergeSort1(int[] arr){
        if(arr==null || arr.length<2) {
            return;
        }
        sort(arr,0,arr.length-1);
    }

    private static void sort(int[] arr, int L, int R) {
        if(L==R){
            return;
        }
        int mid=L+((R-L)>>1);
        sort(arr,L,mid);
        sort(arr,mid+1,R);
        merge(arr,L,mid,R);
    }

    private static void merge(int[] arr, int L, int mid, int R){
        int[] help=new int[R-L+1];
        int index=0;
        int p1=L;
        int p2=mid+1;
        while (p1<=mid && p2<=R){
            help[index++]=arr[p1]>arr[p2] ? arr[p2++]:arr[p1++];
        }

        while (p1<=mid){
            help[index++]=arr[p1++];
        }
        while (p2<=R){
            help[index++]=arr[p2++];
        }

        for (int i = 0; i < index; i++) {
            arr[L+i]=help[i];
        }
    }

    //非递归方法实现
    public static void mergeSort2(int[] arr){
        if(arr == null || arr.length<2){
            return;
        }

        int N=arr.length;
        int mergeSize=1;
        while (mergeSize < N){
            int L=0;
            while (L<N){
                int mid=L+mergeSize-1;
                if(mid>=N){
                    break;
                }
                int R=Math.min(mid+mergeSize,N-1);
                merge(arr,L,mid,R);
                L=R+1;
            }
            if(mergeSize > N/2){
                break;
            }
            mergeSize <<= 1;
        }
    }
    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr={2,1,4,3,6,5};
        printArr(arr);
        mergeSort2(arr);
        printArr(arr);
    }

}
