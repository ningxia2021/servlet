package DataStructure;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 【实现有序数组】
 * 有序数组插入时，需要比较元素大小，开销较大
 * 但是查找时很方便，无需遍历所有元素，通过拿索引的值就可以知道还要不要继续查下去
 */
class orderedArray {
    //    数组
    private int[] array;
    //    容量
    private int sizes;

    //    初始化
    public orderedArray(int max) {
        this.array = new int[max];
        this.sizes = 0;
    }

    //    是否为空
    public boolean isEmpty() {
        if (array.length == 0 || this.sizes == 0) {
            return true;
        } else {
            return false;
        }
    }

    //    是否为满
    public boolean isFull() {
        if (array.length == this.sizes) {
            return true;
        } else {
            return false;
        }
    }

    //    插入数据
    public void add(int number) {
        if (isEmpty()) {
            array[0] = number;
            sizes++;
            return;
        }
        //    扩容
        if (isFull()) {
            array = Arrays.copyOf(array, array.length + 5);
        }
        //   实现有序插入，这将是与普通数组的最大差别
        //   遍历数组，比较大小，获取下表，挪位置，插入
        for (int i = 0; i < sizes; i++) {
            if (array[i] > number) {
                for (int k = sizes - 1; k >= i; k--) {
                    array[k + 1] = array[k];
                }
                array[i] = number;
                sizes++;
                return;
            } else {
                if (i + 1 == sizes) {
                    array[sizes] = number;
                    sizes++;
                    return;
                }
            }
        }
    }

    //    查看数组所有元素
    public void display() {
        for (int i = 0; i < sizes; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    //    获取sizes
    public int getsizes() {
        return sizes;
    }

    //   二分查找定位某个元素，如果存在，返回其下标，不存在返回-1
    private int isContainNum(int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (array[mid] == target) {
    //   返回下标
            return mid;
        } else if (target > array[mid]) {
            return isContainNum(target, mid + 1, right);
        } else  {
            return isContainNum(target, left, mid - 1);
        }
    }
    //    对二分查找方法的初始化
    public int contain(int tager){
        if (array!=null){
            return isContainNum(tager,0,array.length-1);
        }
        return -1;
    }

}

public class demo5 {
    public static void main(String[] args) {
        orderedArray orderedArray = new orderedArray(5);
    //    随机插入100以内的10个数
        for (int i = 0; i < 10; i++) {
            double random = (Math.random() * 1000);
            orderedArray.add((int) random % 100);
        }
        orderedArray.display();
        System.out.println("共包含 " + orderedArray.getsizes() + "  个元素");
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("请输入一个数");
            int ret = scanner.nextInt();
            //     调用比较接口 看ret是否包含在数组内 并给出结果
            int contain = orderedArray.contain(ret);
            if (contain != -1){
                System.out.println("包含");
            }else {
                System.out.println("不包含");
            }
        }
    }
}
