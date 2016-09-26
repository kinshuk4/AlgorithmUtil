package com.vaani.algo.compete.cc150.chap3;

import java.util.Stack;

/**
 * How would you design a stack which, in addition to push and pop, also has a
 * function min which returns the minimum elements? Push, pop and min should all
 * operate in O(1) time.
 */
public class Question2 {

    private Stack<Integer> stack;
    private Stack<Integer> mins;

    public Question2() {
        stack = new Stack<Integer>();
        mins = new Stack<Integer>();
    }

    public void push(int val) {
        if (val <= min()) {
            mins.push(val);
        }
        stack.push(val);
    }

    public int min() {
        if (mins.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return mins.peek();
    }

    public int pop() {
        int val = stack.pop();
        if (val == mins.peek()) {
            mins.pop();
        }
        return val;
    }
}
