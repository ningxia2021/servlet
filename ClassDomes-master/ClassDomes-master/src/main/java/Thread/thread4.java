package Thread;

/**
 * 实现thread3中 写法3/4
 * 关于匿名内部类的介绍：
 * 匿名内部类可以使你的代码更加简洁，你可以在定义一个类的同时对其进行实例化。它与局部类很相似，不同的是它没有类名，如果某个局部类你只需要用一次，
 *   那么你就可以使用匿名内部类
 *
 */
public class thread4 {
    public static void main(String[] args) {
        /**
         * 采用匿名内部类的方式实现一个线程的创建
         * 形式为：
         * Thread thread = new Thread(){
         *             继承自Thread类
         *             同时，重写run方法
         *             同时再new 出这个匿名内部类的实例
         *         };
         */

//        这是针对thread 的匿名内部类
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("采用匿名内部类创建线程！");
            }
        };
//        调用start开启线程
        thread.start();
    }
}
