package com.shiguang.sync;

import java.util.concurrent.TimeUnit;

/**
 * Created By Shiguang On 2024/12/9 17:51
 */

class Phone{
    public static synchronized void sendSMS() throws Exception{
        //停4秒
        TimeUnit.SECONDS.sleep(4);
        System.out.println(Thread.currentThread().getName() + "," + "发短信");
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName() + "," + "发邮件");
    }

    public void getHello(){
        System.out.println("hello");
    }
}

/**
 * 8种锁的总结：
 * 1 标准访问，先打印短信还是邮件
 *      A,发短信
 *      B,发邮件
 * 2 停4秒在短信方法内，先打印短后还是邮件
 *      A,发短信
 *      B,发邮件
 * 3 新增誉通heLLo方法还是先打短后还是hello
 *      hello
 *      A,发短信
 * 4 现在有两部手机，先打印短信还是邮件
 *      B,发邮件
 *      A,发短信
 * 5 两个静态同步方法，1部手机，先打印短层还是邮件
 *      A,发短信
 *      B,发邮件
 * 6 两个静态同步方法，2部手机，先打印短层还是邮件
 *      A,发短信
 *      B,发邮件
 * 7 1个静态同步方法，1个普通同步方法，1部手机，先打印短信后还是邮件
 *      B,发邮件
 *      A,发短信
 * 8 1个静态同步方法，1个普通同步方法，2部手机，先打印短信后还是邮件
 *      B,发邮件
 *      A,发短信
 */
public class Lock_8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
//                phone.sendEmail();
//                phone.getHello();
                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();

    }

}
