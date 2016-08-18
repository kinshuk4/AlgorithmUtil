package com.vaani.algo.misc;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implement peek() and pop() from java iterator().
 * For example [1,2,3,4,5], peek() = 1, pop() = 1, peek() = 2, peek() = 2, pop() = 2
 */
public class PeekIterator implements Iterator<Integer> {
    Integer head;
    Iterator<Integer> it;

    public PeekIterator(Iterator<Integer> it) {
        this.it = it;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        PeekIterator it = new PeekIterator(list.iterator());
        System.out.println(it.peek());// 1
        System.out.println(it.next());// 1
        System.out.println(it.peek());// 2
        System.out.println(it.peek());// 2
        System.out.println(it.next());// 2
    }

    @Override
    public boolean hasNext() {
        return head != null || it.hasNext();
    }

    @Override
    public Integer next() {
        if (head == null) {
            if (it.hasNext()) {
                head = it.next();
                return head;
            }
            throw new NoSuchElementException();
        }
        int val = head;
        head = null;
        return val;
    }

    public Integer peek() {
        if (head == null) {
            if (it.hasNext()) {
                head = it.next();
                return head;
            }
            throw new NoSuchElementException();
        }
        return head;
    }

    @Override
    public void remove() {

    }
}
