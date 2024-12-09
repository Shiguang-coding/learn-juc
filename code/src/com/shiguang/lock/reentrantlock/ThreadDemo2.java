package com.shiguang.lock.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created By Shiguang On 2024/12/9 15:57
 */


// 第一步 创建资源类，定义属性和操作方法
class Share {
    // 初始值
    private int number = 0;

    // 创建Lock
    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    // +1 的方法
    public void incr() throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            // 判断
            while (number != 0) {
                condition.await();
            }

            // 干活
            number++;

            System.out.println(Thread.currentThread().getName() + " : " + number);

            // 通知
            condition.signalAll();
        } finally {
            // 解锁
            lock.unlock();

        }
    }

    // -1 的方法
    public void decr() throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            // 判断
            while (number != 1) {
                condition.await();
            }

            // 干活
            number--;
            System.out.println(Thread.currentThread().getName() + " : " + number);

            // 通知
            condition.signalAll();

        } finally {
            // 解锁
            lock.unlock();
        }
    }
}


public class ThreadDemo2 {

    public static void main(String[] args) {
        // 创建 Share 对象
        Share share = new Share();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

    }

}
