package com.leon.service.impl;

import com.leon.service.Animal;

public class Cat implements Animal {
    @Override
    public void eat() {
        System.out.println("猫吃吃吃吃吃吃.........鱼");
    }
}
