package com.vaani.algo.ds.list.linked;

import com.vaani.algo.ds.core.list.ListNode;

/**
 * Created by Xiaomeng on 7/14/2014.
 */
public class ReverseLinkedList {
    public static ListNode reverseIterative(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static ListNode reverseRecursiveOnePointer(ListNode head)
    {
        //  Reverse of a empty list or null list is null
        if (head == null)
        {
            return null;
        }

        //  Reverse of a single element list is the list with that element
        if (head.next == null)
        {
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



    public static void main(String[] args) {
        ListNode l = new ListNode(1);
        l.next = new ListNode(4);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(2);
        l.next.next.next.next = new ListNode(5);
        l.next.next.next.next.next = new ListNode(2);
        l.display();

        ListNode n = reverseIterative(l);
        n.display();
    }
}
