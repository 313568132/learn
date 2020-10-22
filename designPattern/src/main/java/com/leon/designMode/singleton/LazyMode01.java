package com.leon.designMode.singleton;

/**
 * @author ：leon xie
 * @date ：2020/8/1 20:39
 */
public class LazyMode01 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        System.out.println(instance == instance2);
    }
}

class Singleton{

    private Singleton() {

    }
    private static Singleton singleton;

    public static Singleton getInstance(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }
}