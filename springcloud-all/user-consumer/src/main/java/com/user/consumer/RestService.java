package com.user.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(defaultFallback = "back")
    public String alive(){

        String url ="http://provider/User/alive";
        System.out.println(url);
        String str = restTemplate.getForObject(url, String.class);
        System.out.println(str);
        return str;
    }

    public String back(){
        return "请求失败";
    }
}
