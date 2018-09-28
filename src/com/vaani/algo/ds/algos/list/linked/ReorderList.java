package com.vaani.algo.ds.algos.list.linked;

import com.vaani.algo.ds.core.list.ListNode;

import static com.vaani.algo.ds.utils.ListUtil.reverseIterative;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * You must do this in-place without altering the nodes' values.
 * <p>
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 * <p>
 */
public class ReorderList {
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;

        ListNode n = head;
        int len = 1;
        while (n.next != null) {
            n = n.next;
            len++;
        }

        ListNode partition = head;
        for (int i = 0; i < len / 2 - 1; i++) partition = partition.next;
        ListNode second = partition.next;
        partition.next = null;
        second = reverseIterative(second);

        ListNode first = head;
        ListNode dummy = new ListNode(-1);
        while (first != null && second != null) {
            dummy.next = first;
            first = first.next;
            dummy.next.next = second;
            second = second.next;
            dummy = dummy.next.next;
        }
        dummy.next = first == null ? second : first;
    }



    public static void main(String[] args) {
        ListNode l = new ListNode(1);
        l.next = new ListNode(2);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(4);
        l.next.next.next.next = new ListNode(5);
        l.display();

        reorderList(l);
        l.display();
    }

}
