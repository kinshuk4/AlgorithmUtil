package com.vaani.algo.ds.list.linked;

import com.leetcode.core.ListNode;

/**
 *  Given a linked list, determine if it has a cycle in it.
 *  Follow up:
 *  Can you solve it without using extra space?
 *
 *  Created by Xiaomeng on 5/22/2014.
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while(fast != null && fast.next != null){
            if(slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
