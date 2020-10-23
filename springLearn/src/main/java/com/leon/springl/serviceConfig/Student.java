package com.leon.springl.serviceConfig;

public class Student {

    public String name;

    public Integer age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void say() {
        System.out.println("name: "+ name);
        System.out.println("age: "+ age);
    }
}
