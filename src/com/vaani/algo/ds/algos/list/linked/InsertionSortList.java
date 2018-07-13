package com.vaani.algo.ds.algos.list.linked;

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
        ListNode head = new ListNode<Integer>(3);
        head.next = new ListNode<Integer>(1);
        head.next.next = new ListNode<Integer>(5);
        head.next.next.next = new ListNode<Integer>(4);
        head.next.next.next.next = new ListNode<Integer>(2);

        InsertionSortList test = new InsertionSortList();
        test.insertionSortList(head).display();
    }

    public ListNode insertionSortList(ListNode<Integer> head) {
        if (head == null || head.next == null) return head;

        ListNode<Integer> dummy = new ListNode<Integer>(Integer.MIN_VALUE);
        ListNode<Integer> curr = head;

        while (curr != null) {
            ListNode<Integer> prev = dummy;
            ListNode<Integer> next = curr.next;

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
