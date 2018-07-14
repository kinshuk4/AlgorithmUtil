package com.vaani.algo.ds.algos.list.linked;

import com.vaani.algo.ds.core.list.ListNode;

/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 * <p>
 */
public class MergeTwoSortedLists {
    public static ListNode mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2) {
        ListNode<Integer> dummyHead = new ListNode<>(0);
        ListNode<Integer> curr = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = l1 == null ? l2 : l1;
        return dummyHead.next;
    }

    public ListNode mergeTwoListsRecursive(ListNode<Integer> l1, ListNode<Integer> l2) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l2.next, l1);
            return l2;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);
        l1.display();

        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(6);
        l2.next.next.next = new ListNode(7);
        l2.display();

        ListNode n = mergeTwoLists(l1, l2);
        n.display();
    }
}
