package com.leon.springl;

import com.leon.springl.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlApplication {
    /**
     * 测试:
     *  init
     *  destroy
     *  processer(类初始化: 前置处理器, 后置处理器)
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService = (UserService)context.getBean("userService");
        userService.say();

        /*AbstractApplicationContext context2 = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService2 = (UserService)context2.getBean("userService");
        userService2.say();
        context2.registerShutdownHook();*/
    }
}
