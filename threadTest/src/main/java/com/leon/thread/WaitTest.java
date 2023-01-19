package com.leon.thread;

import java.util.concurrent.TimeUnit;

public class WaitTest implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        WaitTest waitTest = new WaitTest();
        Thread thread = new Thread(waitTest,"A");
        Thread thread2 = new Thread(waitTest,"b");
        thread.start();
        thread2.start();
    }

    public void run() {
        synchronized (this) {
            System.out.println("线程开始: " + Thread.currentThread().getName() + ", 状态: " + Thread.currentThread().getState());
            try {
                notifyAll();
                wait();
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程结束: " + Thread.currentThread().getName() + ", 状态: " + Thread.currentThread().getState());
            notifyAll();
        }
    }
}
