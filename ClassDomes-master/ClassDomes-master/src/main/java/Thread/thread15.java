package Thread;

/**
 * 【线程不安全案例】
 * 什么样的代码会产生线程不安全？产生 线程不安全的 原因如下：
 * 1.线程是抢占式执行。线程的调度充满随机性。【根本原因，但无可奈何，因此想要线程安全，就只能从第二、三个方案中考虑】
 * 2.多个线程对同一个变量进行修改。（如果多个线程对同一个变量读操作，也没事；多个线程对多个变量修改，也没事儿）
 * 3.针对变量的操作不是原子的。【加锁->原子】
 * 4.内存可见性，也会影响线程安全！【由编译器优化导致】
 * 举例说明：假设有 t1 t2 两个线程，首先，t1 从内存读 相比于 从寄存器读 很低效且开销很大。所以，编译器的优化会把频繁读取的变量存进寄存器中，这样可以极大的提升读取效率。
 * 但是，这时候万一 t2 有其他线程修改了内存中的这个值，t1还在读取，就会被感知不到，读取的还是之前存在寄存器中的值。导致了线程不安全！
 * 5.指令重排序 也是编译器优化的一种操作，也会导致线程不安全
 * 所谓指令重排序，就是编译按照最优解，重新对指令的先后顺序进行一定的调整。
 * 正常来说，保证逻辑不变的前提下，调整代码顺序是可以提高效率的，但是多线程时，编译器就可能误判。
 * 解决方案：【synchronized】 不光可以保证【原子性】 还可以保证【内存可见性】 同时还可以保证【指令重排序】
 */

//    这个变量就是两个线程自增的变量
class Counter {
    public int count;
    /**
     * 给方法加上synchronized 关键字 ，此时进入方法就会加锁，离开方法就会解锁
     * 当我们一个线程枷锁成功的时候，其他线程尝试加锁，就会触发等待。处于BLOCKED的状态
     * 阻塞会一直持续到占用锁的线程，把锁释放为止
     */

    /**
     * 1.给方法加synchronized
     */
    synchronized public void increase() {
        count++;
    }

    public void increase1() {
        synchronized (this) {
            count++;
        }
    }

    /**
     * 2.修饰一个代码块
     */


    /**
     * 3.修饰一个静态方法-----针对当前的类对象加锁 【对象.class】 反射
     */
}

public class thread15 {

    private static Counter counter = new Counter();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    counter.increase();
                }
            }
        });
//        这里的自增操作主要经历一下过程：
//        1.把内存中count的值 加载到CPU寄存器中
//        2.把寄存器中的值给+1
//        3.把寄存器中的值再写回给内中的count中
        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    counter.increase();
                }
            }
        };

        t1.start();
        t2.start();
//必须要在t1 t2都执行完之后才可以打印 最终计算结果 因此 此时就要让main线程等待t1 t2执行，因此 需要阻塞main线程
        t1.join();
        t2.join();

        System.out.println("Result = " + counter.count);
    }
}
