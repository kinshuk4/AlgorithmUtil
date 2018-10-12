package com.vaani.algo.misc;

import com.vaani.algo.ds.core.list.dll.DLLNode;

import static com.vaani.algo.ds.utils.DLLUtil.display;


/**
 * Reverse a given Doubly Linked List
 */
public class ReverseDoublyLinkedList {
    public static <T> DLLNode reverse(DLLNode<T> head) {
        if (head == null) return null;

        DLLNode tmp = null;
        DLLNode curr = head;
        while (curr != null) {
            tmp = curr.prev;
            curr.prev = curr.next;
            curr.next = tmp;
            curr = curr.prev;
        }
        return tmp.prev;
    }



    public static void main(String[] args) {
        DLLNode<Integer> node1 = new DLLNode<>(1);
        DLLNode<Integer> node2 = new DLLNode<>(2);
        DLLNode<Integer> node3 = new DLLNode<>(3);
        node1.prev = null;
        node1.next = node2;
        node2.prev = node1;
        node2.next = node3;
        node3.prev = node2;
        node3.next = null;

        display(node1);
        System.out.println();
        display(reverse(node1));
    }

}
