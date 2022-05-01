package Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * JUC java.util.concurrent
 * 2022.04.01
 * 介绍除Runnable之外 另一个实现线程的方法
 */
public class thread20 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        通过Callable描述一个任务
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i =0;i<=1000;i++){
                    sum = i+sum;
                }
                return sum;
            }
        };
//        为了让线程执行callable中的任务，光使用构造方法还不够，还需要一个辅助的类
        FutureTask<Integer> task = new FutureTask<>(callable);
//        创建线程来完成计算工作
        Thread t1 = new Thread(task);
        t1.start();
//        如果没有计算出结果，get方法就会阻塞。
        System.out.println(task.get());
    }
}
