package Thread;

/**
 * 标记线程的名字
 */
public class thread8 {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            while(true){
                System.out.println("t1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"这是thread t1");
        t1.start();
        Thread t2 =new Thread(()->{
            while (true){
                System.out.println("t2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"这是thread t2");
        t2.start();
    }
}
