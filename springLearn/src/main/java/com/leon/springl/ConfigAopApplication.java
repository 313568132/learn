package com.leon.springl;

import com.leon.springl.config.ConfigAop;
import com.leon.springl.service.AnimalService;
import com.leon.springl.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigAopApplication {
    /**
     * Aop test:
     *  ConfigAop中需要开启@EnableAspectJAutoProxy
     *
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigAop.class);
        UserService userService = context.getBean(UserService.class);
        userService.say();
        AnimalService animalService = context.getBean(AnimalService.class);
        animalService.say();
    }
}
