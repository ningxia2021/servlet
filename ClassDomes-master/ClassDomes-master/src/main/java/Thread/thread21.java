package Thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 2022.04.01
 * ReentrantLock  一个新的加锁方式
 * 和synchronized 的区别：
 * 1.synchronized 是一个关键字，背后的逻辑是JVM内部实现的~~ ReentrantLock 是一个标准库中的类 背后的逻辑是java代码写的
 * 2.synchronized 不需要手动释放锁，出了代码块，锁自然释放~~ ReentrantLock 必要要手动释放锁，要谨防忘记释放
 * 3.synchronized 如果竞争锁的时候失败，就会阻塞等待~~ 但是  ReentrantLock 除了阻塞等待这一手之外，还有一手，trylock，失败了直接返回。避免了死等。给了我们更多的回旋余地
 * 4.synchronized 是一个非公平锁~~ ReentrantLock 提供了非公平和公平两个锁版本。在构造方法中可以通过一个参数指定当前是公平锁还是非公平锁。
 * 5.synchronized 衍生出来的等待机制是wait和notify，功能相对有限~~ ReentrantLock衍生出来的等待机制是Condition类，也可以叫做条件变量，功能更丰富一些。
 * 日常开发中 synchronized 是够用的！ 很少去用其他的一些锁，这些其他锁了解即可！
 */
public class thread21 {
    public static void main(String[] args) {
//        参数true就代表公平锁，默认不填写代表非公平锁
        ReentrantLock locker = new ReentrantLock(true);
        locker.lock();
        locker.unlock();
    }
}
