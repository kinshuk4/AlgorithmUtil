package com.vaani.algo.ds.core.list.dll;

import com.vaani.algo.compete.hackerrank.randomchallenges.ConvertTreeToDoubleLinkedList;

public final class DLLNode<T>{
    public T value;
    public DLLNode<T> prev;
    public DLLNode<T> next;

    public DLLNode(T obj) {
        this(null, obj, null);
    }

    public DLLNode(DLLNode<T> previous, T value, DLLNode<T> next) {
        this.prev = previous;
        this.value = value;
        this.next = next;
    }

    // A utility function to find last node of linked list
    public DLLNode<T> getLastNode(DLLNode<T> node) {
        while (node.next != null)
            node = node.next;
        return node;
    }

    public void linkNext(DLLNode<T> n) {
        next = n;
        n.prev = this;
    }

    /**
     * Getters and Setters
     */
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public DLLNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DLLNode<T> prev) {
        this.prev = prev;
    }

    public DLLNode<T> getNext() {
        return next;
    }

    public void setNext(DLLNode<T> next) {
        this.next = next;
    }
}