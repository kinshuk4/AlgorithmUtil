package com.vaani.algo.ds.list.linked;

import com.vaani.algo.ds.core.list.ListNode;

public class FindMidNode {

    public static ListNode getMidNode(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode((2));
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode((4));
        head.next.next.next.next = new ListNode((5));
        System.out.println(getMidNode(head).val);
    }
}
