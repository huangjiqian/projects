package com.user.consumer;

import com.sun.org.apache.xalan.internal.xsltc.runtime.InternalRuntimeError;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import java.net.ConnectException;
import java.util.Map;

@Component
public class UserProviderBackFactory implements FallbackFactory<UserConsumerService> {
    @Override
    public UserConsumerService create(Throwable cause) {
        return new UserConsumerService() {
            @Override
            public String alive() {
                System.out.println(cause);
                if (cause instanceof ConnectException) {
                    return "客户端连接服务器失败";
                }else if (cause instanceof InternalRuntimeError){
                    return "服务器端运行错误";
                }else if (cause instanceof HttpServerErrorException.InternalServerError){
                    return "远程调用出现错误";
                }else {
                    return "降级了";
                }
            }

            @Override
            public Map<Integer, String> getById(Integer id) {
                return null;
            }
        };
    }
}
