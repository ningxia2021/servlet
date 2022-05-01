package Thread_Case;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * 因为反复创建线程，会造很大的开销，因此，需要创建线程池
 * 那么问题来了，为啥反复创建销毁线程会造成很大开销呢？
 * 因为创建线程是需要内核来进行操作的，需要内核创建PCB，这就需要由用户态-内核态进行操作。而线程池则可以将创建好的线程放在用户态保存，下次用的时候就不必经过内核了
 * 举个例子，好比自己去商场买包和找代购买包一个道理，能不倒一手，就不要倒手的好.
 */
//标准库的线程池
public class case5 {
    public static void main(String[] args) {
        //    创建一个自动扩容的线程池
        ExecutorService pool = Executors.newCachedThreadPool();
//    创建一个线程数量固定的线程池
//    ExecutorService pool = Executors.newFixedThreadPool();
//    创建一个有定时器功能的线程池
//    ExecutorService pool = Executors.newScheduledThreadPool();
//    创建一个只有一个线程的线程池
//    ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hello pool");
                }
            });
        }
    }
}
