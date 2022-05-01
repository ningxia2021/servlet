package Thread;
/**
 * 线程的状态
 * 1.NEW 2.RUNNABLE 3.TERMINAL 4.TIME_WAITING 5.BLOCKED 6.WAITING
 */
public class thread14 {
    public static void main(String[] args) {
//        创建 线程t实例 这个过程是 NEW状态
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
//        调用start方法的一瞬间就是Runnable状态，如果方法中有sleep那状态就会转为time_waiting ，如果有锁 那就会blocked
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        当线程执行结束后，状态变为Terminal
        System.out.println(t.getState());
    }
}
