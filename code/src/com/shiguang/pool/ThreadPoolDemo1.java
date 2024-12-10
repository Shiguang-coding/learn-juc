package com.shiguang.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created By Shiguang On 2024/12/10 16:13
 * 线程池三种常用分类
 */
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        // 第一种: 一池N线程
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);

        // 第二种：一池一线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();

        // 第三种: 一池可扩容线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        try {
            for (int i = 1; i <= 10 ; i++) {
                threadPool3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭线程池
            threadPool3.shutdown();
        }



    }
}
