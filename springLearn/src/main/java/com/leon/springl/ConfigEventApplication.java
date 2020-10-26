package com.leon.springl;

import com.leon.springl.config.ConfigEvent;
import com.leon.springl.config.ConfigUser;
import com.leon.springl.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigEventApplication {

    /**
     * 包含event事件
     * @param args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ConfigEvent.class);
        context.start();
        UserService userService = context.getBean(UserService.class);
        userService.say();
        context.stop();
        userService.say();
    }
}
