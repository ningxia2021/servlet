package file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * InputStream.read(byte[] buffer)
 *  每次读磁盘效率都是比较低效的，所以每次能多读就多读一些
 */
public class demo5 {
    public static void main(String[] args) {
    try(InputStream inputStream = new FileInputStream("./test.txt")){
        while (true){
//          定义一个缓冲区 用来存放从inputStream读取到的内容，每次缓冲区满了之后才会存到内存中，这样会避免读一个存一个的低效操作，而是攒够了一把存，更高效
            byte[] buffer = new byte[3];
            int len = inputStream.read(buffer);
            if (len == -1){
                break;
            }
            for (int i = 0;i<len;i++){
                System.out.print(buffer[i]+" ");
            }
        }
    }  catch (IOException e) {
        e.printStackTrace();
    }
    }
}
