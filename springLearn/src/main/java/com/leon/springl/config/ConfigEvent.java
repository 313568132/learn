package com.leon.springl.config;

import com.leon.springl.listener.MyStartListener;
import com.leon.springl.listener.MyStopListener;
import com.leon.springl.service.AnimalService;
import com.leon.springl.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigEvent {

    @Bean
    public MyStartListener myStartListener(){
        return new MyStartListener();
    }

    @Bean
    public MyStopListener myStopListener(){
        return new MyStopListener();
    }

    @Bean
    public UserService userService(){
        UserService userService = new UserService();
        userService.setName("config user name");
        return userService;
    }

    @Bean
    public AnimalService animalService(){
        AnimalService animalService = new AnimalService();
        animalService.setName("config animal name");
        return animalService;
    }
}
