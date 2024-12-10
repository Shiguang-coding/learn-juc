package com.shiguang.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * Created By Shiguang On 2024/12/10 10:43
 */
public class CyclicBarrierDemo {
    public static final int NUMBER = 7;

    public static void main(String[] args) {
        // 集齐7颗龙珠后方可召唤神龙
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println("龙珠已集齐,召唤神龙");
        });

        // 集齐7颗龙珠的过程
        for (int i = 1; i <= 7 ; i++) {
           new Thread(() -> {
               System.out.println(Thread.currentThread().getName() + " 星龙被收集到了");
               try {
                   cyclicBarrier.await();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }, String.valueOf(i)).start();
        }
    }
}
