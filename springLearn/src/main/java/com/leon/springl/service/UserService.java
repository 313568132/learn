package com.leon.springl.service;

public class UserService {

    public String name;

    public void setName(String name) {
        this.name = name;
    }

    public void say(){
        System.out.println("My name is " + name);
    }

    public void init(){
        System.out.println("UserService init()......");
    }

    public void destroy(){
        System.out.println("UserService destroy()......");
    }
}
