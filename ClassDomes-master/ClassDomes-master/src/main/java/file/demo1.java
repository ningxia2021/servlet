package file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * 2022.04.03
 * 学习文件系统 file IO
 */
public class demo1 {
    //    构造File
    public static void main(String[] args) throws IOException {
//        绝对路径
        File file1 = new File("P:/电子书/斗破苍穹 (天蚕土豆) (z-lib.org).txt");
//        相对路径 基准是P:\Idea工作目录\java 这个项目目录
        File file2 = new File("./iostudy.txt");
//        获取文件的父目录
        System.out.println("获取文件的父目录 : " + file1.getParent());
//        获取文件路径
        System.out.println("获取文件路径 : " + file1.getPath());
//        获取文件名
        System.out.println("获取文件名 : " + file1.getName());
//        获取绝对路径
        System.out.println("获取绝对路径 : " + file1.getAbsolutePath());
//        获取绝对路径
        System.out.println("获取绝对路径 : " + file1.getCanonicalPath());
        System.out.println();
        System.out.println("======================================");
        System.out.println();
//        获取文件的父目录
        System.out.println("获取文件的父目录 : " + file2.getParent());
//        获取文件路径
        System.out.println("获取文件路径 : " + file2.getPath());
//        获取文件名
        System.out.println("获取文件名 : " + file2.getName());
//        获取绝对路径 (基准路径 拼接了 相对路径 )
        System.out.println("获取绝对路径 : " + file2.getAbsolutePath());
//        获取绝对路径 (纯绝对路径)
        System.out.println("获取绝对路径 : " + file2.getCanonicalPath());
        System.out.println();
        System.out.println("==================================================");
        System.out.println();
        File file3 = new File("./test.txt");
        System.out.println(file3.exists());
        System.out.println("创建文件 "+"./test.txt");
        file3.createNewFile();
        System.out.println("创建文件结束");
        System.out.println(file3.exists());
        System.out.println();
        System.out.println("===============================================");
        System.out.println();
//        罗列目录中的文件
        File file4 = new File("./");
        System.out.println("罗列目录中的文件 : "+Arrays.toString(file4.list()));
    }
}
