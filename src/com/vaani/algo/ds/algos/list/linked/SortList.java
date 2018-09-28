package com.vaani.algo.ds.algos.list.linked;

import com.vaani.algo.ds.core.list.ListNode;
import com.vaani.algo.ds.utils.ListUtil;


/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * <p>
 * 
 */
public class SortList {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(1);
        head.next.next = new ListNode(5);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);

        SortList test = new SortList();
        test.sortList(head).display();
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode partition = ListUtil.getMidNode(head);
        ListNode mid = partition.next;
        partition.next = null;

        ListNode first = sortList(head);
        ListNode second = sortList(mid);
        return mergeIterative(first, second);
//        return mergeRecursive(first, second);
    }

    public ListNode mergeIterative(ListNode<Integer> first, ListNode<Integer> second) {
        ListNode dummy = new ListNode(-1);
        ListNode dummyHead = dummy;
        while (first != null && second != null) {
            if (first.val < second.val) {
                dummy.next = first;
                first = first.next;
            } else {
                dummy.next = second;
                second = second.next;
            }
            dummy = dummy.next;
        }
        dummy.next = first == null ? second : first;
        return dummyHead.next;
    }

    public ListNode mergeRecursive(ListNode<Integer> l1, ListNode<Integer> l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        if (l1.val < l2.val) {
            l1.next = mergeRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeRecursive(l1, l2.next);
            return l2;
        }
    }

}
