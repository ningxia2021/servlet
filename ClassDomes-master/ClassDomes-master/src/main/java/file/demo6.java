package file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * OutputStream 写内容的案例
 * 每次按照写方式打开文件都会清空原有文件的内容
 * 清空旧的内容，从起始位置往后写
 *
 * 还有一种追加写的流对象，打开之后不清空，从文件末尾继续写
 */
public class demo6 {
    public static void main(String[] args) {
        try(OutputStream outputStream = new FileOutputStream("./test.txt")){
            outputStream.write(97);
            outputStream.write(99);
            outputStream.write(101);
            outputStream.write(103);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
