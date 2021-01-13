package com.myprojects.juc.s09_ThreadPool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class T10_ForkJoinPool {

    static int[] nums=new int[1000_0000];
    static final int MAX_VALUE=500000;
    static Random r=new Random();
    static List<Integer> list=new ArrayList<>();
    static long total=0L;
    static {
        for (int i = 0; i < nums.length; i++) {
            int num=r.nextInt(nums.length);
            nums[i]= num;
            list.add(num);
        }
        //list.parallelStream().forEach(v->add(v));
        //System.out.println("---" + total); //stream api
    }
    public static boolean add(int num){
        total+=num;
        return true;
    }
    //计算数组总和
    static class AddTask extends RecursiveAction{

        int start,end;
        static long sum=0L;
        public AddTask(int start,int end){
            this.start=start;
            this.end=end;
        }

        @Override
        protected void compute() {
            if(end-start<=MAX_VALUE){

                for (int i = start; i < end; i++) {
                    sum+=nums[i];
                }
                System.out.println("from:"+start+" end:"+end+" sum:"+sum);
                return;
            }else{
                int middle=start+(end-start)/2;
                AddTask task1=new AddTask(start,middle);
                AddTask task2=new AddTask(middle+1,end);
                task1.fork();
                task2.fork();
            }
        }
    }

    static class AddTaskReturn extends RecursiveTask<Long>{
        int start,end;

        public AddTaskReturn(int start,int end){
            this.start=start;
            this.end=end;
        }

        @Override
        protected Long compute() {
            if(end-start<=MAX_VALUE){
                long sum=0L;
                for(int i=start;i<end;i++) sum+=nums[i];
                System.out.println("from:"+start+" end:"+end+" sum:"+sum);
                return sum;
            }
            int middle=start+(end-start)/2;
            AddTaskReturn task1=new AddTaskReturn(start,middle);
            AddTaskReturn task2=new AddTaskReturn(middle+1,end);
            task1.fork();
            task2.fork();
            return task1.join()+task2.join();
        }
    }

    public static void main(String[] args) throws IOException {
        /*ForkJoinPool fjp=new ForkJoinPool();
        AddTask task=new AddTask(0,nums.length);
        fjp.execute(task);*/

        ForkJoinPool fjp=new ForkJoinPool();
        AddTaskReturn task=new AddTaskReturn(0,nums.length);
        fjp.execute(task);
        long sum=task.join();
        System.out.println(sum);
        //System.in.read();
    }
}

