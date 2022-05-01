package Thread_Case;

import java.util.concurrent.PriorityBlockingQueue;

class timeTask implements Comparable<timeTask> {

    private Runnable runnable;
    private long time;

    public timeTask(Runnable runnable, long delay) {
        this.runnable = runnable;
        this.time = System.currentTimeMillis() + delay;
    }

    public void run() {
        runnable.run();
    }

    @Override
    public int compareTo(timeTask o) {
        return (int) (this.time - o.time);
    }

    public long getTime() {
        return time;
    }
}

class mytimer44 {
    PriorityBlockingQueue<timeTask> queue = new PriorityBlockingQueue<timeTask>();

    public void schedule(Runnable runnable, long delay) {
        timeTask timeTask = new timeTask(runnable, delay);
        queue.put(timeTask);
        synchronized (locker) {
            locker.notify();
        }
    }

    Object locker = new Object();

    public mytimer44()  {
        Thread t1 = new Thread(()->{
            while (true){
                try {
                    timeTask task = queue.take();
                    if (System.currentTimeMillis()<task.getTime()){
                        queue.put(task);
                        synchronized (locker){
                            try {
                                locker.wait(task.getTime()-System.currentTimeMillis());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }else {
                        task.run();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        );
        t1.start();
    }
}


public class case4_myTimer {
    public static void main(String[] args)  {
        mytimer44 mytimer44 = new mytimer44();
        mytimer44.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("这是3s后的任务");
            }
        },3000);
        System.out.println("这是当前的任务");
    }
}
