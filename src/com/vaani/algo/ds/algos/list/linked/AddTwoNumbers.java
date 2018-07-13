package com.vaani.algo.ds.algos.list.linked;

import com.vaani.algo.ds.core.list.ListNode;

/**
 * You are given two linked lists representing two non-negative numbers.
 * The digits are stored in reverseIterative order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * <p>
 * 
 */
public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode<Integer> l1, ListNode<Integer> l2) {
        ListNode<Integer> dummy = new ListNode(-1);
        ListNode<Integer> dummyHead = dummy;
        int extra = 0;
        while (l1 != null && l2 != null) {
            dummy.next = new ListNode((l1.val + l2.val + extra) % 10);
            extra = l1.val + l2.val + extra > 9 ? 1 : 0;
            dummy = dummy.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        ListNode<Integer> n = l1 != null ? l1 : l2;
        while (n != null) {
            dummy.next = new ListNode<Integer>((n.val + extra) % 10);
            extra = n.val + extra > 9 ? 1 : 0;
            n = n.next;
            dummy = dummy.next;
        }
        if (extra == 1) dummy.next = new ListNode<Integer>(1);
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode<Integer> l1 = new ListNode<Integer>(2);
        l1.next = new ListNode<Integer>(4);
        l1.next.next = new ListNode<Integer>(3);

        ListNode l2 = new ListNode<Integer>(5);
        l2.next = new ListNode<Integer>(6);
        l2.next.next = new ListNode<Integer>(4);

        addTwoNumbers(l1, l2).display();
    }
}
