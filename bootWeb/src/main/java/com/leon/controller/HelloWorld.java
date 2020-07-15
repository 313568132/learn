package com.leon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/HelloWorld")
public class HelloWorld {

    @GetMapping("/sayHello")
    public String sayHello(String name){
        return "hello world! good nice: " + name;
    }
}
