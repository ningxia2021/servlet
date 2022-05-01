package Thread;
/**
 * wait 和 notify 基本的使用
 * tips：notifyall 可以唤醒所有的wait，这些wait会尝试重新获取锁，这个过程就会发生竞争。拿到锁的继续执行，剩下的线程就会就行等。
 */
public class thread18 {

    private static Object Locker = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(){
            @Override
            public void run() {
                synchronized (Locker) {
                    System.out.println("wait  前");
                    try {
                        Locker.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("wait  后");
                }
            }
        };

        t1.start();

        Thread.sleep(3000);

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Locker){
                    System.out.println("notify 前");
                    Locker.notify();
                    System.out.println("notify 后");
                }
            }
        });

        t2.start();
    }
}
