package com.leon.thread;

import java.util.concurrent.TimeUnit;

public class VolatileTest{
    public static void main(String[] args) {
        VolatileTest test = new VolatileTest();
        Thread thread = new Thread(()->{
            try {
                test.write();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(()->{
            try {
                // VolatileTest test = new VolatileTest();
                test.reade();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        thread2.start();
    }

    int anInt = 0;
    boolean flags = false;

    public void write() throws InterruptedException {
        anInt = 1;
        flags = true;
        TimeUnit.SECONDS.sleep(2);
    }
    public void reade() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("执行了!");
        if(flags){
            System.out.println("anInt: " + anInt);
        }
    }
}
