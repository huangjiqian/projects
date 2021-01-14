package com.consumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Value("${config.info}")
    String info;

    @GetMapping
    public String config(){
        return info;
    }
}
