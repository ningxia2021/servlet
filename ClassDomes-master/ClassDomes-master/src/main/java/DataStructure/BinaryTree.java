package DataStructure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 2022.04.06
 * 二叉树的实现
 * 前序遍历：即先遍历根节点，再遍历左子树节点，再遍历右子树节点；
 * 2022.04.07
 * 获取节点个数
 * 获取叶子节点个数
 */

//定义二叉树的节点属性
class btNode {
    //        内容
    private char val;
    //        左子树
    private btNode leftChild;
    //    右子树
    private btNode rightChild;

    //    构造方法
    public btNode(char val) {
        this.val = val;
    }

    public char getVal() {
        return val;
    }

    public btNode getLeftChild() {
        return leftChild;
    }

    public btNode getRightChild() {
        return rightChild;
    }

    public void setVal(char val) {
        this.val = val;
    }

    public void setLeftChild(btNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(btNode rightChild) {
        this.rightChild = rightChild;
    }
}

class tree {
    int count = 0;
    //    定义成静态的变量  类名就可以直接访问 不需要new 实例访问
    static int leadCount = 0;

    //    构造一颗树型结构 用来演示一些功能
    public btNode creatTree() {
        btNode A = new btNode('A');
        btNode B = new btNode('B');
        btNode C = new btNode('C');
        btNode D = new btNode('D');
        btNode E = new btNode('E');
        btNode F = new btNode('F');
        btNode G = new btNode('G');
//        btNode H = new btNode('H');
        A.setLeftChild(B);
        A.setRightChild(C);
        B.setLeftChild(D);
        B.setRightChild(E);
        C.setLeftChild(F);
        C.setRightChild(G);
//        E.setRightChild(H);
        return A;
    }

    //    前序遍历 根>左子树>右子树 所以正确的顺序应该是 A-B-D-E-H-C-F-G
    public void preOrder(btNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.getVal() + " ");
        preOrder(root.getLeftChild());
        preOrder(root.getRightChild());
    }

    //    中序遍历 左子树>根>右子树  正确顺序应该是 D-B-E-H-A-F-C-G
    public void inOrder(btNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.getLeftChild());
        System.out.print(root.getVal() + " ");
        inOrder(root.getRightChild());
    }

    //      后序遍历 左子树>右子树>根 正确顺序为 D-H-E-B-F-G-C-A
    public void postOrder(btNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.getLeftChild());
        postOrder(root.getRightChild());
        System.out.print(root.getVal() + " ");
    }

    //    获取树中节点个数
    public int getSize1(btNode root) {
        if (root != null) {
            count++;
            getSize1(root.getLeftChild());
            getSize1(root.getRightChild());
        }
        return count;
    }

    //    高级的获取节点方法 子问题思路
    public int getSize2(btNode root) {
        if (root == null) {
            return 0;
        }
        return getSize2(root.getLeftChild()) + getSize2(root.getRightChild()) + 1;
    }

    //    求叶子节点数量 子问题思路：左子树叶子节点+右子树叶子节点个数 = 总的叶子节点个数
    //                 递归思路: 判断是不是叶子节点 不是就继续遍历 是的话就++
    public int getLeafNodeCount1(btNode root) {
        if (root == null) {
            return 0;
        }
        if (root.getRightChild() == null && root.getRightChild() == null) {
            leadCount++;
        } else {
            getLeafNodeCount1(root.getLeftChild());
            getLeafNodeCount1(root.getRightChild());
        }
        return leadCount;
    }

    public int getLeafNodeCount2(btNode root) {
        if (root == null) {
            return 0;
        }
        if (root.getRightChild() == null && root.getRightChild() == null) {
            //            满足条件的就是叶子节点 计数器+1
            return 1;
        }
        //        这一步相当于是在遍历整个树 前面的条件实在筛选符合条件的内容
        return getLeafNodeCount2(root.getLeftChild()) + getLeafNodeCount2(root.getRightChild());
    }

    //    计算第K层的节点个数
    public int getKLevelNodeCount(btNode root, int k) {
        //    这里还需要知道层数 防止K超出范围 空指针异常 感觉有时间要把层序遍历搞出来
        //    k=1 为递归结束的标志
        if (k <= 0 || k > getHeight(root)) {
            return 0;
        }
        if (k == 1) {
            if (root == null) {
                return 0;
            }
            return 1;
        }
        //    相当于层层遍历
        return getKLevelNodeCount(root.getLeftChild(), k - 1) + getKLevelNodeCount(root.getRightChild(), k - 1);
    }

    //    获取二叉树的高度 (左树高度与右树高数的最大值 再+1)
    public int getHeight(btNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.getLeftChild()), getHeight(root.getRightChild())) + 1;
    }

    //    检测是否包含某元素
    public btNode isContain(btNode root, char val) {
        if (root == null) {
            return null;
        }
        if (root.getVal() == val) {
//            System.out.println(val);
            return root;
        }
        btNode ret1 = isContain(root.getLeftChild(), val);
        if (ret1 != null) {
            return ret1;
        }
        btNode ret2 = isContain(root.getRightChild(), val);
        if (ret2 != null) {
            return ret2;
        }
        return null;
    }

    //    判断一棵树是不是完全二叉树
