package com.vaani.algo.ds.list.linked;

import com.leetcode.core.ListNode;

/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 *
 * For example,
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 *
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 *
 * Created by Xiaomeng on 7/11/2014.
 */
public class RemoveNthFromEnd {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return null;

        ListNode first = head, second = head;

        for(int i = 0; i < n; i++) second = second.next;

        if(second == null) return first.next;

        while(second.next != null){
            first = first.next;
            second = second.next;
        }

        first.next = first.next.next;
        return head;
    }

    public static void main(String[] args){
        ListNode l = new ListNode(1);
        l.next = new ListNode(2);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(4);
        l.next.next.next.next = new ListNode(5);
        l.display();

        ListNode n = removeNthFromEnd(l, 1);
        n.display();
    }
}
