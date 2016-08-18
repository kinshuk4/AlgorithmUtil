package com.vaani.algo.ds.list.linked;

import com.vaani.algo.ds.core.ListNode;

/**
 * Created by Xiaomeng on 10/9/2014.
 */
public class FindMidNode {

    public static ListNode findMid(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode first = head;
        ListNode second = head;
        while (second.next != null) {
            second = second.next;
            if (second.next != null) {
                second = second.next;
                first = first.next;
            }
        }
        return first;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode((2));
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode((4));
        head.next.next.next.next = new ListNode((5));
        System.out.println(findMid(head).val);
    }
}
