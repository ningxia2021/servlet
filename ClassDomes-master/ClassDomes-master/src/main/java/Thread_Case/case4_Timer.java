package Thread_Case;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器
 * 标准库版本
 */
public class case4_Timer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timer ");
            }
        },3000);

        System.out.println("main");
    }
}
