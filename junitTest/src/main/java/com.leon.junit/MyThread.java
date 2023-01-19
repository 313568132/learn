package com.leon.junit;


import java.util.concurrent.TimeUnit;

public class MyThread {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        new Thread(() -> {
            try {
                myThread.method();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                myThread.method();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public synchronized void method() throws InterruptedException {
        System.out.println("init");

        TimeUnit.SECONDS.sleep(2);

        System.out.println("end");
    }

}
