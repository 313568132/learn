package com.leon.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.leon.api.result.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/HelloWorld")
public class HelloWorld {

    @GetMapping("/sayHello")
    @ResponseResult
    public Integer sayHello(String name){
        System.out.println("haha");
        return 4;
    }

    @Value("${logging.level.root}")
    String property;


    @GetMapping("/helloAnno")
    @ResponseResult
    public String helloAnno(String name) throws InterruptedException {
        while (true){
            TimeUnit.SECONDS.sleep(2);
            System.out.println(property);
            if(3 > 4){
                break;
            }
        }
        return "hello world! good nice: " + name;
    }

    public static void main(String[] args) throws InterruptedException {
        Config config = ConfigService.getAppConfig();
        while (true){
            TimeUnit.SECONDS.sleep(2);
            String property = config.getProperty("logging.level.root", null);
            System.out.println(property);
        }
    }
}
