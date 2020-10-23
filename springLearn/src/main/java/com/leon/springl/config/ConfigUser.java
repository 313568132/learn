package com.leon.springl.config;

import com.leon.springl.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import(ConfigAnimal.class)
public class ConfigUser {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public UserService userService(){
        UserService userService = new UserService();
        userService.setName("config user name");
        return userService;
    }
}
