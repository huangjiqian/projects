package com.myprojects.s03;

/**
 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1、3
 * 2左边比2小的数：1
 * 5左边比5小的数：1、3、4、 2
 * 所以数组的小和为1+1+3+1+1+3+4+2=16
 */
public class T02_SmallSum {

    public static int smallSum(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        return process(arr,0,arr.length-1);
    }

    private static int process(int[] arr, int L, int R) {
        if(L==R){
            return 0;
        }
        int mid=L+((R-L)>>1);
        return process(arr,L,mid) + process(arr,mid+1,R) + merge(arr,L,mid,R);
    }

    private static int merge(int[] arr, int L, int mid, int R) {
        int[] help=new int[R-L+1];
        int index=0;
        int result=0;
        int p1=L;
        int p2=mid+1;
        while (p1 <= mid && p2 <= R){
            help[index++]=arr[p1]>arr[p2] ? arr[p2++]:arr[p1++];
            if(arr[p2]>arr[p1]){
                result+=arr[p1];
            }
        }

        while (p1 <= mid){
            help[index++]=arr[p1++];
        }
        while (p2 <= R){
            help[index++]=arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L+i]=help[i];
        }
        return result;
    }

    public static int comparator(int[] arr){
        if(arr==null || arr.length<2){
            return 0;
        }
        int result=0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                result+=arr[j]>arr[i]?arr[i]:0;
            }
        }
        return result;
    }

    public static int[] generateRandomArray(int maxSize,int maxValue){
        int[] arr=new int[(int) (Math.random() * (maxSize+1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int) Math.random() * (maxValue+1) - (int) Math.random() * maxValue;
        }
        return arr;
    }

    public static int[] copyArray(int[] arr){
        if(arr==null){
            return null;
        }
        int[] res=new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i]=arr[i];
        }
        return res;
    }
    public static void main(String[] args) {
        int maxSize=100;
        int testTimes=100000;
        int maxValue=100;
        boolean success=true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1=generateRandomArray(maxSize,maxValue);
            int[] arr2=copyArray(arr1);
            if(smallSum(arr1) != comparator(arr2)){
                success=false;
                break;
            }
        }
        System.out.println(success ? "success":"failure");
    }
}
