package com.vaani.algo.list.linked.ds;

public class ListNode {
	public int val;
	public ListNode next;
	public ListNode(int x){
		this.val = x;
		next = null;
	}
	
	public ListNode(int x, ListNode next){
		this.val = x;
		this.next = next;
	}
}
