package com.shiguang.lock.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created By Shiguang On 2024/12/10 13:33
 */

//资源类
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    //写操作
    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在写入：" + key);
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写入完成：" + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }

    }

    //读操作
    public Object get(String key) {
        rwLock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + " 正在读取>>：" + key);
            TimeUnit.MILLISECONDS.sleep(300);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取完成>>：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }

        return result;
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        // 存数据
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                myCache.put(Thread.currentThread().getName(), Thread.currentThread().getName());
            }, String.valueOf(i)).start();

        }

        // 取数据
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                myCache.get(Thread.currentThread().getName());
            }, String.valueOf(i)).start();
        }
    }
}
