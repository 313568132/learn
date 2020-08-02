package com.leon.designMode.singleton;

/**
 * @author ：leon xie
 * @date ：2020/8/1 20:39
 */
public class LazyMode02 {
    public static void main(String[] args) {
        Singleton02 instance = Singleton02.getInstance();
        Singleton02 instance2 = Singleton02.getInstance();

        System.out.println(instance == instance2);
    }
}

class Singleton02{

    private Singleton02() {

    }
    private static Singleton02 singleton;

    public static synchronized Singleton02 getInstance(){
        if(singleton == null){
            singleton = new Singleton02();
        }
        return singleton;
    }
}