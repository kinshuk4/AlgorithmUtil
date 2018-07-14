package com.vaani.algo.ds.algos.list.linked;

import com.vaani.algo.ds.core.list.ListNode;

/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * <p>
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 * <p>
 * Created by Xiaomeng on 7/16/2014.
 */
public class RotateList {
    public static ListNode rotateRight(ListNode head, int n) {
        if (n == 0 || head == null || head.next == null) return head;

        ListNode node = head, first = head, second = head;
        int len = 0;
        while (node != null) {
            node = node.next;
            len++;
        }

        n = n % len;
        if (n == 0) return head;

        for (int i = 0; i < n; i++) second = second.next;

        while (second.next != null) {
            first = first.next;
            second = second.next;
        }

        ListNode result = first.next;
        first.next = null;
        second.next = head;
        return result;
    }

    public static void main(String[] args) {
        ListNode l = new ListNode(1);
        l.next = new ListNode(2);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(4);
        l.next.next.next.next = new ListNode(5);
        l.display();

        ListNode n = rotateRight(l, 8);
        n.display();
    }
}
