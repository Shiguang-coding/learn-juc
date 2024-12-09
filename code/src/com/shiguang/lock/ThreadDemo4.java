package com.shiguang.lock;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created By Shiguang On 2024/12/9 16:36
 * List集合线程不安全问题
 */
public class ThreadDemo4 {
    public static void main(String[] args) {
        // 使用ArrayList，出现线程不安全问题
//        List<String> list = new ArrayList<>();

        // 解决方案一: 使用Vector
//        List<String> list = new Vector<>();

        // 解决方案二：使用Collections.synchronizedList()
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

        // 解决方案三：使用CopyOnWriteArrayList
//        List<String> list = new CopyOnWriteArrayList<>();
//
//        for (int i = 0; i < 30; i++) {
//            new Thread(() -> {
//                try {
//                    list.add(UUID.randomUUID().toString().substring(0, 8));
//                    System.out.println(list);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    // 异常时终止程序, 0表示正常终止，非零值表示异常终止
//                    System.exit(1);
//                }
//            }, String.valueOf(i)).start();
//        }


        // 使用Set集合线程不安全问题
//        HashSet<String> set = new HashSet<>();

//        // 解决方案一: 使用CopyOnWriteArraySet
//        Set<String> set = new CopyOnWriteArraySet<>();
//
//        for (int i = 0; i < 30; i++) {
//            new Thread(() -> {
//                try {
//                    set.add(UUID.randomUUID().toString().substring(0, 8));
//                    System.out.println(set);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    // 异常时终止程序, 0表示正常终止，非零值表示异常终止
//                    System.exit(1);
//                }
//            }, String.valueOf(i)).start();
//        }

        // 使用Map集合线程不安全问题
//        Map<String, String> map = new HashMap<>();

        // 解决方案一: 使用ConcurrentHashMap
//        Map<String, String> map = new ConcurrentHashMap<>();

//        // 解决方案二: 使用Collections.synchronizedMap()
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                try {
                    map.put(key, UUID.randomUUID().toString().substring(0, 8));
                    System.out.println(map);
                } catch (Exception e) {
                    e.printStackTrace();
                    // 异常时终止程序, 0表示正常终止，非零值表示异常终止
                    System.exit(1);
                }
            }, String.valueOf(i)).start();

        }




    }
}
