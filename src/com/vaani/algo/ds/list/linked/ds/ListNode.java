package com.vaani.algo.ds.list.linked.ds;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(int x) {
		this.val = x;
		next = null;
	}

	public ListNode(int x, ListNode next) {
		this.val = x;
		this.next = next;
	}

	public static boolean listEqual(ListNode head1, ListNode head2) {
		if (head1 == null && head2 == null) {
			return true;
		} else if (head1 != null && head2 != null) {
			return (head1.val == head2.val) && listEqual(head1.next, head2.next);
		} else {
			return false;
		}
	}

	public static ListNode arrayToList(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		ListNode head = new ListNode(arr[0]);
		ListNode cur = head;
		for (int i = 1; i < arr.length; ++i) {
			cur.next = new ListNode(arr[i]);
			cur = cur.next;
		}
		return head;
	}
}
