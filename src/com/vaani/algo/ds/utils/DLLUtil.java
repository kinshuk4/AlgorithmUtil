package com.vaani.algo.ds.utils;

import com.vaani.algo.ds.core.list.dll.DLLNode;
import com.vaani.algo.ds.core.list.dll.DoubleLinkedList;

public class DLLUtil {

    public static <T> void display(DLLNode<T> head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

    /**
     * Reverses the doubly linked list.
     *
     * @param list
     */
    public static <E> void reverseList(DoubleLinkedList<E> list) {

        DLLNode<E> curr = list.get(0);
        DLLNode<E> temp = curr;

        while (curr != null) {
            temp = curr.prev;
            curr.prev = curr.next;
            curr.next = temp;
            curr = curr.prev;
        }

        // temp will be null if linked list has only one node
        if (temp != null) {
            list.head = temp.prev;
        }
    }
}
