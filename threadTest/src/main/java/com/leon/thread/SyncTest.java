package com.leon.thread;

import sun.misc.Unsafe;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SyncTest {

    // synchronized关键字在静态方法上，锁为当前Class对象
    public static synchronized void method(){
        System.out.println("线程开始: " + Thread.currentThread().getName() + ", 状态: " + Thread.currentThread().getState());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程结束: " + Thread.currentThread().getName() + ", 状态: " + Thread.currentThread().getState());
    }

    // synchronized关键字在实例方法上，锁为当前实例
    public synchronized void method2(){
        System.out.println("线程开始: " + Thread.currentThread().getName() + ", 状态: " + Thread.currentThread().getState());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程结束: " + Thread.currentThread().getName() + ", 状态: " + Thread.currentThread().getState());
    }

    public synchronized void method3(){
        System.out.println("线程开始: " + Thread.currentThread().getName() + ", 状态: " + Thread.currentThread().getState());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程结束: " + Thread.currentThread().getName() + ", 状态: " + Thread.currentThread().getState());
    }
    static int size;
    public static void main(String[] args) {

        SyncTest syncTest = new SyncTest();
        new Thread(()->{
            syncTest.method2();
        }).start();
        new Thread(()->{
            syncTest.method3();
        }).start();
        System.out.println(size+1);
        ReentrantLock lock = new ReentrantLock();
        // Unsafe
        // List

//        SyncTest syncTest = new SyncTest();
//        new Thread(()->{
//            syncTest.method2();
//        }).start();
//        new Thread(()->{
//            syncTest.method2();
//        }).start();


//        new Thread(()->{
//            new SyncTest().method2();
//        }).start();
//        new Thread(()->{
//            new SyncTest().method2();
//        }).start();


//        new Thread(()->{
//            SyncTest.method();
//        }).start();
//        new Thread(()->{
//            SyncTest.method();
//        }).start();
    }
}
