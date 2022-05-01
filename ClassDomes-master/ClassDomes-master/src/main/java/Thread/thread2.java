package Thread;

/**
 * 多线程编程的万恶之源--抢占式执行！
 * 这里main线程和t线程都是休眠1s就打印，但是先后顺序并不是完全固定的，这就是因为线程是抢占式执行的。
 */
public class thread2 {
    /**
     * 采用继承Thread类的方式创建线程
     * 并实现 并发执行 的过程
     * @param args
     */
//    一个进程中至少会有一个线程，
//    在一个java进程中，至少会有一个调用main方法的线程，这个线程不是手动搞出来的，而是系统自动生成的
//    这时自己创建的t线程和系统创建的main线程，就是并发执行的
    public static void main(String[] args) {
        Thread t = new MyThread2();
        t.start();
        while (true){
            System.out.println("这是系统自动生成的main线程");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        while (true){
            System.out.println("这是自己创建的t线程");
            try {
//                强制让线程进入阻塞状态。
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
