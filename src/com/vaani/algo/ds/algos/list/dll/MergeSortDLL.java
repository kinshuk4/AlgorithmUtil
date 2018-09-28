package com.vaani.algo.ds.algos.list.dll;

import com.vaani.algo.ds.core.list.dll.DLLNode;

/**
 * Created by kchandra on 21/08/16.
 * http://www.geeksforgeeks.org/merge-sort-for-doubly-linked-list/
 */
public class MergeSortDLL {

    // Split a doubly linked list (DLL) into 2 DLLs of
    // half sizes
    DLLNode split(DLLNode head) {
        DLLNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        DLLNode temp = slow.next;
        slow.next = null;
        return temp;
    }

    DLLNode mergeSort(DLLNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        DLLNode second = split(node);

        // Recur for left and right halves
        node = mergeSort(node);
        second = mergeSort(second);

        // Merge the two sorted halves
        return merge(node, second);
    }

    // Function to merge two linked lists
    DLLNode merge(DLLNode<Integer> first, DLLNode<Integer> second) {
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
    static DLLNode head;
    // Driver program to test above functions
    public static void main(String[] args) {

        MergeSortDLL list = new MergeSortDLL();
        list.head = new DLLNode(10);
        list.head.next = new DLLNode(30);
        list.head.next.next = new DLLNode(3);
        list.head.next.next.next = new DLLNode(4);
        list.head.next.next.next.next = new DLLNode(20);
        list.head.next.next.next.next.next = new DLLNode(5);


        DLLNode node = null;
        node = list.mergeSort(head);
        System.out.println("Linked list after sorting :");
//        list.print(node);

    }
}
