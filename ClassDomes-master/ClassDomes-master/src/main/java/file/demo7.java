package file;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * 按照字符读
 */
public class demo7 {
    public static void main(String[] args) {
        try (Reader reader = new FileReader("./test.txt")){
            while (true) {
                char[] buffer = new char[1024];
                int len = reader.read(buffer);
                if (len == -1){
                    break;
                }
//                for (int i = 0; i<len;i++){
//                    System.out.print(buffer[i]+" ");
//                }
//                这个操作是对上面循环打印数组中元素的优化,将字符变成字符串。
                String s = new String(buffer,0,len);
                System.out.println(s+" ");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}


