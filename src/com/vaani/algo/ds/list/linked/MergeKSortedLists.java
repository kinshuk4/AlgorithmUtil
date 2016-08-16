package com.vaani.algo.ds.list.linked;

import com.leetcode.core.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Created by Xiaomeng on 7/24/2014.
 */
public class MergeKSortedLists {
    public static ListNode mergeKLists(List<ListNode> lists) {
        if(lists.size() == 0) return null;

        Comparator<ListNode> comparator = new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        };

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(), comparator);
        for(ListNode node : lists){
            if(node != null) queue.offer(node);
        }

        ListNode dummy = new ListNode(-1);
        ListNode dummyHead = dummy;
        while(!queue.isEmpty()){
            ListNode node = queue.poll();
            dummy.next = node;
            if(node.next != null) queue.offer(node.next);
            dummy = dummy.next;
        }
        return dummyHead.next;
    }

    public static void main(String[] args){
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(5);
        l1.next.next = new ListNode(8);
        l1.next.next.next = new ListNode(9);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(11);
        l2.next.next.next = new ListNode(20);

        ListNode l3 = new ListNode(3);
        l3.next = new ListNode(7);
        l3.next.next = new ListNode(10);
        l3.next.next.next = new ListNode(12);

        List<ListNode> list = new ArrayList<ListNode>();
        list.add(l1);
        list.add(l2);
        list.add(l3);

        mergeKLists(list).display();
    }
}
