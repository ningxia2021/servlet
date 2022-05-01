package DataStructure;

/**
 * 单向链表
 */
public class demo2 {

    /**
     * 定义什么是节点
     */
    static class Node {
        private String data;
        private Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * 定义链表，由很多Node组成的链表
     */
    static class listNode {
        private Node head;
        private Node tail;
        private int size;

        public listNode() {
            this.head = null;
            this.tail = null;
        }
//         下面就是定义链表的功能

        /**
         * isEmpty
         */
        public boolean isEmpty() {
            if (head == null) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * 尾插
         */
        public void tailInsert(String data) {
            Node node = new Node(data);
            if (isEmpty()) {
                head = node;
                tail = head;
                size++;
            } else {
                tail.next = node;
                tail = node;
                size++;
            }
        }

        /**
         * 查看
         */
        public void disPlayAll() {
            Node node = head;
            for (int i = 0; i < size; i++) {
                System.out.print(node.data + " ");
                if (node.next == null) {
                    break;
                }
                node = node.next;
            }
            System.out.println();
        }

        /**
         * 头插
         */
        public void headInsert(String data) {
            Node node = new Node(data);
            if (isEmpty()) {
                head = node;
                tail = head;
                size++;
            } else {
                node.next = head;
                head = node;
                size++;
            }
        }

        /**
         * 指定位置随便插
         */
        public void insert(int pos, String data) {
//            定义一个Node接收数据
            Node node = new Node(data);
            if (isEmpty()) {
                headInsert(data);
                return;
            }
            if (pos == size) {
                tailInsert(data);
                return;
            }

            if (pos > size || pos < 1) {
                System.out.println("pos不合法");
                return;
            }
//            中间插入
            Node cur = new Node(null);
            Node nex = new Node(null);
            cur = head;
            if (pos == 1) {
                if (head.next == null) {
                    tailInsert(data);
                    size++;
                } else {
                    nex = cur.next;
                    cur.next = node;
                    node.next = nex;
                    size++;
                }
                return;
            }
//            找pos位置的节点
            for (int i = 1; i < pos; i++) {
//                链表不同于数组，它没有下标，无法直接得到要操作的哪个节点的位置，因此需要有个顺藤摸瓜的过程
                cur = cur.next;
            }
            if (cur.next == null) {
//                cur是最后一个节点，尾插
                tailInsert(data);
            } else {
                nex = cur.next;
                cur.next = node;
                node.next = nex;
                size++;
            }
        }

        /**
         * 删除节点
         */
        public void del(int pos) {
            if (isEmpty() || pos > size || pos < 1) {
                System.out.println("pos无效");
                return;
            }
            if (head.next == null) {
                head = null;
                size--;
                return;
            }
            if (pos == 1 && head.next != null) {
                head = head.next;
                size--;
                return;
            }
            Node node = new Node(null);
            Node nex = new Node(null);
            node = head;
            for (int i = 1; i < pos - 1; i++) {
                node = node.next;
            }
//            找到了要删除的位置 的前一个位置
            if (node.next.next == null) {
                node.next = null;
            } else {
                nex = node.next.next;
                node.next = nex;
                size--;
            }
        }

        /**
         * 修改Node的参数
         */
        public void updateNode(int pos, String data) {
            Node node = new Node(data);
            Node cur = new Node(null);
            cur = head;
            if (isEmpty()){
                System.out.println("空链表，修改个毛");
                return;
            }
            if (pos>size || pos<1){
                System.out.println("pos 不合法");
                return;
            }
            if (pos==1){
                head.data = data;
            }
            if (pos == size){
                tail.data=data;
            }
            for (int i = 1;i<pos;i++){
                cur = cur.next;
            }
            cur.data=data;
        }

        /**
         * 查看链表长度
         */
        public int lenth(){
            return size;
        }
    }

    public static void main(String[] args) {
        listNode listNode = new listNode();
        System.out.println(listNode.isEmpty());
        listNode.tailInsert("1");
        listNode.tailInsert("2");
        listNode.tailInsert("3");
        listNode.tailInsert("4");
        listNode.disPlayAll();
        listNode.insert(1, "777");
        listNode.del(2);
        listNode.disPlayAll();
        listNode.updateNode(5,"9");
        listNode.disPlayAll();
        System.out.println(listNode.lenth());
    }
}
