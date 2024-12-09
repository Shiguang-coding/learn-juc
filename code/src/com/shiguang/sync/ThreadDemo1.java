package com.shiguang.sync;

/**
 * Created By Shiguang On 2024/12/9 15:01
 */
// 第一步 创建资源类，定义属性和操作方法
class Share {
    //初始值
    private int number = 0;

    // +1 的方法
    public synchronized void incr() throws InterruptedException {
        // 第二步：判断, 干活, 通知
        while (number != 0) {// 判断number值是否为0,不为0则等待
            // 等待
            this.wait();
        }
        // 干活
        number++;
        System.out.println(Thread.currentThread().getName() + " : " + number);
        // 通知其他线程
        this.notifyAll();
    }

    // -1 的方法
    public synchronized void decr() throws InterruptedException {
        // 第二步：判断, 干活, 通知
        while (number != 1) {// 判断number值是否为1,不为0则等待
            // 等待
            this.wait();
        }
        // 干活
        number--;
        System.out.println(Thread.currentThread().getName() + " : " + number);
        // 通知其他线程
        this.notifyAll();

    }
}


public class ThreadDemo1 {
    //第三步 创建多个线程，调用资源类的操作方法
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

        }, "D").start();

    }
}