package com.leon.springl.serviceConfig;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class StudentService {

    @Autowired
    Student student;

    public void ssay(){
        student.say();
    }

    @PostConstruct
    public void init(){
        System.out.println("StudentService init()......");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("StudentService destroy()......");
    }
}
