package com.transformers.solution.linkedlist;

public class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 哨兵节点
        ListNode result = new ListNode(n);
        result.next = head;
        ListNode pre = null;
        ListNode cur = result;
        int index = 1;
        while (head != null) {
            // 保证head和cur之间间隔n-1
            if (index >= n) {
                pre = cur;
                cur = cur.next;
            }
            head = head.next;
            index++;
        }
        pre.next = pre.next.next;
        return result.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        int index = 0;
        while (first != null) {
            // 执行n+1次后才进入
            if (index > n) {
                second = second.next;
            }
            first = first.next;
            index++;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 0; i < n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
