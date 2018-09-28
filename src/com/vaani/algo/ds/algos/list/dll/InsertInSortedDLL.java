package com.vaani.algo.ds.algos.list.dll;

import com.vaani.algo.ds.core.list.dll.DLLNode;
import com.vaani.algo.misc.ReverseDoublyLinkedList;

public class InsertInSortedDLL {
    public static <T extends Comparable<T>> DLLNode<T> insertInSortedDLL(DLLNode<T> head, T data) {
        DLLNode<T> newNode = new DLLNode<>(data);
        if (newNode.value.compareTo(head.value) < 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return head;
        }


        DLLNode<T> curr = head;
        DLLNode<T> currPrev = head;
        while (curr != null && curr.value.compareTo(newNode.value) < 0) {
            currPrev = curr;
            curr = curr.next;
        }


        newNode.next = currPrev.next;
        newNode.prev = currPrev;
        if (currPrev.next != null) {
            currPrev.next.prev = newNode;
        }
        currPrev.next = newNode;
        return head;
    }
}
