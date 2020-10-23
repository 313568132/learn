package com.leon.springl;

import com.leon.springl.config.ConfigUser;
import com.leon.springl.service.AnimalService;
import com.leon.springl.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigWithImportApplication {

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
        UserService userService = context.getBean(UserService.class);
        userService.say();
        AnimalService animalService = context.getBean(AnimalService.class);
        animalService.say();
    }
}
