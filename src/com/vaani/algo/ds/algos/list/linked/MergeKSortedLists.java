package com.vaani.algo.ds.algos.list.linked;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.vaani.algo.ds.core.list.ListNode;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 */
public class MergeKSortedLists {
    public static ListNode mergeKLists(List<ListNode<Integer>> lists) {
        if (lists.size() == 0) return null;

        Comparator<ListNode<Integer>> comparator = new Comparator<ListNode<Integer>>() {
            @Override
            public int compare(ListNode<Integer> o1, ListNode<Integer> o2) {
                return o1.val - o2.val;
            }
        };

        PriorityQueue<ListNode<Integer>> queue = new PriorityQueue<ListNode<Integer>>(lists.size(), comparator);
        for (ListNode node : lists) {
            if (node != null) queue.offer(node);
        }

        ListNode dummy = new ListNode(-1);
        ListNode dummyHead = dummy;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            dummy.next = node;
            if (node.next != null) queue.offer(node.next);
            dummy = dummy.next;
        }
        return dummyHead.next;
    }

    public static ListNode mergeKLists2(ArrayList<ListNode> lists) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (lists == null || lists.size() == 0) {
            return null;
        }
        ListNode l = lists.get(0);
        for (int i = 1; i < lists.size(); i++) {
            l = MergeTwoSortedLists.mergeTwoLists(l, lists.get(i));
        }
        return l;
    }

    //Solution 2 - //heap sort
    public static ListNode mergeKLists3(ArrayList<ListNode> lists) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (lists == null || lists.size() == 0) {
            return null;
        }

        PriorityQueue<ListNode<Integer>> heap = new PriorityQueue(lists.size(), new Comparator<ListNode<Integer>>() {
            public int compare(ListNode<Integer> n1, ListNode<Integer> n2) {
                if (n1.val < n2.val) return -1;
                if (n1.val > n2.val) return 1;
                return 0;
            }
        });

        for (ListNode n : lists) {
            if (n != null) {
                heap.add(n);
            }
        }

        ListNode head = heap.poll();
        ListNode cur = head;
        while (!heap.isEmpty()) {
            if (cur.next != null) {
                heap.add(cur.next);
            }

            cur.next = heap.poll();
            cur = cur.next;
        }
        return head;
    }
    public static void main(String[] args) {
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

        List<ListNode<Integer>> list = new ArrayList<ListNode<Integer>>();
        list.add(l1);
        list.add(l2);
        list.add(l3);

        mergeKLists(list).display();
    }
}
