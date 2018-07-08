package com.vaani.algo.compete.cc150.chap2;

import com.vaani.algo.ds.core.list.ListNode;

/**
 * Write code to remove duplicates from an unsorted linked list.
 * <p>
 * FOLLOW UP
 * <p>
 * How would you solve this problem if a temporary buffer is not allowed?
 */
public class RemoveDuplicateFromUnsortedList {

    public void removeDuplicateWithoutBuffer(ListNode head) {
        // write implementation here
        if (head == null) {
            return;
        }
        ListNode cur = head;
        while (cur != null) {
            ListNode runner = cur;

            while (runner.next != null) {
                if (runner.next.val == cur.val) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }

            cur = cur.next;
        }
    }

}
