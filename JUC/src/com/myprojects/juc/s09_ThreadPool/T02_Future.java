package com.myprojects.juc.s09_ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 与Callable不同的是，返回值自己存储，实现RunnableFuture-实现Runnable,可以用Thread来执行
 */
public class T02_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task=new FutureTask<String>(()->{
            return "Hello FutureTask";
        });

        new Thread(task).start();
        System.out.println(task.get());
    }
}
