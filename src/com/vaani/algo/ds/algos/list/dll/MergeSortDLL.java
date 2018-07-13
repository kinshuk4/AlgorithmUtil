package com.vaani.algo.ds.algos.list.dll;

import com.vaani.algo.ds.core.list.dll.DoubleListNode;

/**
 * Created by kchandra on 21/08/16.
 * http://www.geeksforgeeks.org/merge-sort-for-doubly-linked-list/
 */
public class MergeSortDLL {

    // Split a doubly linked list (DLL) into 2 DLLs of
    // half sizes
    DoubleListNode split(DoubleListNode head) {
        DoubleListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        DoubleListNode temp = slow.next;
        slow.next = null;
        return temp;
    }

    DoubleListNode mergeSort(DoubleListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        DoubleListNode second = split(node);

        // Recur for left and right halves
        node = mergeSort(node);
        second = mergeSort(second);

        // Merge the two sorted halves
        return merge(node, second);
    }

    // Function to merge two linked lists
    DoubleListNode merge(DoubleListNode<Integer> first, DoubleListNode<Integer> second) {
        // If first linked list is empty
        if (first == null) {
            return second;
        }

        // If second linked list is empty
        if (second == null) {
            return first;
        }

        // Pick the smaller value
        if (first.value < second.value) {
            first.next = merge(first.next, second);
            first.next.prev = first;
            first.prev = null;
            return first;
        } else {
            second.next = merge(first, second.next);
            second.next.prev = second;
            second.prev = null;
            return second;
        }
    }
    static DoubleListNode head;
    // Driver program to test above functions
    public static void main(String[] args) {

        MergeSortDLL list = new MergeSortDLL();
        list.head = new DoubleListNode(10);
        list.head.next = new DoubleListNode(30);
        list.head.next.next = new DoubleListNode(3);
        list.head.next.next.next = new DoubleListNode(4);
        list.head.next.next.next.next = new DoubleListNode(20);
        list.head.next.next.next.next.next = new DoubleListNode(5);


        DoubleListNode node = null;
        node = list.mergeSort(head);
        System.out.println("Linked list after sorting :");
//        list.print(node);

    }
}
