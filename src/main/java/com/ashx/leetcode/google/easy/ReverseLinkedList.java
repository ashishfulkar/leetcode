package com.ashx.leetcode.google.easy;

import com.ashx.leetcode.google.model.ListNode;

public class ReverseLinkedList {
    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode reverseList1(ListNode head) {
        return recur(head);
    }

    private ListNode recur(ListNode node) {
        if (node == null) {
            return node;
        }
        ListNode next = recur(node.next);
        if (next == null) {
            return node;
        }
        node.next = null;

        ListNode curr = next;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = node;

        return next;
    }
}
