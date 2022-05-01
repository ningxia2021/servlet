package Thread;

/**
 * 哪个线程调用的Thread.currentThread()，将会获得哪个线程的实例，
 */
public class thread13 {
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(this.getName());
            }
        };
        t.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        这个操作是main线程中调用的，因此拿到的就是main线程中的实例
        System.out.println(Thread.currentThread().getName());
    }
}
