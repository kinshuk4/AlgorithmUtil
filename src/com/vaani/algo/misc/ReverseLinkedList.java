package com.vaani.algo.misc;

public class ReverseLinkedList {
    //interative
    public ListNode reverseLinkedListIter(ListNode head) {
        if (head == null) return null;
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head = pre;
        return head;
    }

    //recursive
    public ListNode reverseLinkedListRecursive(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode newHead = reverseLinkedListRecursive(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }
}