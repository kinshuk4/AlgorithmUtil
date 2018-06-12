package com.vaani.algo.ds.core.list;

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

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
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
