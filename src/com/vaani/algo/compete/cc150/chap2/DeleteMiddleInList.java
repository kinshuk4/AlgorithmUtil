package com.vaani.algo.compete.cc150.chap2;

import com.vaani.algo.ds.core.list.ListNode;

/**
 * Implement an algorithm to delete a node in the middle of a singly linked
 * list, given only access to that node.
 * <p>
 * <p>
 * EXAMPLE
 * <p>
 * Input: the node c from the linked list a ->b ->c ->d ->e
 * <p>
 * Result: nothing is returned, but the new linked list looks like a ->b ->d ->e
 */
public class DeleteMiddleInList {

    public ListNode deleteMiddle(ListNode head) {
        // write implementation here
        // 1. count length 2. remove the middle
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            ++count;
            cur = cur.next;
        }
        if (count == 2) {  // remove all two
            return null;
        }
        cur = head;
        for (int i = 0; i < (count - 1) / 2 - 1; ++i) {
            cur = cur.next;
        }
        if (count % 2 == 1) {
            cur.next = cur.next.next;
        } else {
            cur.next = cur.next.next.next;
        }
        return head;
    }

}

