package com.api.userapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

//@RequestMapping("/user")
public interface UserApi {

    @GetMapping("/User/alive")
    String alive();

    @PostMapping("/User/getById")
    Map<Integer, String> getById(@RequestParam Integer id);
}
