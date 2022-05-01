package file;

import java.io.File;

//File的构造
public class demo2 {
    public static void main(String[] args) {
//        给一个路径
        File file = new File("./aaa/BBB/ccc");
//        创建多级目录 而mkdir()只能创建一级目录  mkdirs()可以创建多级目录
        file.mkdirs();
        System.out.println(file.isDirectory());
    }
}
