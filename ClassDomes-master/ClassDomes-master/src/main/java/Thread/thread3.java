package Thread;

/**
 * Thread类创建线程的写法：
 * 1.创建子类，继承自Thread
 * 2.创建一个类，实现Runnable接口，再创建一个Runnable实例，传给Thread实例
 * 3/4.就是上面两个写法的翻版  使用了匿名内部类  将在thread4/5 中实现
 */
public class thread3 {
    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();
        while(true){
            System.out.println("这是main线程，用来实现并发的");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//Runnable就是在描述一个“任务” 具体干啥
class MyRunnable implements Runnable{

    @Override
    public void run() {

        while(true){
            System.out.println("通过Runnable接口创建Thread");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}