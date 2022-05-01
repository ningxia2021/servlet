package file;

import java.io.*;
import java.util.Scanner;

/**
 * 2022.04.04
 * 案例2：进行文件的复制
 *  需要让用户指定两个路径：一个是源路径 一个是目标路径
 *      打开源路径的文件，进行读取内容，并写入到目标文件中！
 */
public class demo10 {
    public static void main(String[] args) {
        System.out.println("请输入要复制的文件路径-》");
        Scanner scanner = new Scanner(System.in);
//        拿到要复制的文件路径
        String src = scanner.next();
        System.out.println("请输入要粘贴的路径");
//        拿到要粘贴的文件路径
        String desc = scanner.next();
//        文件操作 判断是否是文件 文件是否合法
        File srcFile = new File(src);
        if (!srcFile.isFile()){
            System.out.println("源路径不正确！重新输入");
            return;
        }
//        文件验证合法后，开始进行拷贝操作
//        此处不需要检查目标文件的存在，OutputStream写文件的时候能够自动创建不存在的文件
        try (InputStream inputStream = new FileInputStream(src)){
            try(OutputStream outputStream = new FileOutputStream(desc)){
                byte[] buffer = new byte[1024];
                while (true){
                    int lens = inputStream.read(buffer);
                    if (lens == -1){
//                        读完了 跳出循环
                        break;
                    }
//                        写入的时候不要把整个buffer都写进去，毕竟buffer有可能只有一部分是有效数据
                    outputStream.write(buffer,0,lens);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("拷贝成功!");
    }
}
