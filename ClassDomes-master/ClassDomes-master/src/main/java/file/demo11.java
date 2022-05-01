package file;

import java.io.*;
import java.util.Scanner;

/**
 * 案例3
 * 扫描目录 并进行文件内容的查找
 * 先输入一个路径
 * 再输入要查找文件内容的关键字
 * 递归的遍历文件，找到看哪个文件里的内容包含了关键字，就把对应的文件路径打印出来
 * 具体实现：先递归遍历文件，针对每个文件都打开，并读取文件内容，再进行字符串查找即可
 */

public class demo11 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入要扫描的路径");
        String rootDir = scanner.next();
        System.out.println("输入要查找的关键字");
        String keyWords = scanner.next();
//        判断路径合法性
        File rootFile = new File(rootDir);
        if (!rootFile.isDirectory()) {
            System.out.println("路径不合法");
            return;
        }
//        寻找文件，并读取内容进行比较
        scan(rootFile, keyWords);
    }

    private static void scan(File rootFile, String keyWords) throws IOException {
//        列出路径下的所有文件，并存在相应数据类型的数组中
        File[] files = rootFile.listFiles();
        if (files == null) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                scan(f, keyWords);
            } else {
//                是文件 就比较
                if (contains(f, keyWords)) {
                    System.out.println(f.getCanonicalPath());
                }
            }
        }
    }

    private static boolean contains(File f, String keyWords) {
        StringBuilder stringBuilder = new StringBuilder();
//        把f中的内容都读出来放到StringBuilder中拼装起来
        try (Reader reader = new FileReader(f.getCanonicalPath())) {
            char[] buffer = new char[1024];
            while (true) {
                int lens = reader.read(buffer);
                if (lens == -1) {
                    break;
                }
                stringBuilder.append(buffer, 0, lens);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return stringBuilder.toString().contains(keyWords);
//        indexof()返回的是子串的下标，如果keyWords在stringBuilder中不存在，则返回下表-1
        return stringBuilder.indexOf(keyWords) != -1;
    }

}
