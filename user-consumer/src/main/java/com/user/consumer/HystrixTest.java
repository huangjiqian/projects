package com.user.consumer;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HystrixTest extends HystrixCommand {
    protected HystrixTest(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected Object run() throws Exception {
        System.out.println("执行逻辑");
        int i = 1/1;
        return "ok";
    }

    @Override
    protected Object getFallback() {
        return "getFallback";
    }

    public static void main(String[] args) {
        Future future = new HystrixTest(HystrixCommandGroupKey.Factory.asKey("ext")).queue();
        Object result = "";

        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("程序执行结果：" + result);
    }
}
