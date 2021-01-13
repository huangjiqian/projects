package com.myprojects.s01;

public class T06_EvenTimesOddTimes {

    //arr中，只有一种数，出现了奇数次
    public static void printOneNumEvenTimes(int[] arr){
        int eor=0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }
    //arr中，只有两种数，出现了奇数次
    public static void printTwoNumEvenTimes(int[] arr){
        int eor=0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        /**
         * 提取eor最右边的一个1
         * eor     =  0001 1100
         * ~eor    =  1110 0011
         * ~eor+1  =  1110 0100
         * rightOne=  0000 0100
         */
        int rightOne=eor & (~eor +1);
        int onlyOne=0;
        for (int i = 0; i < arr.length; i++) {
            if((rightOne & arr[i]) != 0){
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne + "," + (onlyOne^eor));
    }

    public static void main(String[] args) {
        int[] arr1= {1,1,1,2,2,3,3,4,5,5,4};
        printOneNumEvenTimes(arr1);
        int[] arr2= {1,1,1,2,2,2,3,3,4,5,5,4};
        printTwoNumEvenTimes(arr2);
    }
}
