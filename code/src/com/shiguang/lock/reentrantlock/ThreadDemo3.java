package com.shiguang.lock.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created By Shiguang On 2024/12/9 16:17
 */
// 第一步 创建资源类，定义属性和操作方法

class shareResource {
    // 定义标志位
    private int flag = 1; // 1 A ,2 B,3 C

    // 创建Lock锁
    private Lock lock = new ReentrantLock();

    // 创建三个Condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    // 打印5次,参数第几轮
    public void print5(int loop) throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            // 判断
            while (flag != 1) {
                c1.await();
            }
            // 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i + " , " + "轮数:" + loop);
            }
            // 通知
            flag = 2;
            c2.signal();
        } finally {
            // 解锁
            lock.unlock();
        }
    }

    // 打印10次,参数第几轮
    public void print10(int loop) throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            // 判断
            while (flag != 2) {
                c2.await();
            }
            // 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i + " , " + "轮数:" + loop);
            }
            // 通知
            flag = 3;
            c3.signal();
        } finally {
            // 解锁
            lock.unlock();
        }
    }

    // 打印15次,参数第几轮
    public void print15(int loop) throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            // 判断
            while (flag != 3) {
                c3.await();
            }
            // 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i + " , " + "轮数:" + loop);
            }
            // 通知
            flag = 1;
            c1.signal();
        } finally {
            // 解锁
            lock.unlock();
        }
    }
}


public class ThreadDemo3 {
    public static void main(String[] args) {
        // 创建 Share 对象
        shareResource shareResource = new shareResource();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
    }

}
