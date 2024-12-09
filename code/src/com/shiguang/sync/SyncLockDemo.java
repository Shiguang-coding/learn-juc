package com.shiguang.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created By Shiguang On 2024/12/9 20:09
 * 可重用锁
 */
public class SyncLockDemo {

    public synchronized void add() {
        add();
    }

    public static void main(String[] args) {
//        Object obj = new Object();
//        new Thread(() -> {
//            synchronized (obj) {
//                System.out.println(Thread.currentThread().getName() + " 外层");
//
//                synchronized (obj) {
//                    System.out.println(Thread.currentThread().getName() + " 中层");
//
//                    synchronized (obj) {
//                        System.out.println(Thread.currentThread().getName() + " 内层");
//                    }
//                }
//            }
//        }, "t1").start();

//        new SyncLockDemo().add();


        // Lock演示可重用锁
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " 外层");

                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " 内层");
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }

        }, "t1").start();

        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
            } finally {
                lock.unlock();
            }

        }, "t2").start();
    }
}
