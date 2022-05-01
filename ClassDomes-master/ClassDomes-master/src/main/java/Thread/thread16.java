package Thread;

import java.util.Scanner;

/**
 * 关于4.内存可见性 de 线程不安全案例
 * 解决办法：1、使用synchronized关键字，不光可以保证指令的原子性，同时也能保证内存可见性，被synchronized包裹起来的代码，编译器就不会轻易的做出优化。
 *              相当于手动禁用了编译的优化。
 *         2、使用volatile关键字，它和原子性无关，但可以保证内存可见性，
 *              禁止编译器做出上述优化，编译器每次执行判断相等时，都会从内存中读取isQuit的值。
 * 【volatile 是解决 内存可见性 的多数答案，针对一个线程读 一个线程写的情况使用，但是无法保证操作的原子性】
 */
public class thread16 {

    /* volatile */
   private static volatile int isQuit = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
             public void run() {
                while (0==isQuit) {
                    /**
                     * 由于这里的读操作非常的低效，所以编译器做了一个大胆的操作
                     * 将isQuit的值放在寄存器里加快读取速度
                     * 这就导致，后面无法通过修改isQuit变量的值来中断t线程
                     */
                }
                System.out.println(Thread.currentThread().getName() + "结束");
            }
        };
        t.start();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个数字，结束线程t");
        int i = scanner.nextInt();
//        Thread.sleep(100);
        isQuit=1;
        System.out.println(Thread.currentThread().getName() + "结束");
        /**
         * 结果：无法通过给isQuit置非0 来结束t线程
         */
    }
}

