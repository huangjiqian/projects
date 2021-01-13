package com.user.consumer;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserProviderBack implements UserConsumerService {
    @Override
    public String alive() {

        return "降级了";
    }

    @Override
    public Map<Integer, String> getById(Integer id) {
        return null;
    }
}
