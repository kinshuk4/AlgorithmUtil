package com.vaani.algo.ds.list.linked;

import com.vaani.algo.ds.core.list.ListNode;

/**
 * Sort a linked list using insertion sort.
 * <p>
 * Reference: http://www.binglu.me/leetcode-question-insertion-sort-list/
 * <p>
 * Created by Xiaomeng on 8/4/2014.
 */
public class InsertionSortList {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(1);
        head.next.next = new ListNode(5);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);

        InsertionSortList test = new InsertionSortList();
        test.insertionSortList(head).display();
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode curr = head;

        while (curr != null) {
            ListNode prev = dummy;
            ListNode next = curr.next;

            while (prev.next != null && prev.next.val < curr.val) {
                prev = prev.next;

            }
            curr.next = prev.next;
            prev.next = curr;
            curr = next;
        }
        return dummy.next;
    }
}
