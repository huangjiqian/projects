package com.myprojects.s03;


import java.util.Random;

public class T03_PartitionAndQuickSort {

    //实现快速排序
    public static void quickSort1(int[] arr){
        if(arr==null || arr.length<2){
            return;
        }
        quick(arr,0,arr.length-1);
    }

    private static void quick(int[] arr, int L, int R) {
        if(L > R){
            return;
        }
        int pivot=swapLeftAndRight(arr,L,R);
        quick(arr,L,pivot-1);
        quick(arr,pivot+1,R);
    }

    //比pivot大的放右边，比pivot小的放左边
    private static int swapLeftAndRight(int[] arr, int L, int R) {
        //int pivot= (int) (Math.random() * (arr.length));
        //int pivot=new Random().nextInt(arr.length-1);
        int pivot=L;
        //int fromLeft=L;
        //int fromRight=R;
        while (L<R){
            while (L<R && arr[L] <= arr[pivot]){
                L++;
            }
            while (L<R && arr[R]>=arr[pivot]){
                R--;
            }
            swap(arr,L,R);
        }
        //pivot=fromLeft;
        swap(arr,pivot,L);
        return L;
    }

    /*----------------------------------上面自己实现的代码------------------------------------------*/

    public static int partition(int[] arr,int L,int R){
        if(L>R){
            return -1;
        }
        if(L==R){
            return L;
        }
        int lessEqual=L-1;
        int index=L;
        while (index<R){
            if (arr[index] <= arr[R]) {
                swap(arr,index,++lessEqual);
            }
            index++;
        }
        swap(arr,++lessEqual,R);
        return lessEqual;
    }

    public static int[] netherlandsFlag(int[] arr,int L,int R){
        if(L>R){
            return new int[]{-1,-1};
        }
        if(L==R){
            return new int[]{L,R};
        }
        int less=L-1;
        int more=R;
        int index=L;
        while (index<more){
            if(arr[index] == arr[R]){
                index++;
            }else if(arr[index] < arr[R]){
                swap(arr,index++,++less);
            }else{
                swap(arr,index,--more);
            }
        }
        swap(arr,more,R);
        return new int[]{less+1,more};
    }

    public static void quickSort2(int[] arr){
        if(arr==null || arr.length<2){
            return;
        }
        process1(arr,0,arr.length-1);
    }

    private static void process1(int[] arr, int L, int R) {
        if(L>=R){
            return;
        }
        int M=partition(arr,L,R);
        process1(arr,L,M-1);
        process1(arr,M+1,R);
    }

    public static void quickSort3(int[] arr){
        if(arr==null || arr.length<2){
            return;
        }
        process2(arr,0,arr.length-1);
    }
    private static void process2(int[] arr, int L, int R) {
        if(L>=R){
            return;
        }
        int[] equalArea=netherlandsFlag(arr,L,R);
        process2(arr,L,equalArea[0]-1);
        process2(arr,equalArea[1]+1,R);
    }

    public static void quickSort4(int[] arr){
        if(arr==null || arr.length<2){
            return;
        }
        process3(arr,0,arr.length-1);
    }
    private static void process3(int[] arr, int L, int R) {
        if(L>=R){
            return;
        }
        swap(arr,L+(int)(Math.random() * (R-L+1)),R);
        int[] equalArea=netherlandsFlag(arr,L,R);
        process3(arr,L,equalArea[0]-1);
        process3(arr,equalArea[1]+1,R);
    }
    private static void swap(int[] arr, int index1, int index2) {
        int temp=arr[index1];
        arr[index1]=arr[index2];
        arr[index2]=temp;
    }

    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr={5,3,1,4,2,6,7,5,8,6,9,3};
        printArr(arr);
        //quickSort1(arr);
        //quickSort2(arr);
        //quickSort3(arr);
        quickSort4(arr);
        printArr(arr);
    }
}
