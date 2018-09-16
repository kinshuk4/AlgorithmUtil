package com.vaani.algo.compete.cc150.chap2;


import com.vaani.algo.ds.core.list.ListNode;

import static com.vaani.algo.ds.utils.ListUtil.reverseIterative;

/**
 * Implement an algorithm to find the kth to last element of a singly linked
 * list.
 */
// O(1) space, O(n) time
public class KthToLastInList {


    public <T> ListNode<T> findKthToLast(int k, ListNode<T> head) {
        // write implementation here
        ListNode<T> newHead = reverseIterative(head);
        ListNode<T> kthListNode = newHead;
        for (int i = 0; i < k; ++i) {
            kthListNode = kthListNode.next;
        }
        return kthListNode;
    }


}
