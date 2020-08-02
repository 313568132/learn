package com.leon.designMode.singleton;

/**
 * @author ：leon xie
 * @date ：2020/8/1 20:55
 */
public class HungryMode02 {
    public static void main(String[] args) {
        SingletonHungry02 instance = SingletonHungry02.getInstance();
        SingletonHungry02 instance2 = SingletonHungry02.getInstance();

        System.out.println(instance == instance2);
    }
}

class SingletonHungry02{
    private SingletonHungry02() {
    }
    private static final SingletonHungry02 single;

    static {
        single = new SingletonHungry02();
    }

    public static SingletonHungry02 getInstance(){
        return single;
    }
}