package com.vaani.algo.ds.algos.list.linked;

import com.vaani.algo.ds.core.list.ListNode;

public class LinkedListCheckCycle {
    public boolean hasCycle(ListNode head) {

        ListNode slowNode = head;
        ListNode fastNode = head;
        if (head != null && head.next != null) {
            fastNode = head.next;
        } else {
            return false;
        }

        while (slowNode != null && fastNode != null) {
            if (slowNode.val == fastNode.val) return true;

            slowNode = slowNode.next;
            fastNode = fastNode.next;
            if (fastNode != null) {
                fastNode = fastNode.next;
            }
        }

        return false;
    }


}
