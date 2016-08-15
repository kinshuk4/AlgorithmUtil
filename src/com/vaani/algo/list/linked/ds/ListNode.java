package com.vaani.algo.list.linked.ds;

public class ListNode {
	public int value;
	public ListNode next;
	public ListNode(int x){
		this.value = x;
		next = null;
	}
	
	public ListNode(int x, ListNode next){
		this.value = x;
		this.next = next;
	}
}
