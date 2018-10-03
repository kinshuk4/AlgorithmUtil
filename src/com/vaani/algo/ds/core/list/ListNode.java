package com.vaani.algo.ds.core.list;

public class ListNode<T> {
    public T val;
    public ListNode<T> next;


    public ListNode(T x) {
        this.val = x;
        next = null;
    }

    public ListNode(T x, ListNode next) {
        this.val = x;
        this.next = next;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }


    public void display() {
        ListNode head = this;
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public String detailedToString(){
        StringBuilder sb = new StringBuilder();
        ListNode curr = this;
        while (curr != null) {
            sb = sb.append(curr.val);
            curr = curr.next;
        }

        return sb.toString();
    }
}
