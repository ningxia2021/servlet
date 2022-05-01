package file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * demo3代码优化
 */
public class demo4 {
    public static void main(String[] args) {
        /**
         * 在这个代码中 并没有显示的调用close关闭字节流
         * 当我们执行完try语句块后
         * try会帮我们自动调用
         * 但是 try的括号中 需要满足实现closeable这个接口才可以实现自动关闭流的功能
         */
        try(InputStream inputStream = new FileInputStream("./test.txt");
        ){
            while (true){
                int b = inputStream.read();
                if (b==-1){
                    break;
                }
                System.out.print(b+" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
