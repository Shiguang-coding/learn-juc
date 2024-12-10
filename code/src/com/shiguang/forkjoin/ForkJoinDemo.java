package com.shiguang.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created By Shiguang On 2024/12/10 17:28
 */

class MyTask extends RecursiveTask<Integer> {
    // 拆分差值不能超过10
    public static final int VALUE = 10;

    private int begin; // 拆分的开始值
    private int end; // 拆分的结束值
    private int result; // 结果

    //有参构造
    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }


    //拆分和合并
    @Override
    protected Integer compute() {
        // 两个数值相加是否大于10
        if (end - begin <= VALUE) {
            //相加操作
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        } else {// 进一步拆分
            // 获取中间值
            int middle = (begin + end) / 2;
            // 拆分左边
            MyTask t1 = new MyTask(begin, middle);
            // 拆分右边
            MyTask t2 = new MyTask(middle + 1, end);
            // 合并
            t1.fork();
            t2.fork();
            // 合并结果
            result = t1.join() + t2.join();
        }
        return result;
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask task = new MyTask(0, 100);
        // 创建分支合并池对象
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = pool.submit(task);

        // 获取最终合并后的结果
        Integer result = forkJoinTask.get();

        System.out.println(result);

        // 关闭分支合并池
        pool.shutdown();

    }

}
