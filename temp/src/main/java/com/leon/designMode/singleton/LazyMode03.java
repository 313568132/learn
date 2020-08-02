package com.leon.designMode.singleton;

/**
 * @author ：leon xie
 * @date ：2020/8/1 20:39
 */
public class LazyMode03 {
    public static void main(String[] args) {
        Singleton03 instance = Singleton03.getInstance();
        Singleton03 instance2 = Singleton03.getInstance();

        System.out.println(instance == instance2);
    }
}

class Singleton03{

    private Singleton03() {

    }
    private static Singleton03 singleton;

    public static Singleton03 getInstance(){
        if(singleton == null){
            synchronized (Singleton03.class){
                singleton = new Singleton03();
            }
        }
        return singleton;
    }
}