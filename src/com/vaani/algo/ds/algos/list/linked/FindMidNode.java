package com.vaani.algo.ds.algos.list.linked;

import com.vaani.algo.ds.core.list.ListNode;

import static com.vaani.algo.ds.utils.ListUtil.getMidNode;

public class FindMidNode {


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode((2));
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode((4));
        head.next.next.next.next = new ListNode((5));
        System.out.println(getMidNode(head).val);
    }
}
