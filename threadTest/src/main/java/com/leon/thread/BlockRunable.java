package com.leon.thread;

import java.util.concurrent.TimeUnit;

public class BlockRunable {



    public static void main(String[] args) throws InterruptedException {
        BlockRunable blockRunable = new BlockRunable();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                blockRunable.runMethod();
            }
        },"threadA");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                blockRunable.runMethod();
            }
        },"threadB");

        thread1.start();
        // TimeUnit.SECONDS.sleep(1);
        thread1.join();
        thread2.start();
        // TimeUnit.SECONDS.sleep(1);
        System.out.println("线程: " + thread2.getName() + ", 状态: " + thread2.getState());
        System.out.println("线程: " + thread1.getName() + ", 状态: " + thread1.getState());
        System.out.println("线程: " + thread2.getName() + ", 状态: " + thread2.getState());
    }

    public synchronized void runMethod(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
