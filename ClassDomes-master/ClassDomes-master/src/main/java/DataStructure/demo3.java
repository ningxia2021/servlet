package DataStructure;

import java.util.Arrays;

/**
 * 2022.04.02 使用数组实现栈
 */

class mystack{
    private int[] array;
    private int size;

    public mystack(int num){
        this.array = new int[num];
        this.size = 0;
    }

//    判断是否为空
    public boolean isEmpty(){
        if (this.size == 0){
            return true;
        }else {
            return false;
        }
    }

//    判断是否为满
    public boolean isFull(){
        if (this.size == array.length){
            return true;
        }else {
            return false;
        }
    }

//    扩容
    public void expansion(){
        if (isFull()){
            array = Arrays.copyOf(array,array.length+10);
        }
    }

//    压栈
    public void push(int data){
        if (isFull()){
            this.expansion();
        }
        array[size] = data;
        size++;
    }

//    出栈
    public int pull(){
        int data = array[size-1];
        size--;
        return data;
    }

//    取栈顶元素
    public int peek(){
        return array[size-1];
    }

//    显示
    public void display(){
       for (int i = 0;i<size;i++){
           System.out.print(array[i]+" ");
       }
        System.out.println();
    }
}

public class demo3 {
    public static void main(String[] args) {
        mystack stack = new mystack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.display();
        System.out.println(stack.pull());
        stack.display();
        System.out.println(stack.peek());
        stack.display();
    }
}