//    需要借助队列，将元素一次放入队列，每次弹出一个元素，判断是否为空  ，不为空就需要放它的左右节点进队列。
//   为空的时候如果队列不全是null 则不是完全二叉树。如果是二叉树，应满足在栈顶元素为null时，队列中全是null。
    public boolean isCompleteTree(btNode root) {
//      创建一个数组
        Queue<btNode> queue = new LinkedList<>();
        btNode cur = root;
        queue.offer(cur);
//        循环 添加/弹出 树中元素进队列 当栈顶元素为空时，跳出循环 进入判断
        while (!queue.isEmpty()) {
//            出队列
            btNode ele = queue.poll();
//            作比较 当栈顶元素为空时 跳出循环 开始做判断
            if (ele != null) {
//                加入根的左右子树 如果其左右子树为null  也是可以将null加入进队列的
                queue.offer(ele.getLeftChild());
                queue.offer(ele.getRightChild());
            } else {
                break;
            }
        }
//       检查队列中是否有非空元素 有则就判定为不完全二叉树 无则判定为完全二叉树
        for (btNode a : queue) {
//            有一个非空 就说明是不完全二叉树
            if (a != null) {
                return false;
            }
        }
//        到这一步说明 全是null 为完全二叉树
        return true;
    }

    //层序遍历 借鉴
    public void levelOrderTraverse(btNode root) {
        if (root != null) {
            LinkedList<btNode> linkedList = new LinkedList<>();
            linkedList.offer(root);
            while (!linkedList.isEmpty()) {
                root = linkedList.poll();
                if (root.getLeftChild() != null) {
                    linkedList.offer(root.getLeftChild());
                }
                if (root.getRightChild() != null) {
                    linkedList.offer(root.getRightChild());
                }
                System.out.print(root.getVal() + " ");
            }
            System.out.println();
        }
    }

    //    层序遍历 自己实现  大致思路就是借助一个队列 将非空元素放入队列 每次弹出一个元素并判断其是否为空 不为空则添加左右孩子进队列
    public void layerOrder(btNode root) {
        //        创建队列
        Queue<btNode> queue = new LinkedList<>();
        //        入队列
        queue.offer(root);
        //        进入循环
        while (!queue.isEmpty()) {
            btNode cur = queue.poll();
            if (cur.getLeftChild() != null) {
                queue.offer(cur.getLeftChild());
            }
            if (cur.getRightChild() != null) {
                queue.offer(cur.getRightChild());
            }
            System.out.print(cur.getVal() + " ");
        }
        System.out.println();
    }

    /**
     * 判断两棵树是否完全相同
     * 非递归方法
     */
    //判断两棵树是否完全相同 完全相同就包含结构+值都要相同
    public boolean isSameTree1(btNode p, btNode q) {
//        判断结构
        if (p == null && q == null) {
            return true;
        }
//        判断结构
        if (p == null && q != null || p != null && q == null) {
            return false;
        }
//        判断值
        if (p.getVal() != q.getVal()) {
            return false;
        }
//   如果两棵树的根都存在且值都相等 那么就需要对两棵树进行遍历 比较结构 和 值
//        这里选择层序遍历试试看
        Queue<btNode> queue1 = new LinkedList<>();
        Queue<btNode> queue2 = new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            btNode root1 = queue1.poll();
            btNode root2 = queue2.poll();
//            判断
            if (root1 == null && root2 != null || root1 != null && root2 == null) {
                return false;
            }
//            空节点就跳过
            if (root1 == null && root2 == null) {
                continue;
            }
//            到这一步说明结构没有问题 比较值 如果值相同 那么就把它的左右子树入队列。 同样下一次循环过来就会出对列 判断它是不是结构和值都相同
            if (root1.getVal() == root2.getVal()) {
                queue1.offer(root1.getLeftChild());
                queue1.offer(root1.getRightChild());
                queue2.offer(root2.getLeftChild());
                queue2.offer(root2.getRightChild());
            } else {
//                值不同  不符合条件
                return false;
            }
        }
