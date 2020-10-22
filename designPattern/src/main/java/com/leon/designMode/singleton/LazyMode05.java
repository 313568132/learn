package com.leon.designMode.singleton;

/**
 * @author ：leon xie
 * @date ：2020/8/1 20:39
 */
public class LazyMode05 {
    public static void main(String[] args) {
        Singleton04 instance = Singleton04.getInstance();
        Singleton04 instance2 = Singleton04.getInstance();

        System.out.println("静态内部类");
        System.out.println(instance == instance2);
    }
}

class Singleton05{

    private Singleton05() {

    }

    public static class SingletonInstance{
        public static Singleton05 INSTANCE = new Singleton05();
    }

    public static Singleton05 getInstance(){
        return SingletonInstance.INSTANCE;
    }
}