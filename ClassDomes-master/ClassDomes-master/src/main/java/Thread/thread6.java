package Thread;

/**
 * 使用lambda表达式 创建线程
 */
public class thread6 {
    public static void main(String[] args) {
        /**
         * lambda 表达式也是一个匿名内部类 其中：
         * "->"表示它是一个lambda
         * “（）”表示它的参数
         */
        Thread t = new Thread(() ->{
            System.out.println("lambda 创建线程");
        });
        t.start();
    }
}

/**
 * 以上五种写法都很常见，要求熟练掌握！
 */
