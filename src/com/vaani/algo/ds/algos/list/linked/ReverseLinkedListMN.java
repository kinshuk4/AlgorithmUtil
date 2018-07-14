package com.vaani.algo.ds.algos.list.linked;


import com.vaani.algo.ds.core.list.ListNode;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * <p>
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * <p>
 * return 1->4->3->2->5->NULL.
 * <p>
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
 * <p>
 */
public class ReverseLinkedListMN {
    public static ListNode reverseBetweenMN(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n){
        	return head;
        }

        ListNode left = new ListNode(-1);
        left.next = head;
        for (int i = 1; i < m; i++){
        	left = left.next;
        }

        ListNode rHead = left.next;
        ListNode prev = left.next;
        ListNode curr = prev.next;

        int step = n - m;
        while (curr != null && step != 0) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            step--;
        }

        left.next = prev;
        rHead.next = curr;
        return m == 1 ? prev : head;
    }

    public static void main(String[] args) {
        ListNode l = new ListNode(1);
        l.next = new ListNode(2);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(4);
        l.next.next.next.next = new ListNode(5);
        l.display();

        ListNode n = reverseBetweenMN(l, 1, 4);
        n.display();
    }
}
