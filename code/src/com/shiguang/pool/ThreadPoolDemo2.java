package com.shiguang.pool;

import java.util.concurrent.*;

/**
 * Created By Shiguang On 2024/12/10 16:48
 * 自定义线程池
 */
public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());


        try {
            for (int i = 1; i <= 10 ; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭线程池
            threadPool.shutdown();
        }
    }
}
