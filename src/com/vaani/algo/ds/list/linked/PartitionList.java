package com.vaani.algo.ds.list.linked;

import com.vaani.algo.ds.core.ListNode;

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
	
    public static ListNode partition2(ListNode head, int x) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ListNode beforeStart = null;
        ListNode beforeEnd = null;
        ListNode afterStart = null;
        ListNode afterEnd = null;

        if (head == null) return null;
        ListNode node = head;
        while (node != null) {
            ListNode next = node.next;
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
    
    public static ListNode partition(ListNode head, int x) {
        ListNode dummyHead1 = new ListNode(-1);
        ListNode dummyHead2 = new ListNode(-1);
        ListNode dummy1 = dummyHead1;
        ListNode dummy2 = dummyHead2;

        ListNode node = head;
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
        ListNode l = new ListNode(1);
        l.next = new ListNode(4);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(2);
        l.next.next.next.next = new ListNode(5);
        l.next.next.next.next.next = new ListNode(2);
        l.display();

        ListNode n = partition(l, 3);
        n.display();
    }
}
