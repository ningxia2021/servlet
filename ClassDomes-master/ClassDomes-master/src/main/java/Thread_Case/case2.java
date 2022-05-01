package Thread_Case;

/**
 * 懒汉模式
 */
class Singleton2 {

    /**
     * 1.就不立即初始化实例
     * tips:反复读instance就会面临反复读取调用出现的内存可见性问题！
     * 比如一百个线程想要获取这个getInstance方法，他们在第一个if判定时都会读取instance值，这就造成了内存可见性问题
     * 随后竞争到锁的哪个线程进入了第二个if判定，成功的修改了instance的值
     * 但是 由于编译器的优化 就会导致剩下99个线程依然读到的instance值为null；依然会继续竞争锁，进入第二次if判定，造成效率还是低下。
     * 因此 ，对症下药  加volatile
     */
    private static volatile Singleton2 instance = null;

    /**
     * 2.把构造方法设为private
     */
    private Singleton2() {
//        初始化
    }

    /**
     * 3.提供一个方法来获取实例
     * 只有真正用到这个实例的时候,才会创建实例
     * =======================================================================
     * tips：这里真正要解决的是线程安全的问题，具体指在多线程环境下，并发的调用getInstance方法
     * 饿汉模式中的getInstance只是读取了变量的内容，如果多个线程只是读取变量的操作 不修改 那么线程任然安全的
     * 懒汉模式中既包含了读 又包含了修改 而且这的读和写还是分成两个步骤（不是原子的）；因此 存在线程安全问题
     */
    public static Singleton2 getInstance() {
        /**
         * 改进方案：如果第一个if条件成立，说明当前单例未初始化过，存在线程安全问题，需要加锁，
         * 否则则说明instance已经初始化实例了，可以直接return
         */
        if (instance == null) {
            /**
             * 这里使用类对象作为锁对象
             * 类对象在一个程序中只有一份，就能够保证多个线程调用个体Instance的时候都是针对同一个对象进行的加锁
             * 保证加锁对象的一致。彼此之间就会出现互斥和竞争。因此就可以保证线程安全。
             * 但仅仅这样还存在问题：无论instance是否被初始化，调用这个getInstance的时候，都会加锁.
             * 一旦instance初始化后，instance就仅仅是读操作，线程已经安全了
             * 但仍然线程间面临竞争，这样会导致效率始终还是上不去
             */
            synchronized (Singleton2.class) {
                /**
                 * 这里两个 if 判断的意思完全不同
                 * 第一个 if 是判定是否要加锁
                 * 第二个 if 是判定是否要创建实例
                 * 因此，这个结构叫做：双重 if 判定！
                 */
                if (instance == null) {
                    instance = new Singleton2();
                }
                return instance;
            }
        }
        return instance;
    }


}

public class case2 {
    public static void main(String[] args) {
        Singleton2 instance = Singleton2.getInstance();

    }

}
