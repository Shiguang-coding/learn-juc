package com.shiguang.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created By Shiguang On 2024/12/10 10:55
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 6辆车，停3个车位

        // 创建Semaphore对象,设置许可数量
        Semaphore semaphore = new Semaphore(3);

        // 模拟6辆车
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    // 抢占
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 抢到了车位");

                    // 设置随机停车时间
//                    Thread.sleep((long) (Math.random() * 1000));
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    System.out.println(Thread.currentThread().getName() + " >>>离开了车位");

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 释放
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }

    }
}
