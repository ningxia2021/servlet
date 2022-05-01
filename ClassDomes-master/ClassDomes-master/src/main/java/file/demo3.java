package file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 【流对象 Stream】
 * 读写文件内容
 * java标准库提供了一组类
 * 字节流对象：针对二进制文件，以字节为单位进行读写
 *      读：InputStream
 *      写：OutputStream
 * 字符流对象：读写文本文件，以字符为单位进行读写的
 *      读：Reader
 *      写：Writer (以上这些类都是抽象类，使用时需要用他们的子类 继承自这些类 FileInputStream FileOutputSteam FileWrite FileRead。。。)
 */
public class demo3 {
    public static void main(String[] args) {
        /**
         * InputStream是抽象类，只能new其具体实现方法的子类
         * 这里将文件内容读到inputStream对象中
         * InputStream提供了三种从对象中读取文件内容的方式：
         *      1.inputSteam.read()   无参数版本，一次读一个字节，返回值是读到的这个字节
         *      2.inputSteam.read(byte[] b) 一个参数版本，一次读若干个字节，把读到的字节放到参数中指定的数组中，返回值就是读到的字节数
         *      3.inputSteam.read(byte[] b , int off , int len)  三个参数版本，把读到的结果放到参数指定的数组中，返回值就是读到的字节，
         *                                                       其中off指独到的内容从数组中哪个位置开始放置， len表示最多可以放多少个字节
         *      读取结束回返回-1
         *      返回对象是int
         */
//        1.创建对象，同时也是打开文件
        InputStream inputStream = null;
        try{
            //
            inputStream = new FileInputStream("./test.txt");
            while(true){
//        2.尝试一个字节一个字节读，知道把文件都读完
                int a = inputStream.read();
                if (a==-1){
//                文件已经读完
                    break;
                }
//            此处读出来的是字符的ascii码值
                System.out.print(a+" ");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
//        3.读完记得关闭文件，释放资源
            try {
//               为了防止异常导致无法关闭文件，所以需要将其放在finally中
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
