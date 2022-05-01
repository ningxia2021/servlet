package Thread;

/**
 * synchronized 不同上下文中有不同含义：1.多线程中指的是互斥 2.网络编程或IO中，表示消息发送方如何获取结果
 * 使用方式：1.直接修饰普通方法
 * 使用synchronized 的时候，其实本质上是针对某个对象进行加锁。
 * 2.修饰代码块 3.修饰一个静态方法
 */

/**
 * 【synchronized】 也叫 monitor locker
 *  可重入：同一个线程针对同一个锁，连续加锁两次，出现了死锁，就是不可重入，不会死锁，就是可重入
 *  解决线程安全的时候，要注意避免死锁
 */

/**
 * 死锁的四个必要条件：
 * 1.互斥使用，一个锁被一个线程占用后，其他线程用不了 【锁本身的特点】
 * 2.不可抢占，一个锁被一个线程占用之后，其他线程不能把这个锁给抢走 【锁本身的特点】
 * 3.请求和保持，当一个线程占据了多把锁之后，除非显式的释放锁，否则这些锁都是被该线程持有 【锁本身的特点】
 * 4.环路等待  【这是避免死锁的关键！】
 */

/**
 * 【wait】 和 【notify】
 * 处理线程调度随机性的问题，让程序之间有一个固定的顺序  join这是一种控制顺序的方式，但是它更倾向于控制线程结束。
 * 而wait和notify都是object对象，调用wait方法的线程，就会陷入阻塞
 * 阻塞到其他线程通过notify来通知~ 唤醒它
 */
public class thread17 {

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        /**
         * wait哪个对象 就要对哪个对象加锁
         */
        synchronized (object){
            System.out.println("wait 前");
            /**
             * wait操作会做三件事儿：
             * 1.先释放锁  （一定要先拿到锁，才可以释放，因此要想使用wait 就要搭配synchronization）
             * 2.等待其他线程的通知
             * 3.收到通知后，重新获取锁，并继续往下执行
             */
            object.wait();
            System.out.println("wait 后");
        }
    }
}