//        循环正常退出来 就说明所有节点都是经得起考研的  完全相同
        return true;
    }

    /**
     * 判断两棵树是否完全相同
     * 递归方法
     */
    public boolean isSameTree2(btNode p, btNode q) {
        //        判断结构
        if (p == null && q == null) {
            return true;
        }
//        判断结构
        if (p == null && q != null || p != null && q == null) {
            return false;
        }
//        判断值
        if (p.getVal() != q.getVal()) {
            return false;
        }
//        到这一步说明两个节点都是非空节点 且 值相同。进入递归就是一直判断左子树的节点是否结构相同以及值相同 直至到空节点后return回来 进入右子树的节点，从下网上递归回来。
//        要想return true  必须所有节点都返回ture才可以。
        return isSameTree2(p.getLeftChild(), q.getLeftChild()) && isSameTree2(p.getRightChild(), q.getRightChild());
    }

    //    判断subroot是否为root 的子树 子树就是完全一样的树
    public boolean isSubTree(btNode root, btNode subroot) {
        if (root == null) {
            return false;
        }
        return isSameTree2(root,subroot)||isSubTree(root.getLeftChild(),subroot)||isSubTree(root.getRightChild(),subroot);
    }

//    平衡二叉树 (条件 ： 1.左树与右树的高度差小于二 2.所有子树都是平衡二叉树)
    public boolean isBalanced(btNode root){
        if (root==null){
            return true;
        }
        int i = getHeight(root.getLeftChild());
        int j = getHeight(root.getRightChild());
        if (Math.abs(i-j)>1){
            return false;
        }else {
            return isBalanced(root.getLeftChild())&&isBalanced(root.getRightChild());
        }
    }
}

public class BinaryTree {
    public static void main(String[] args) {
        tree t = new tree();
        btNode root = t.creatTree();
        System.out.print("前序遍历 : ");
        t.preOrder(root);
        System.out.println();
        System.out.print("中序遍历 : ");
        t.inOrder(root);
        System.out.println();
        System.out.print("后序遍历 : ");
        t.postOrder(root);
        int size = t.getSize1(root);
        System.out.println();
        System.out.print("获取结点的个数方法 (递归) : ");
        System.out.println(size);
        System.out.print("获取结点的个数方法 (子问题) : ");
        System.out.println(t.getSize2(root));
        System.out.print("叶子节点的个数 (递归) : ");
        System.out.println(t.getLeafNodeCount1(root));
        System.out.print("叶子节点的个数 (子问题) : ");
        System.out.println(t.getLeafNodeCount2(root));
        System.out.print("计算第K层的节点个数 : ");
        System.out.println(t.getKLevelNodeCount(root, 4));
        System.out.print("计算树的高度 : ");
        System.out.println(t.getHeight(root));
        System.out.print("计算层序遍历 :");
        t.levelOrderTraverse(root);
        System.out.print("计算层序遍历 (自己复现) :");
        t.layerOrder(root);
        System.out.print("检测值为 value 的元素是否 存在 : ");
        try {
            System.out.println(t.isContain(root, 'e').getVal());
        } catch (NullPointerException e) {
            System.out.println("当前二叉树不包含这个元素");
        }
        System.out.print("判断是否为完全二叉树 : ");
        System.out.println(t.isCompleteTree(root));

    }
}
