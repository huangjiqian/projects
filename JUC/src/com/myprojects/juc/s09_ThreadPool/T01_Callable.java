package com.myprojects.juc.s09_ThreadPool;

import java.util.concurrent.*;

/**
 * Callable->Runnable+returnValue
 * Future->存储线程执行的将来产生的值
 */
public class T01_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable=new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello Callable";
            }
        };

        ExecutorService service= Executors.newCachedThreadPool();
        Future<String> future=service.submit(callable);//异步，将callable扔进线程池就不管了
        System.out.println(future.get());//阻塞
        service.shutdown();
    }
}
