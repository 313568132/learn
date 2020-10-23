package com.leon.springl.service;

public class AnimalService {
    public String name;

    public void setName(String name) {
        this.name = name;
    }

    public void say(){
        System.out.println("This animal name is " + name);
    }

    public void init(){
        System.out.println("AnimalService init()......");
    }

    public void destroy(){
        System.out.println("AnimalService destroy()......");
    }
}
