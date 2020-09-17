package com.transformers.solution.linkedlist;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Solution141 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        Set<ListNode> container = new HashSet<>();
        while (head != null) {
            if (container.contains(head)) {
                return true;
            }
            container.add(head);
            head = head.next;
        }
        return false;
    }

    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != null && fast != null && fast.next != null) {
            if (Objects.equals(slow, fast)) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
