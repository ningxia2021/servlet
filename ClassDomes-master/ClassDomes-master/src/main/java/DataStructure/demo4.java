package DataStructure;

/**
 * 2022.04.02
 * 数组实现CircularBlockingQueue (循环阻塞队列)哈哈
 */
class CircularBlockingQueue {
    //    定义队列
    //    标记头 head
    private int head;
    //    标记尾 tail
    private int tail;
    //    数量
    private int size;
    //    定义数组
    private int[] array;
    //    构造方法
    public CircularBlockingQueue(int num) {
        this.array = new int[num];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    //    判断是否为空 如果为空时，还要出队列，就要开始阻塞
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    //    判断是否为满 如果为满时，还要入队列，就要开始阻塞
    public boolean isFull() {
        if (tail + 1 == head) {
            return true;
        } else {
            return false;
        }
    }

    Object locker = new Object();

    //    入队列
    public void putQueue(int data) throws InterruptedException {
        synchronized (locker) {
            if (isFull()) {
//            阻塞
                locker.wait();
            }
            array[tail] = data;
            tail++;
            size++;
            if (tail == array.length) {
                tail = 0;
            }
            locker.notify();
        }
    }


    //    出队列
    public int takeQueue() throws InterruptedException {
        synchronized (locker) {
            if (isEmpty()) {
//            阻塞
                locker.wait();
            }
            int ret = array[head];
            head++;
            locker.notify();
//            满足于循环条件
            if (head == array.length) {
                head = 0;
            }
            size--;
            return ret;
        }
    }

    //    查看队列
    public void diaPlay() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}

public class demo4 {
    public static void main(String[] args) throws InterruptedException {
        CircularBlockingQueue queue = new CircularBlockingQueue(5);
        Thread producter = new Thread(new Runnable() {
            int num = 1;
            @Override
            public void run() {
                while (true) {
                    try {
                        queue.putQueue(num);
                        System.out.println("生产了：" + num);
                        num++;
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        producter.start();

        Thread consumer = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        int ret = queue.takeQueue();
                        System.out.println("消费了 ：" + ret);
//                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread.sleep(3000);
        consumer.start();
//        queue.putQueue(1);
//        queue.putQueue(2);
//        queue.putQueue(3);
//        queue.putQueue(4);
//        queue.diaPlay();
//        System.out.println(queue.takeQueue());
//        System.out.println(queue.takeQueue());

    }
}
