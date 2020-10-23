package com.leon.springl;

import com.leon.springl.config.ConfigUser;
import com.leon.springl.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigApplication {

    /**
     * @Configration注解:
     *  @bean
     *  init
     *  destroy
     *
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigUser.class);
        // UserService userService = (UserService)context.getBean("userService");
        UserService userService = context.getBean(UserService.class);
        userService.say();
    }
}
