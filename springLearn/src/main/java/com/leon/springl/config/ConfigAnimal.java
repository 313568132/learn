package com.leon.springl.config;

import com.leon.springl.service.AnimalService;
import com.leon.springl.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigAnimal {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public AnimalService animalService(){
        AnimalService animalService = new AnimalService();
        animalService.setName("config animal name");
        return animalService;
    }

}
