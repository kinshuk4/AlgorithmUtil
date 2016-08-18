package com.vaani.algo.ds.list.linked;

import com.vaani.algo.ds.core.ListNode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * <p>
 * Created by Xiaomeng on 8/4/2014.
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
        ListNode partition = getMidNode(head);
        ListNode mid = partition.next;
        partition.next = null;

        ListNode first = sortList(head);
        ListNode second = sortList(mid);
        return merge(first, second);
    }

    public ListNode merge(ListNode first, ListNode second) {
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

    public ListNode getMidNode(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
