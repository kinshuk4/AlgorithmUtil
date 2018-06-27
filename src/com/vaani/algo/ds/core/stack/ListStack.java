package com.vaani.algo.ds.core.stack;

import com.vaani.algo.ds.core.list.ListNode;

/**
 * Created by kchandra on 18/08/16.
 */

public class ListStack {
    private ListNode m_head;

    public ListStack() {
        m_head = null;
    }

    public void push(int n) {
        ListNode tmp = getNewNode(n);
        tmp.val = n;

        ListNode oHead = m_head;
        m_head = tmp;
        m_head.next = oHead;
    }

    public int pop() {
        if (isEmpty())
            return -1;
        int retVal = m_head.val;
        m_head = m_head.next;
        return retVal;
    }

    public int top() {
        return m_head.val;
    }

    public boolean isEmpty() {
        return m_head == null;
    }

    private ListNode getNewNode(int n) {
        return new ListNode(n);
    }
}
