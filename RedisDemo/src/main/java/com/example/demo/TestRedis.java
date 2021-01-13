package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TestRedis {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void test(){
//        redisTemplate.opsForValue().set("name","henry");
//        System.out.println(redisTemplate.opsForValue().get("name"));

//        stringRedisTemplate.opsForValue().set("name","henry");
//        System.out.println(stringRedisTemplate.opsForValue().get("name"));

//        RedisConnection connection=redisTemplate.getConnectionFactory().getConnection();
//        connection.set("name".getBytes(),"henry".getBytes());
//        System.out.println(new String(connection.get("name".getBytes())));
//
//        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
//        hash.put("henry","name","huang");
//        hash.put("henry","age","22");
//
//        System.out.println(hash.entries("henry"));



        Person p=new Person();
        p.setName("bob");
        p.setAge(16);


        //true/false:是否展开
        Jackson2HashMapper hashMapper=new Jackson2HashMapper(objectMapper,false);
        Map<String, Object> hash = hashMapper.toHash(p);
        redisTemplate.opsForHash().putAll(p,hash);
    }
}
