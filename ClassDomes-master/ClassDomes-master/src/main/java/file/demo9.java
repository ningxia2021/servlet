package file;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * 2022.04.04
 * 案例1 实现查找文件并删除
 */
public class demo9 {
    public static void main(String[] args) {
//        1.输入要扫描的目录以及要删除的文件名
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要删除的路径->");
        String rootDirPath = scanner.next();
        System.out.println("请输入要删除的文件名->");
        String toDeleteName = scanner.next();
        File rootDir = new File(rootDirPath);
        if (!rootDir.isDirectory()){
            System.out.println("文件路径有误！");
            return;
        }
//        2.遍历目录，把指定目录中的所有文件和子目录都遍历一遍，从而找到要删除的文件
//        通过这个方法来实现递归遍历并删除的操作
        scanDir(rootDir,toDeleteName);

    }

    private static void scanDir(File rootDir, String toDeleteName) {
//        1.先列出root中有哪些内容
        File[] files = rootDir.listFiles();
        if (files == null){
//            rootDir是一个空目录
            return;
        }
//        2.遍历当前列出的这些文件，如果是普通文件就检测文件名是否是要删除的文件
//        如果是目录就递归的进行遍历
        for (File e : files){
            if (e.isDirectory()){
                scanDir(e,toDeleteName);
            }
            if (e.isFile()){
                if (e.getName().contains(toDeleteName)){
                    deletefile(e);
                }
            }
        }
    }

    private static void deletefile(File e) {
        try {
            System.out.println(e.getCanonicalPath()+"确定要删除吗？ (Y/N)");
            Scanner scanner = new Scanner(System.in);
            String chooice = scanner.next();
            if (chooice.equalsIgnoreCase("y")){
                e.delete();
                System.out.println("文件删除成功！");
            }else {
                System.out.println("文件取消删除！");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
