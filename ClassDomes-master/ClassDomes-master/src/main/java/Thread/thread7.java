package Thread;

/**
 * 多线程可以提高任务完成效率
 * 下面就来测试一下
 * 对两个变量的操作，来看看处理的时间上有什么不同
 */
public class thread7 {
//    申明变量
    final static long count = 10_0000_0000;
    //    串行执行0-10亿的自增操作
    public static void serial() {
//        记录程序执行的时间
        long begin = System.currentTimeMillis();
        long a = 0;
        for (long i = 0; i < count; i++) {
            a++;
        }
        long b = 0;
        for (long j = 0; j < count; j++) {
            b++;
        }
        long end = System.currentTimeMillis();
        System.out.println("串行操作消耗的时间为：" + (end - begin) + "ms");
    }

    public static void concurrency() {
        long begin = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                long a = 0;
                for (long i = 0; i < count; i++) {
                    a++;
                }
            }
        });

        Thread t2 = new Thread(() -> {
            long b = 0;
            for (long j = 0;j<count;j++ ){
                b++;
            }
        });

        t1.start();
        t2.start();
        /**
         * 这里main方法与t1 t2之间是并发执行的关系，此处如果t1 t2还没有执行完就开始计时，明显是不对的
         * 应该让main等待t1 和t2 执行完之后，再来记录时间
         * 这里join的效果就是等待线程结束
         * 注意：main调用的t1.join 就是main等待t1线程结束
         */
        try {
//            等待t1 和t2 执行完 再开始计算时间
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("串行操作消耗的时间为：" + (end - begin) + "ms");
    }

    public static void main(String[] args) {
//        thread7 thread7 = new thread7();
//        thread7.serial();
        serial();
        concurrency();
    }
}
