package com.user.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserConsumerService consumerService;

    @Autowired
    RestService restService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/alive")
    public String alive(){
        System.out.println("执行了");
        return consumerService.alive();
    }

    @GetMapping("/alive2")
    public String alive2(){
        return restService.alive();
    }

    @GetMapping("/alive3")
    public String alive3(){
        String url = "http://provider/User/alive";
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    @GetMapping("/getById")
    public Map<Integer, String> getById(Integer id){

        Map<Integer, String> map = consumerService.getById(id);
        return map;
    }
}
