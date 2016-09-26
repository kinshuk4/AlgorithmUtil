package com.vaani.algo.compete.cc150.chap3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Imagine a (literal) stack of plates. If the stack gets too high, it might
 * topple. Therefore, in real life, we would likely start a new stack when the
 * previous stack exceeds some threshold. Implement a data structure SetOfStacks
 * that mimics this. SetOfStacks should be composed of several stacks and should
 * create a new stack once the previous one exceeds capacity. SetOfStacks.push()
 * and SetOfStacks.pop() should behave identically to a single stack (that is,
 * pop() should return the same values as it would if there were just a single
 * stack).
 * <p>
 * FOLLOW UP Implement a function popAt(int index) which performs a pop
 * operation on a specific sub-stack.
 */
// space: push O(1), pop O(1)
// time: push O(1), pop O(n)
public class Question3 {

    private int capacity = 3;
    private List<List<String>> stackList;

    public Question3(int capacity) {
        this.capacity = capacity;
        stackList = new ArrayList<List<String>>();
    }

    public void push(String elem) {
        if (stackList.size() == 0) {
            stackList.add(new ArrayList<String>());
        }
        List<String> curStack = stackList.get(stackList.size() - 1);
        if (curStack.size() == capacity) {
            stackList.add(new Stack<String>());
            curStack = stackList.get(stackList.size() - 1);
        }
        curStack.add(elem);
    }

    public String pop() {
        String ret = null;
        if (stackList.size() != 0) {
            List<String> curStack = stackList.get(stackList.size() - 1);
            ret = curStack.remove(curStack.size() - 1);
            if (curStack.size() == 0) {
                stackList.remove(stackList.size() - 1);
            }
        }
        return ret;
    }

    public String popAt(int index) {
        List<String> curStack = stackList.get(index);
        String val = curStack.remove(curStack.size() - 1);
        for (int i = index + 1; i < stackList.size(); ++i) {
            curStack = stackList.get(i - 1);
            List<String> nextStack = stackList.get(i);
            while (curStack.size() < capacity && nextStack.size() > 0) {
                curStack.add(nextStack.get(0));
                nextStack.remove(0);
            }
        }
        if (stackList.get(stackList.size() - 1).size() == 0) {
            stackList.remove(stackList.size() - 1);
        }
        return val;
    }
}



