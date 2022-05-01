package Thread;

/**
 * join（）
 * 控制线程先后顺序
 */
public class thread12 {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0 ;i < 5;i++){
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
        try {
            /**
             * 进入join就会阻塞，谁调用谁阻塞or谁等待，这里main调用 main等t执行完才可以继续执行
             * t.join();     相当于不见不散  必须要t线程run执行完才可以接着往下执行
             * t.join(3000); 相当于给等待加了一份时限，更加合理
             */
            t.join(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束");
    }
}
