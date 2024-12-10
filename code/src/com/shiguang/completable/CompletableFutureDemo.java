package com.shiguang.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created By Shiguang On 2024/12/10 17:59
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 异步调用，无返回值
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            // 执行异步任务
            System.out.println(Thread.currentThread().getName() + " runAsync");
        });

        future.get();

        // 异步调用，有返回值
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            // 执行异步任务
            System.out.println(Thread.currentThread().getName() + " supplyAsync");

            // 模拟异常
            int i = 1 / 0;
            return 1;
        });

        future2.whenComplete((t, u) -> {
            // 处理异步任务的结果
            System.out.println("异步任务结果：" + t);
            System.out.println("异常信息：" + u);
        }).exceptionally((e) -> {
            // 处理异常
            System.out.println("异步任务异常：" + e.getMessage());
            return null;
        });

    }

}
