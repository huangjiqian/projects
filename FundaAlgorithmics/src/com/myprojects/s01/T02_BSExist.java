package com.myprojects.s01;

public class T02_BSExist {

    public static boolean isExist(int[] sortedArr,int num){
        if(sortedArr==null || sortedArr.length==0) return false;
        int L=0;
        int R=sortedArr.length-1;
        int mid=0;
        if(sortedArr[L]==num) return true;
        if(sortedArr[R]==num) return true;
        while (L < R){
            mid=L + ((R-L)>>1); // mid=(L+R)/2;
            if(sortedArr[mid]==num){
                return true;
            }else if(sortedArr[mid] > num){
                R=mid-1;
            }else{
                L=mid+1;
            }
        }
        return sortedArr[L]==num;
    }
}
