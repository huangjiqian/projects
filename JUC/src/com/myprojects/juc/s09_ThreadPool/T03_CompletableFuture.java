package com.myprojects.juc.s09_ThreadPool;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 假设你能提供一个服务。这个服务查询各大电商网站同一类产品的价格并汇总展示
 */
public class T03_CompletableFuture {//各种任务的管理类
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        CompletableFuture<Double> futureTM=CompletableFuture.supplyAsync(()->priceOfTM());
        CompletableFuture<Double> futurePDD=CompletableFuture.supplyAsync(()->priceOfPDD());
        CompletableFuture<Double> futureJD=CompletableFuture.supplyAsync(()->priceOfJD());
        CompletableFuture.allOf(futureTM,futurePDD,futureJD).join();

        //price1.0
        /*CompletableFuture.supplyAsync(()->priceOfTM())
                .thenApply(String::valueOf)//输出获取的值之前添加属性
                .thenApply(str->"price"+str)//添加值
                .thenAccept(System.out::println);
        long end=System.currentTimeMillis();*/
        //System.out.println("completableFuture used "+(end-start));
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double priceOfTM(){
        delay();
        return 1.00;
    }
    private static double priceOfPDD(){
        delay();
        return 2.00;
    }
    private static double priceOfJD(){
        delay();
        return 3.00;
    }
    private static void delay(){
        int time=new Random().nextInt(3000);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after sleep "+time);
    }
}

