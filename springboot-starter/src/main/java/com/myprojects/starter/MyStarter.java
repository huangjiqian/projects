package com.myprojects.starter;

import com.myprojects.entity.Person;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "EnableAutoConfiguration", matchIfMissing = true)
public class MyStarter {

    static {
        System.out.println("myStarter init ...");
    }

    @Bean
    public Person person(){
        return new Person();
    }
}
