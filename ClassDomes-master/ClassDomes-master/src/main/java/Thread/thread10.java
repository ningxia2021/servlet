package Thread;

/**
 * 中断线程
 * 1.设置标志位 让线程停下来
 * 可以在其他线程中控制这个标志位来控制这个线程的结束
 * 但是这种写法还存在缺陷
 * 更好的做法是使用thread中内置的标志位
 */
public class thread10 {
    private static boolean isQuit = false;
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isQuit){
                    System.out.println("hello thread");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();

//        现在只有把这个isQuit设置为true，t线程中循环条件不满足，此时这个循环就推出了，run执行结束，线程也就结束了。
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isQuit = true;
        System.out.println("线程终止 ！");

    }
}
