package Thread_Case;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Timer内部都需要啥？
 * 1）管理很多的任务
 *  1.1）描述任务
 *      创建一个专门的类来表示一个定时器中的任务。类似与TimerTask
 *  1.2）组织任务
 *      使用一定的数据结构把一些任务给放到一起
 * 2）执行时间到了的任务
 */

//创建一个类 表示具体的任务
class myTask implements Comparable<myTask>{
    //    表示任务具体要干啥
    private Runnable runnable;
    //    任务具体啥时候干,保存任务要执行的毫秒级时间戳
    private long time;

    public myTask(Runnable runnable, long delay) {
        this.runnable = runnable;
//        相对时间
        this.time = System.currentTimeMillis() + delay;
    }

    public void run() {
        runnable.run();
    }

    public long getTime(){
        return time;
    }

    @Override
    public int compareTo(myTask o) {
        return (int) (this.time-o.time);
    }
}

//创建定时器，要可以管理多个任务（myTask）就需要用到合适的数据结构来管理这些任务
//这些任务需要按照时间的排序，用到堆来组织
class myTimer {
//    此处的队列 要考虑到线程安全问题   可能在多个线程中进行入队列和出队列  所以这里采用阻塞的优先级队列
    private PriorityBlockingQueue<myTask> queue = new PriorityBlockingQueue<>();

    public void schedule(Runnable runnable,long delay){
        myTask task = new myTask(runnable,delay);
//        优先队列需要对象实现Comparable<>接口
//        否则不知道比较规则，会报错：Thread_Case.myTask cannot be cast to java.lang.Comparable
        queue.put(task);
    }

    public myTimer(){
        Thread t1 = new Thread(()->{
            while(true){
//                取出队首元素
                try {
                    myTask task = queue.take();
//                    比较是否到时间
                    if (System.currentTimeMillis()<task.getTime()){
//                        说明还没到时间,就需要把任务再塞回到队列中
                        queue.put(task);
//                       计算一下需要等多久，并开始阻塞
                        wait(System.currentTimeMillis()-task.getTime());
                    }else {
//                        时间到了  就需要执行任务
                        task.run();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
    }
}

public class case4_Timer_2 {
    public static void main(String[] args) {
        myTimer myTimer = new myTimer();
        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        },3000);
        System.out.println("main");
    }
}
