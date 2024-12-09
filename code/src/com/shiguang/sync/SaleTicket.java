package com.shiguang.sync;

/**
 * Created By Shiguang On 2024/12/9 13:52
 */

// 第一步 创建资源类，定义属性和操作方法
class Ticket {
    // 票数
    private int number = 30;
    // 卖票操作
    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + " 卖出了第 " + (number--) + " 张票, 剩余: " + number);
        }
    }
}
public class SaleTicket {
    // 第二步 创建多个线程，调用资源类的操作方法
    public static void main(String[] args) {
        // 创建 Ticket 对象
        Ticket ticket = new Ticket();

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
