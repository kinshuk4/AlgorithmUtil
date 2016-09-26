package com.vaani.algo.compete.cc150.chap3;

import java.util.Stack;

/**
 * Implement a class which implements a queue using two stacks.
 */
// O(n) space, O(n) time
public class Question5 {

    private Stack<Integer> backupStack;
    private Stack<Integer> curStack;
    private boolean lastIsEnqueue;

    public Question5() {
        curStack = new Stack<Integer>();
        backupStack = new Stack<Integer>();
        lastIsEnqueue = true;
    }

    public void enqueue(int elem) {
        // write the implementation here
        if (!lastIsEnqueue) {
            swap();
        }
        lastIsEnqueue = true;
        curStack.push(elem);
    }

    public int dequeue() {
        // write the implementation here
        if (lastIsEnqueue) {
            swap();
        }
        lastIsEnqueue = false;
        return curStack.pop();
    }

    private void swap() {
        while (!curStack.isEmpty()) {
            backupStack.push(curStack.pop());
        }
        Stack<Integer> tmp = curStack;
        curStack = backupStack;
        backupStack = tmp;
    }

    public int size() {
        // write the implementation here
        return curStack.size();
    }

    public boolean empty() {
        // write the implementation here
        return curStack.size() == 0;
    }

}

