package com.shiguang.juc;

import java.util.concurrent.CountDownLatch;

/**
 * Created By Shiguang On 2024/12/10 10:27
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 6个同学陆续离开教室后，班长才锁门

        // 创建CountDownLatch对象，设置初始值为6
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 号同学离开了教室");
                // 计数器减一
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }


        // 等待计数器归零
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + " 班长锁门了");
    }
}
