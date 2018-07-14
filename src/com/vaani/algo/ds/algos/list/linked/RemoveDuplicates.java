package com.vaani.algo.ds.algos.list.linked;

import com.vaani.algo.ds.core.list.ListNode;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * <p>
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 * <p>
 * Created by Xiaomeng on 7/12/2014.
 */
public class RemoveDuplicates {
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode n = head;
        while (n != null && n.next != null) {
            if (n.val == n.next.val)
                n.next = n.next.next;
            else
                n = n.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l = new ListNode(1);
        l.next = new ListNode(1);
        l.next.next = new ListNode(2);
        l.next.next.next = new ListNode(3);
        l.next.next.next.next = new ListNode(3);
        l.display();

        ListNode n = deleteDuplicates(l);
        n.display();
    }
}
