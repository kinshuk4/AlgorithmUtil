package com.vaani.algo.ds.algos.list.linked;

import com.vaani.algo.ds.core.list.ListNode;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * <p>
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 * <p>
 * Created by Xiaomeng on 7/12/2014.
 */
public class RemoveDuplicates2 {
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode curr = new ListNode(-1);
        curr.next = head;
        head = curr;

        while (curr.next != null) {
            ListNode end = curr.next;
            while (end.next != null && (end.val == end.next.val)) {
                end = end.next;
            }
            if (end == curr.next) {
                curr = curr.next;
            } else {
                curr.next = end.next;
            }
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode l = new ListNode(1);
        l.next = new ListNode(1);
        l.next.next = new ListNode(1);
        l.next.next.next = new ListNode(2);
        l.next.next.next.next = new ListNode(3);
        l.display();

        ListNode n = deleteDuplicates(l);
        n.display();
    }
}
