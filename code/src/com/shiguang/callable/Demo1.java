package com.shiguang.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created By Shiguang On 2024/12/10 9:34
 * 比较Runnable和Callable的区别
 */
class MyThread1 implements Runnable {
    @Override
    public void run() {

    }
}


class MyThread2 implements Callable {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" come in callable");
        return 200;
    }
}

public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        new Thread(new MyThread1(), "A").start();

        // 不能直接通过new Thread 调用call方法，
        // 需要借助FutureTask,FutureTask是Runnable的实现类，其构造可以接受Callable的实现类
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread2());

        //lambda表达式
        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName()+" come in callable");
            return 1024;
        });

        new Thread(futureTask2, "lucy").start();
        new Thread(futureTask1, "mary").start();

//        while (!futureTask2.isDone()) {
//            // 等待callable执行完成
//            System.out.println("waiting...");
//        }

        System.out.println(futureTask2.get());

        // 再次调用执行结果，会直接返回缓存的结果，不会重新调用callable
//        System.out.println(futureTask2.get());

        System.out.println(futureTask1.get());

        System.out.println(Thread.currentThread().getName()+" come in main over");
    }
}
