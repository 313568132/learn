package com.leon.designMode.singleton;

/**
 * @author ：leon xie
 * @date ：2020/8/1 20:47
 */
public class HungryMode01 {
    public static void main(String[] args) {
        SingletonHungry instance = SingletonHungry.getInstance();
        SingletonHungry instance2 = SingletonHungry.getInstance();

        System.out.println(instance == instance2);
    }
}

class SingletonHungry{
    //私有化构造方法,不允许使用者使用new 创建该对象
    private SingletonHungry(){

    }

    private static final SingletonHungry singleton = new SingletonHungry();

    //创建getInstance方法,让使用者通过该方法获取对象
    public static SingletonHungry getInstance(){
        return singleton;
    }
}