package com.transformers.algo.linkedlist_06;

/**
 * 1）单链表的插入、删除、查找操作；
 * 2）链表中存储的是int类型的数据；
 *
 * 判断链表数据是否为回文
 */
public class SinglyLinkedList {

    private Node head;

    public Node findByValue(int value) {
        Node p = head;
        while (p != null && p.data != value) {
            p = p.next;
        }
        return p;
    }

    public Node findByIndex(int index) {
        Node p = head;
        int pos = 0;
        while (p != null && pos != index) {
            p = p.next;
            ++pos;
        }
        return p;
    }

    /**
     * 无头结点
     * 表头部插入
     * 与输入的顺序相反，逆序
     *
     * @param value
     */
    public void insertToHead(int value) {
        Node newNode = new Node(value, null);
        insertToHead(newNode);
    }

    private void insertToHead(Node newNode) {
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    /**
     * 链表尾部插入
     * 顺序插入
     *
     * @param value
     */
    public void insertTail(int value) {
        Node newNode = new Node(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node node = head;
            while (node.next != null) {
                node = node.next;
            }
//            newNode.next = node.next;
            node.next = newNode;
        }
    }

    public void insertAfter(Node node, int value) {
        Node newNode = new Node(value, null);
        if (node == null) {
            return;
        }
        newNode.next = node.next;
        node.next = newNode;
    }

    public void insertBefore(Node node, int value) {
        Node newNode = new Node(value, null);
        if (node == null) {
            return;
        }
        if (head == node) {
            insertToHead(newNode);
            return;
        }
        Node q = head;
        while (q != null && q.next != node) {
            q = q.next;
        }
        if (q == null) {
            return;
        }
        newNode.next = node;
        q.next = newNode;
    }

    public void deleteByNode(Node node) {
        if (node == null || head == null) {
            return;
        }
        if (node == head) {
            head = head.next;
            return;
        }
        Node tmp = head;
        while (tmp != null && tmp.next != node) {
            tmp = tmp.next;
        }
        if (tmp == null) {
            return;
        }
        tmp.next = node.next;
    }

    public void deleteByValue(int value) {
        if (head == null) {
            return;
        }
        Node node = head;
        Node tmp = null;
        while (node != null && node.data != value) {
            tmp = node;
            node = node.next;
        }
        if (node == null) {
            return;
        }
        if (tmp == null) {
            // value为头结点数据
            head = head.next;
        } else {
            tmp.next = tmp.next.next;
        }
    }

    public void printAll() {
        Node node = head;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    // 判断是否为回文
    public boolean palindrome() {
        if (head == null) {
            return false;
        }
        System.out.println("开始执行找到中间节点");
        // 快慢指针
        Node p = head;
        Node q = head;

        if (p.next == null) {
            System.out.println("只有一个元素");
            return true;
        }
        while (q.next != null && q.next.next != null) {
            p = p.next;
            q = q.next.next;
        }
        System.out.println("中间节点" + p.data);
        Node leftLink;
        Node rightLink;
        if (q.next == null) {
            System.out.println("开始执行奇数节点的回文判断");
            // p一定为整个链表的中点，且节点数目为奇数
            rightLink = p.next;
            leftLink = inverseLinkList(p).next;
            System.out.println("左边第一个节点" + leftLink.data);
            System.out.println("右边第一个节点" + rightLink.data);
        } else {
            System.out.println("开始执行偶数节点的回文判断");
            // p q 均为中点
            rightLink = p.next;
            leftLink = inverseLinkList(p);
        }
        return result(leftLink, rightLink);
    }

    private boolean result(Node leftLink, Node rightLink) {
        Node l = leftLink;
        Node r = rightLink;

        boolean flag = true;
        System.out.println("left " + l.data);
        System.out.println("right " + r.data);
        while (l != null && r != null) {
            if (l.data == r.data) {
                l = l.next;
                r = r.next;
                continue;
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 无头节点的链表翻转
     *
     * @param p
     * @return
     */
    private Node inverseLinkList(Node p) {
        Node pre = null;
        Node r = head;
        System.out.println("z---" + r.data);
        Node next = null;
        /**
         * 从头结点到p结点，倒序后，赋值给r
         * 如原链表为
         * 1 2 3 4 5 6
         * 此时传入的p为3
         * 最终返回的r为
         * 3 2 1
         * pre负责收集倒序的链表
         * r负责链表指针，依次向后，直至找到p节点位置
         * next临时节点
         */
        while (r != p) {
            next = r.next;

            r.next = pre;
            pre = r;
            r = next;
        }
        r.next = pre;
        // 返回左半部分的中点之前的那个节点
        // 从此处开始同步向两边比较
        return r;
    }

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList link = new SinglyLinkedList();
        System.out.println("hello");
        int data[] = {1, 2, 3, 4, 5, 6};

        for (int i = 0; i < data.length; i++) {
            link.insertTail(data[i]);
        }
        System.out.println("打印原始:");
        link.printAll();
        if (link.palindrome()) {
            System.out.println("回文");
        } else {
            System.out.println("不是回文");
        }
    }
}
