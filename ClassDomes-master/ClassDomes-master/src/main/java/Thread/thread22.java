package Thread;

import java.util.concurrent.Semaphore;

/**
 * 2022.04.01
 * 信号量 semaphore 是一个更广义的锁 或者说 锁是信号量中第一种特殊情况，叫做“二元信号量”
 * 实际开发中并不会经常用到信号量
 */
public class thread22 {
    public static void main(String[] args) throws InterruptedException {
//        初始化的值的表示可用资源有4个
        Semaphore semaphore = new Semaphore(4);
//        申请资源 P操作
        semaphore.acquire();
//        释放资源，V操作
        semaphore.release();
    }
}
