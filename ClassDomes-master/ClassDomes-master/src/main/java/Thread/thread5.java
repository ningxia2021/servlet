package Thread;

/**
 * 实现线程的写法3/4
 */
public class thread5 {
    public static void main(String[] args) {
//       这是 针对new的Runnable创建的匿名内部类，同时new出Runnable实例传给Thread的构造方法
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello thread");
            }
        });
        /**
         * 通常认为Runnable的匿名内部类构造线程，这种写法更好一些，能够做到让线程和线程执行的任务，更好的解耦合
         * 写代码一般希望高内聚，低耦合
         */
        t.start();
    }
}
