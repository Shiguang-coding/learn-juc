package com.shiguang.lock.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created By Shiguang On 2024/12/10 13:57
 * 演示读写锁降级
 */
public class Demo {
    public static void main(String[] args) {
        // 可重入读写锁对象
        ReentrantReadWriteLock wrLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = wrLock.readLock(); // 读锁
        ReentrantReadWriteLock.WriteLock writeLock = wrLock.writeLock(); // 写锁

        // 锁降级
        // 1. 获取写锁
        writeLock.lock();
        System.out.println("获得写锁");

        // 2. 获得读锁
        readLock.lock();
        System.out.println("获得读锁");

        // 3. 释放写锁
        writeLock.unlock();

        // 4. 释放读锁
        readLock.unlock();

    }
}
