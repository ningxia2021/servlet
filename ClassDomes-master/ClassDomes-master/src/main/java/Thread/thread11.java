package Thread;

/**
 * 中断线程
 * 2.通过Thread内置的标志位来进行判定
 *      这其中可以通过静态的方法：Thread.interrupted()
 *      也可以通过实例的方法Thread.currentThread().isInterrupted()  其中，currentThread()可以获得当前线程的实例
 */
public class thread11 {

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("线程被中断！！");
                    e.printStackTrace();
                    break;
                }
            }
        });
        t.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        /**
         * 通过在主线程中调用 interrupt 来中断t线程     t.interrupt()  的意思就是让t线程被中断
         *  调用这个方法可能产生两种情况：
         *  1.如果t线程处在就绪状态，就是设置线程的标志位为true；
         *   2.如果t线程处在阻塞状态（sleep时），就会触发异常。
         *      中断是希望能够立即产生效果，如果线程已经是阻塞状态下，设置的标志位就不能起到及时唤醒的效果
         *       显然，当前代码中t线程绝大部分时间都在sleep，
         *       调用这个interrupt方法就会让sleep触发一个异常，从而导致线程从阻塞状态下被唤醒，
         */

        t.interrupt();

    }
}
