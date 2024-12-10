package com.shiguang.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created By Shiguang On 2024/12/10 15:19
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

        // 第一组
//        System.out.println(queue.add("a"));
//        System.out.println(queue.add("b"));
//        System.out.println(queue.add("c"));
////        System.out.println(queue.element());
//
////        System.out.println(queue.add("d")); // 超出容量，抛出异常 IllegalStateException
//
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
////        System.out.println(queue.remove()); // 队列为空时，抛出异常 NoSuchElementException


        // 第二组
//        System.out.println(queue.offer("a"));
//        System.out.println(queue.offer("b"));
//        System.out.println(queue.offer("c"));
//        System.out.println(queue.offer("d")); // 超出容量，返回 false
//
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll()); // 队列为空时，返回 null

        // 第三组
//        queue.put("a");
//        queue.put("b");
//        queue.put("c");
////        queue.put("d"); // 超出容量，阻塞
//
//        System.out.println(queue.take());
//        System.out.println(queue.take());
//        System.out.println(queue.take());
//        System.out.println(queue.take()); // 队列为空时，阻塞

        // 第四组
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
//        System.out.println(queue.offer("d",3L, TimeUnit.SECONDS)); // 超出容量，阻塞, 超时返回 false

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll(3L, TimeUnit.SECONDS)); // 队列为空时，阻塞, 超时返回 null


    }
}
