package com.vaani.algo.ds.list.linked;

import java.util.ArrayList;
import java.util.List;

import com.vaani.algo.ds.core.ListNode;

/**
 * Get the union of two sorted linked list.
 */
public class UnionOfSortedLinkedList {

    // O(n + m) space, O(m + n) time
    public List<Integer> unionOfSortedLinkedList(ListNode l1, ListNode l2) {
        ListNode curL1 = l1;
        ListNode curL2 = l2;

        List<Integer> res = new ArrayList<Integer>();

        while (curL1 != null && curL2 != null) {
            if (curL1.val < curL2.val) {
                curL1 = curL1.next;
            } else if (curL1.val > curL2.val) {
                curL2 = curL2.next;
            } else {
                res.add(curL1.val);
                curL1 = curL1.next;
                curL2 = curL2.next;
            }
        }

        return res;
    }

    

}
