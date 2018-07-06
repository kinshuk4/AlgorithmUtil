package com.vaani.algo.compete.cc150.chap2;

import com.vaani.algo.ds.core.list.ListNode;

/**
 * Write code to partition a linked list around a value x, such that all nodes
 * less than x come before all nodes greater than or equal to x.
 */
// O(n) space, O(n) time
public class PartitionList {

    /**
     * Return a pair of lists, where the first list contains the nodes whose
     * elements are less than x, and the second list contains the nodes whose
     * elements are greater or equal than x.
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode<Integer> head, int x) {
        // write implementation here
        if (head == null) {
            return null;
        }

        ListNode<Integer> headL = new ListNode(0);
        ListNode<Integer> headGe = new ListNode(0);
        ListNode<Integer> headLCur = headL;
        ListNode<Integer> headGeCur = headGe;
        ListNode<Integer> cur = head;
        while (cur != null) {
            if (cur.val < x) {
                headLCur.next = new ListNode(cur.val);
                headLCur = headLCur.next;
            } else {
                headGeCur.next = new ListNode(cur.val);
                headGeCur = headGeCur.next;
            }
            cur = cur.next;
        }
        headLCur.next = headGe.next;
        return headL.next;
    }

}


