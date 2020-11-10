package com.transformers.algo.linkedlist_06;

/**
 * 基于单链表LRU算法
 */
public class LRUBaseLinkedList<T> {

    private static final int DEFAULT_CAPACITY = 8;

    /**
     * 链表容量
     */
    private int capacity;

    /**
     * 链表长度
     */
    private int length;

    /**
     * 头结点
     */
    private SNode<T> headNode;

    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public LRUBaseLinkedList(int capacity) {
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    public void add(T data) {
        SNode preNode = findPreNode(data);

        // 链表中存在，删除原数据，再插入到链表的头部
        if (preNode != null) {
            deleteElemOptim(preNode);
            insertElemAtBegin(data);
        } else {
            if (length >= this.capacity) {
                deleteElemAtEnd();
            }
            insertElemAtBegin(data);
        }
    }

    /**
     * 删除尾结点
     */
    private void deleteElemAtEnd() {
        SNode ptr = headNode;
        // 空链表直接返回
        if (ptr.getNext() == null) {
            return;
        }
        // 倒数第二个节点
        while (ptr.getNext().getNext() != null) {
            ptr = ptr.getNext();
        }

        SNode tmp = ptr.getNext();
        ptr.setNext(null);
        tmp = null;
        length--;
    }

    /**
     * 链表头部插入节点
     *
     * @param data
     */
    private void insertElemAtBegin(T data) {
        SNode next = headNode.getNext();
        headNode.setNext(new SNode(data, next));
        length++;
    }

    /**
     * 删除preNode节点下一个元素
     *
     * @param preNode
     */
    private void deleteElemOptim(SNode preNode) {
        SNode temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        length--;
    }

    /**
     * 获取查找到元素的前一个节点
     *
     * @param data
     * @return
     */
    private SNode findPreNode(T data) {
        SNode node = headNode;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getElement())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    public void printAll() {
        SNode node = headNode.getNext();
        while (node != null) {
            System.out.print(node.getElement() + ",");
            node = node.getNext();
        }
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        SNode node = headNode.getNext();
        if (node == null) {
            return "";
        }
        while (node.getNext() != null) {
            builder.append(node.getElement());
            builder.append(" ");
            node = node.getNext();
        }
        builder.append(node.getElement());
        builder.append(" ");
        return builder.toString();
    }

    public class SNode<T> {
        private T element;

        private SNode next;

        public SNode() {
        }

        public SNode(T element) {
            this.element = element;
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUBaseLinkedList<Integer> list = new LRUBaseLinkedList<>(4);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list);
        list.add(1);
        System.out.println(list);
        list.add(5);
        System.out.println(list);
        list.add(7);
        System.out.println(list);
        list.add(1);
        System.out.println(list);
    }

}
