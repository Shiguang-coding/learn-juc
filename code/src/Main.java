/**
 * Created By Shiguang On 2024/12/9 12:14
 */
public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 是否为守护线程: " + Thread.currentThread().isDaemon());
            while (true) {

            }
        }, "myThread");

        // 设置为守护线程,在线程启动之前设置
        thread.setDaemon(true);

        thread.start();

        System.out.println(Thread.currentThread().getName() + " over");

    }
}
