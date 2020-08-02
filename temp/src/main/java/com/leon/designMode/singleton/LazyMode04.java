package com.leon.designMode.singleton;

/**
 * @author ：leon xie
 * @date ：2020/8/1 20:39
 */
public class LazyMode04 {
    public static void main(String[] args) {
        Singleton04 instance = Singleton04.getInstance();
        Singleton04 instance2 = Singleton04.getInstance();

        System.out.println(instance == instance2);
    }
}

class Singleton04{

    private Singleton04() {

    }
    //添加volatile关键字,使jvm能感知到对象的改变
    private static volatile Singleton04 singleton;

    public static Singleton04 getInstance(){
        if(singleton == null){
            synchronized (Singleton04.class){
                if(singleton == null){
                    singleton = new Singleton04();
                }
            }
        }
        return singleton;
    }
}