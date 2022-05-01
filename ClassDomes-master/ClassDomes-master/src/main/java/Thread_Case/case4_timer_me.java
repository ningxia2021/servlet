package Thread_Case;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 自己实现一下定时器 2022.03.31
 */
//定义定时器的任务:内容+时间
class time_task implements Comparable<time_task>{
//    定义任务 这里用runnable来表示，其实例化由线程中完成
    private Runnable runnable;
//    定义时间
    private long time;
//    构造方法
    public time_task(Runnable runnable,long delay){
        this.runnable = runnable;
        this.time = System.currentTimeMillis()+delay;
    }
//    获取时间进行比较确定等待时间
    public long getTime(){
        return this.time;
    }
//    因为要按照时间来进行优先级排序，所以需要实现Conparable接口
    @Override
    public int compareTo(time_task o) {
        return (int)(this.time-o.time);
    }
//    任务到时间，需要执行任务
    public void run(){
        runnable.run();
    }
}

//定义定时器 选择一个数据结构来组织任务，达到按照时间排序，并执行到时的任务 的效果
class gaohtimer{
//    选择带有阻塞队列的堆 新的任务都会放进这个队列中
    PriorityBlockingQueue<time_task> queue = new PriorityBlockingQueue<>();
//    定义个方法，将任务初始化，并放进队列
    public void schedule(Runnable runnable , long delay){
        time_task task = new time_task(runnable,delay);
        queue.put(task);
        synchronized (Locker){
            Locker.notify();
        }
    }
    Object Locker = new Object();
//    构造方法，线程轮询
    public gaohtimer()  {
        Thread t1 = new Thread(()->{
            while(true){
                synchronized (Locker){
                    time_task take = null;
                    try {
                        take = queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (System.currentTimeMillis()<take.getTime()){
//                说明时间不到,放回去继续等待
                        queue.put(take);
//                    计算等待时间，避免忙等
                        try {
                            Locker.wait(take.getTime()-System.currentTimeMillis());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        take.run();
                    }
                }
            }
        });
        t1.start();
    }
}

public class case4_timer_me {
    public static void main(String[] args) throws InterruptedException {
        gaohtimer timer = new gaohtimer();
//        放任务内容及时间进队列，这里就堆runnable进行了实例化
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("来啦老弟");
            }
        },3000);
        System.out.println("main");
        Thread.sleep(5000);
    }
}
