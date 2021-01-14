package com.user.provider;

import com.api.userapi.UserApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class UserController implements UserApi {

    @Value("${server.port}")
    String port;

    private AtomicInteger count = new AtomicInteger();

    @Override
    public String alive() {

//        try {
//            System.out.println("睡了");
//
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int i = count.incrementAndGet();

        System.out.println(port + "====第" + i + "次调用");
        //int j = 1/0;
        return "port:" + port;
    }

    @Override
    public Map<Integer, String> getById(Integer id) {

        return Collections.singletonMap(id, "henry");
    }
}
