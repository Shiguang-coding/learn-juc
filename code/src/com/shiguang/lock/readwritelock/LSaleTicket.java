package com.shiguang.lock.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created By Shiguang On 2024/12/9 14:09
 */

// 第一步 创建资源类，定义属性和操作方法
class LTicket {
    // 票数
    private int number = 30;

    // 创建可重用读写锁
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    // 卖票操作
    public void sale() {
        // 获取写锁
        lock.writeLock().lock();
        try {
            // 判断是否有票
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + " 卖出了第 " + (number--) + " 张票, 剩余: " + number);
            }
        } finally {
            // 释放写锁
            lock.writeLock().unlock();
        }
    }
}

public class LSaleTicket {

    // 第二步 创建多个线程，调用资源类的操作方法
    public static void main(String[] args) {
        LTicket ticket = new LTicket();
        // 创建三个线程
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread(() -> {

            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();
    }


}
