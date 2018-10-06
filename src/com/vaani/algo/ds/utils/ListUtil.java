package com.vaani.algo.ds.utils;

import com.vaani.algo.ds.core.list.ListNode;

public class ListUtil {
    public static <T> ListNode<T> insertAt(ListNode<T> head, int position, T value) {
        ListNode<T> newNode = new ListNode<>(value);
        if (head == null && position == 0) {
            head = newNode;
            return head;
        }
        if (head == null) {
            return head;
        }

        ListNode<T> curr = head;

        if (position == 0) {
            newNode.next = curr;
            head = newNode;
            return head;
        }

        int i = 0;
        while (curr != null && i < position) {
            curr = curr.next;
            i++;
        }
        if(curr == null && i!= position){
            return head;
        }

        newNode.next = curr.next;
        curr.next = newNode;

        return head;
    }

    public static <T> ListNode<T> getMidNode(ListNode<T> head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode<T> slow = head;
        ListNode<T> fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        return slow;
    }

    public static <T> ListNode<T> reverseIterative(ListNode<T> head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode<T> prev = null;
        ListNode<T> curr = head;
        while (curr != null) {
            ListNode<T> next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static ListNode reverseRecursiveOnePointer(ListNode head) {
        //  Reverse of a empty list or null list is null
        if (head == null) {
            return null;
        }

        //  Reverse of a single element list is the list with that element
        if (head.next == null) {
            return head;
        }

        //  Reverse of n element list is reverse of the second element followed by first element

        //  Get the list node pointed by second element
        ListNode secondToHead = head.next;

        //  Unlink the first element
        head.next = null;

        //  Reverse everything from the second element
        ListNode revNode = reverseRecursiveOnePointer(secondToHead);

        // Now we join both the lists
        secondToHead.next = head;

        return revNode;
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

    public static void display(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static <T> ListNode<T> arrayToList(T[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode<T> head = new ListNode<T>(arr[0]);
        ListNode<T> cur = head;
        for (int i = 1; i < arr.length; ++i) {
            cur.next = new ListNode<T>(arr[i]);
            cur = cur.next;
        }
        return head;
    }
}
