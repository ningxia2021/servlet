package DataStructure;

import java.util.Arrays;

/**
 * @author gaoh
 * @time 2022.03.26
 * @theme 实现顺序表
 */
public class demo1 {
    /**
     * 定义顺序表的属性
     */
    static class SequentialList {
        //    属性
        public int[] Ele;
        private int ListSize;

        //    初始化
        public SequentialList(int number) {
            this.Ele = new int[10];
            this.ListSize = 0;
        }

//    定义顺序表功能

        /**
         * 1.判断顺序表是否为满
         */
        public boolean isFull() {
            if (ListSize == Ele.length) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * 2.判断顺序表是否为空
         */
        public boolean isEmpty() {
            if (ListSize == 0) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * 扩容
         *
         * @param array
         */
        public void Expansion(int[] array) {
            this.Ele = Arrays.copyOf(array, array.length + 10);
        }


        /**
         * 3.尾插
         */
        public void RearInsert(int val) {
//        若满则array
            if (isFull()) {
                Expansion(Ele);
            }
            Ele[ListSize] = val;
            ListSize++;
        }

        /**
         * 4.在指定位置插入数据
         */
        public void inSert(int pos, int val) {
            /**
             * 首先对插入位置的合法性进行判断：是否越界 是否isFull
             */
            if (pos < 0 || pos > ListSize || pos > Ele.length) {
                System.out.println("当前输入数据不合法");
                return;
            }
            /**
             * 动态扩容
             */
            if (isFull()) {
                Expansion(Ele);
            }
            /**
             * 指定位置插入
             */
            for (int i = ListSize - 1; i >= pos; i--) {
                Ele[i + 1] = Ele[i];
            }
            Ele[pos] = val;
            ListSize++;
        }

        /**
         * 删除指定位置上的元素
         *
         * @param pos
         */
        public void Delete(int pos) {
            /**
             * 合法性判断
             */
            if (pos < 0 || pos > ListSize) {
                System.out.println("pos越界");
                return;
            }
            /**
             * 实现后面的覆盖前面的操作
             */
            for (int i = pos; i < ListSize; i++) {
                Ele[pos] = Ele[pos + 1];
            }
            ListSize--;
        }

        /**
         * 查看顺序表
         */
        public void viewList(){
            /**
             * 遍历数组
             */
//            for (Integer a : Ele){
//                System.out.println(a);
//            }
            System.out.print("数组:");
            for (int i = 0; i<ListSize;i++){
                System.out.print(Ele[i]+" ");
            }
            System.out.println();
        }

    }


    //  主函数实现
    public static void main(String[] args) {
        SequentialList list = new SequentialList(10);
        System.out.println("是否为空？"+list.isEmpty());
        System.out.println("是否为满？"+list.isFull());
        System.out.println("插入9个数字 : ");
        list.RearInsert(1);
        list.RearInsert(2);
        list.RearInsert(3);
        list.RearInsert(4);
        list.RearInsert(5);
        list.RearInsert(6);
        list.RearInsert(7);
        list.RearInsert(8);
        list.RearInsert(9);
        list.viewList();

        System.out.println("是否为满？"+list.isFull());
        list.RearInsert(10);
        list.viewList();

        System.out.println("是否为满？"+list.isFull());
//        list.RearInsert(11);
//        list.RearInsert(12);
        System.out.println("是否为空？"+list.isEmpty());

        list.RearInsert(11);
        list.inSert(1,99);
        list.viewList();
        list.Delete(1);
        list.viewList();
    }

}
