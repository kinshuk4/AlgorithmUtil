package com.vaani.algo.ds.list.linked;

import com.leetcode.core.ListNode;

/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 *
 * Created by Xiaomeng on 7/9/2014.
 */
public class MergeTwoSortedLists {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode node = dummyHead;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                node.next = l1;
                l1 = l1.next;
            }else{
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        node.next = l1 == null ? l2 : l1;
        return dummyHead.next;
    }

    public static void main(String[] args){
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);
        l1.display();

        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(6);
        l2.next.next.next = new ListNode(7);
        l2.display();

        ListNode n = mergeTwoLists(l1, l2);
        n.display();
    }
}
