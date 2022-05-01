package Thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基于CAS实现原子类 2022.03.31
 * 伪代码如下：
 * while(CAS(&value,old_value,操作oldvalue)!=true){
 *     old_value = value;
 * }
 * CAS:cpu实现的一条指令，包含了
 *          1.将内存中的值读取到寄存器 int old_value = value
 *          2.内存中的值与寄存器中的值是否相等 if(&value == ola_vale)
 *          3.相等则对其进行相关操作后再与内存中值进行交换 (swap)、不相等则继续读取内存中的值到寄存器
 * java标准库里提供了一组原子类，针对常用的int long array... 进行了封装，可以基于CAS的方式进行修改，并且线程安全
 */
public class thread19 {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger num = new AtomicInteger(0);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0;i<500000;i++){
//                    这个方法就相当于num++
                    num.getAndIncrement();

                }
            }
        });
        t1.start();

        Thread t2 = new Thread(()->{
            for (int i =0;i<500000;i++){
                num.getAndIncrement();

            }
        });
        t2.start();
        t1.join();
        t2.join();
        System.out.println("num = "+num.get());

    }
}
