package com.vaani.algo.compete.cc150.chap3;

import java.util.Stack;

/**
 * Write a program to sort a stack in ascending order (with biggest items on
 * top). You may use additional stacks to hold items, but you may not copy the
 * elements into any other data structure (such as an array). The stack supports
 * the following operations: push, pop, peek, and isEmpty.
 */
// O(n) space, O(n^2) time
public class Question6 {

    private Stack<Integer> stack = new Stack<Integer>();

    public void push(int elem) {
        stack.push(elem);
    }

    public int pop() {
        return stack.pop();
    }

    public int peek() {
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void sort() {
        // write implementation here
        Stack<Integer> sortedStack = new Stack<Integer>();
        while (!stack.isEmpty()) {
            // put 1 element from stack to sortedStack each time
            int len = 0;
            int top = stack.pop();
            //  pop all elem larger than top
            while (!sortedStack.isEmpty() && sortedStack.peek() > top) {
                stack.push(sortedStack.pop());
                ++len;
            }
            sortedStack.push(top);
            for (int i = 0; i < len; ++i) {
                sortedStack.push(stack.pop());
            }
        }
        stack = sortedStack;
    }

}

