package com.myprojects.juc.s08_Container.Collection.Queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 适用于任务调度，执行定时任务
 */
public class T03_DelayQueue {
    static DelayQueue<MyTask> tasks=new DelayQueue<MyTask>();
    public static void main(String[] args) {
        long now=System.currentTimeMillis();
        MyTask task1=new MyTask("task1",now+1000);
        MyTask task2=new MyTask("task2",now+1100);
        MyTask task3=new MyTask("task3",now+500);
        MyTask task4=new MyTask("task4",now+600);
        MyTask task5=new MyTask("task5",now+400);
        MyTask task6=new MyTask("task6",now+800);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
        tasks.add(task5);
        tasks.add(task6);

        for (int i = 0; i < 6; i++) {
            try {
                System.out.println(tasks.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyTask implements Delayed {
        String name;
        long runningTime;

        public MyTask(String name,long runningTime){
            this.name=name;
            this.runningTime=runningTime;
        }
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS)<o.getDelay(TimeUnit.MILLISECONDS))
                return -1;
            else if(this.getDelay(TimeUnit.MILLISECONDS)>o.getDelay(TimeUnit.MILLISECONDS))
                return 1;
            else
                return 0;
        }

        @Override
        public String toString() {
            return name + " " +runningTime;
        }
    }
}
