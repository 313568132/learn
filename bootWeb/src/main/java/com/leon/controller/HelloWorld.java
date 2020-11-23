package com.leon.controller;

import com.leon.api.result.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/HelloWorld")
public class HelloWorld {

    @GetMapping("/sayHello")
    @ResponseResult
    public Integer sayHello(String name){
        System.out.println("haha");
        return 4;
    }

    @GetMapping("/helloAnno")
    @ResponseResult
    public String helloAnno(String name){
        return "hello world! good nice: " + name;
    }
}
