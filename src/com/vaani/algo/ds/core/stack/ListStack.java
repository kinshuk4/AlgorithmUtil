package com.vaani.algo.ds.core.stack;

import com.vaani.algo.ds.core.list.ListNode;

/**
 * Created by kchandra on 18/08/16.
 */

public class ListStack<T> {
    private ListNode<T> mHead;

    public ListStack() {
        mHead = null;
    }

    public void push(int n) {
        ListNode tmp = getNewNode(n);
        tmp.val = n;

        ListNode oHead = mHead;
        mHead = tmp;
        mHead.next = oHead;
    }

    public T pop() {
        if (isEmpty())
            return null;
        T retVal = mHead.val;
        mHead = mHead.next;
        return retVal;
    }

    public T top() {
        return mHead.val;
    }

    public boolean isEmpty() {
        return mHead == null;
    }

    private ListNode getNewNode(int n) {
        return new ListNode(n);
    }
}
