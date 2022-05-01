package Thread;

/**
 * 2022.03.21 记录多线程学习（一）下
 */
public class thread9 {
    public static void main(String[] args) {

         Thread t = new Thread(()->{
             while(true){
                 System.out.println("hello thread");
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         });
//            t.start();
        /**
         * 主要区分这里start和run的区别；
         * start 创建了一个线程，t和main并发执行
         * run 是运行一个方法，将会陷入循环
         */
        t.run();
        while(true){
            System.out.println("hello main");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

