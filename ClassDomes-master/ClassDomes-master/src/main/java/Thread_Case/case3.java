package Thread_Case;

/**
 * 案例二：阻塞队列
 * 阻塞队列同样也是一个符合先进先出规则的队列，只是相比普通队列多出了其他方面的功能
 * 功能：1.线程安全 2.产生阻塞效果 （如果队列为空，尝试出队列，就会阻塞；如果队列为满，尝试入队列，也会阻塞）
 * 基于此特性，可以实现一个生产者消费者模型，此处阻塞队列就可以作为生产者消费者模型中的交易场所
 * 生产者消费者模型：假设有A、B两台服务器，A接收请求，调用B ；B给A返回响应。如果不使用生产者消费者模型，A、B的耦合性比较强
 *                开发A代码的时候就得充分了解B提供的一些接口
 *                开发B代码的时候也得充分了解A是怎样调用的，一旦把B换成C ，A的代码就需要较大的改动
 *                生产者消费者模型，就会将请求和响应 放在阻塞队列中，A发送请求至阻塞队列，B从阻塞队列返回先响应 达到解耦；
 *                A只需要关注如何和队列交互，不需要认识B   B同理
 * 生产者消费者模型优点：1.解耦 2.削峰填谷
 *
 */


import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 【标准库中阻塞队列的使用】
 */
public class case3 {
    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<String> queue = new LinkedBlockingDeque<>();
//        入队列
        queue.put("hello");
//        出队列
        String s = queue.take();
        System.out.println(s);
    }
}
