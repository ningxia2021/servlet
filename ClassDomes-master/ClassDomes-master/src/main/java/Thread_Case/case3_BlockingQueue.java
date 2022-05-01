package Thread_Case;

/**
 * 自己手动实现【阻塞队列】
 * 1.先实现队列
 * 2.再加上线程安全
 * 3.再加上在实现阻塞
 */

class myBlockingQueue {
    /**
     * 基于数组实现一个循环队列
     * 这里需要明白循环队列怎样入队列(tail++)，出队列(head++)，判空（head = tail），判满（tail+1 = head），循环（tail = size-1时，tail = 0）
     */
//    数组
    private int[] data;
    //    头标记位
    private int head;
    //    尾标记位
    private int tail;
    //    已经被占用的长度
    private int size;

    //    初始化
    public myBlockingQueue(int max) {
        data = new int[max];
        this.head = 0;
        this.tail = 0;
    }

    //    接口
//    0.判断是否为空
    public boolean isEmpty() {
        if (head == tail) {
            return true;
        } else {
            return false;
        }
    }

    //    1.判断是否为满
    public boolean isFull() {
        if (tail + 1 == head) {
            return true;
        } else {
            return false;
        }
    }

    public static Object Locker = new Object();

    //    2.入队列
    public void push(int ele) throws InterruptedException {
        synchronized (Locker) {
            if (isFull()) {
//            如果满了，就会阻塞，不能再继续入
                Locker.wait();
            }
            data[tail] = ele;
            tail++;
            size++;
//        tail到头 开始循环
            if (tail == data.length) {
                tail = 0;
            }
            Locker.notify();
        }
    }

    //    3.出队列
    public int pull() throws InterruptedException {
        synchronized (Locker) {
            if (isEmpty()) {
//            如果为空，就会阻塞，等待有元素进入队列
                Locker.wait();
            }
            int ele = data[head];
            head++;
            Locker.notify();
            if (head == data.length) {
                head = 0;
            }
            size--;
            return ele;
        }
    }

    //    4.size大小
    public Integer gerSize() {
        return size;
    }
}

public class case3_BlockingQueue{

    public static void main(String[] args) throws InterruptedException {
        myBlockingQueue queue = new myBlockingQueue(1000);
        /**
         * 模拟生产消费者模型
         */
        Thread producer = new Thread(() -> {
            int i =1;
            while (true) {
                try {
                    System.out.println("生产了 :"+ i);
                    queue.push(i);
                    i++;
//                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();

        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    int ret = queue.pull();
                    System.out.println("消费了 : "+ ret);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        consumer.start();
    }
}
