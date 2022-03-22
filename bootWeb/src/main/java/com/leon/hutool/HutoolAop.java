package com.leon.hutool;

import cn.hutool.aop.ProxyUtil;
import cn.hutool.aop.aspects.TimeIntervalAspect;
import com.leon.service.Animal;
import com.leon.service.impl.Cat;
import com.leon.service.impl.Dog;

public class HutoolAop {
    public static void main(String[] args) {
        Animal proxy = ProxyUtil.proxy(new Cat(), TimeIntervalAspect.class);
        proxy.eat();
        System.out.println(String.format("我的位置%s,我的位置%s,我的位置%s",1,2,3));
        System.out.printf("我的位置%s,我的位置%s,我的位置%s",1,2,3);
        Dog proxy2 = ProxyUtil.proxy(new Dog(), TimeIntervalAspect.class);
        proxy2.eat();
    }
}
