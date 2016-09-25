package com.vaani.algo.ds.list.dll;

import com.vaani.algo.ds.list.dll.impl.DoubleLinkedList;
import com.vaani.algo.ds.list.dll.impl.DoubleListNode;

// http://www.geeksforgeeks.org/reverse-a-doubly-linked-list/
public class ReverseDLL {

    /**
     * Reverses the doubly linked list.
     *
     * @param list
     */
    public static <E extends Comparable<E>> void reverseList(DoubleLinkedList<E> list) {

        DoubleListNode<E> curr = list.get(0);
        DoubleListNode<E> temp = curr;

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

    public static void main(String a[]) {
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();
        linkedList.add(11);
        linkedList.add(22);
        linkedList.add(33);
        linkedList.add(44);
        linkedList.add(55);
        linkedList.add(66);
//        linkedList.printList();
        reverseList(linkedList);
//        linkedList.printList();
    }
}