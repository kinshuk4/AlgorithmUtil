package com.vaani.algo.ds.algos.list.linked;

import com.vaani.algo.ds.core.list.ListNode;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * <p>
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p>
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 * <p>
 * 
 */
public class PartitionList {
	
    public static ListNode partition2(ListNode<Integer> head, int x) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ListNode<Integer> beforeStart = null;
        ListNode<Integer> beforeEnd = null;
        ListNode<Integer> afterStart = null;
        ListNode<Integer> afterEnd = null;

        if (head == null) return null;
        ListNode<Integer> node = head;
        while (node != null) {
            ListNode<Integer> next = node.next;
            node.next = null;
            if (node.val < x) {
                if (beforeStart == null) {
                    beforeStart = node;
                    beforeEnd = node;
                } else {
                    beforeEnd.next = node;
                    beforeEnd = node;
                }
            } else {
                if (afterStart == null) {
                    afterStart = node;
                    afterEnd = node;
                } else {
                    afterEnd.next = node;
                    afterEnd = node;
                }
            }
            node = next;
        }
        if (beforeEnd == null) {
            return afterStart;
        } else {
            beforeEnd.next = afterStart;
            return beforeStart;
        }

    }
    
    public static ListNode partition(ListNode<Integer> head, int x) {
        ListNode<Integer> dummyHead1 = new ListNode(-1);
        ListNode<Integer> dummyHead2 = new ListNode(-1);
        ListNode<Integer> dummy1 = dummyHead1;
        ListNode<Integer> dummy2 = dummyHead2;

        ListNode<Integer> node = head;
        while (node != null) {
            if (node.val < x) {
                dummy1.next = node;
                dummy1 = dummy1.next;
            } else {
                dummy2.next = node;
                dummy2 = dummy2.next;
            }
            node = node.next;
        }
        dummy2.next = null;
        dummy1.next = dummyHead2.next;
        return dummyHead1.next;
    }

    public static void main(String[] args) {
        ListNode l = new ListNode<Integer>(1);
        l.next = new ListNode<Integer>(4);
        l.next.next = new ListNode<Integer>(3);
        l.next.next.next = new ListNode<Integer>(2);
        l.next.next.next.next = new ListNode<Integer>(5);
        l.next.next.next.next.next = new ListNode<Integer>(2);
        l.display();

        ListNode n = partition(l, 3);
        n.display();
    }
}
