package com.vaani.algo.ds.algos.list.linked;

import com.vaani.algo.ds.core.list.RandomListNode;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * <p>
 * Return a deep copy of the list.
 * <p>
 */
public class CopyListWithRandomPointer {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return head;
        RandomListNode n = head;
        while (n != null) {
            RandomListNode temp = new RandomListNode(n.label);
            temp.next = n.next;
            n.next = temp;
            n = n.next.next;
        }
        RandomListNode result = head.next;

        n = head;
        while (n != null) {
            if (n.random != null) n.next.random = n.random.next;
            n = n.next.next;
        }

        n = head;
        while (n != null) {
            RandomListNode temp = n.next;
            n.next = temp.next;
            if (temp.next != null) temp.next = temp.next.next;
            n = n.next;
        }
        return result;
    }
}
