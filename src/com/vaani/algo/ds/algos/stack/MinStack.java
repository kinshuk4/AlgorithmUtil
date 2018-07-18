package com.vaani.algo.ds.algos.stack;

import java.util.Stack;

//https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Fstack%2FMinStack.java
public class MinStack {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(Integer element)
    {
        stack.push(element);
        if (minStack.size() == 0 || element.compareTo(minStack.peek()) <= 0)
        {
            minStack.push(element);
        }
        else
            minStack.push(minStack.peek());
    }

    public Integer pop()
    {
        minStack.pop();
        return stack.pop();
    }

    public Integer min(){
        return minStack.peek();
    }
    public static void main(String[] args) {
        MinStack ms = new MinStack();
        int nums[] = new int[]{3, 4, 2, 1, 9};
        for (int i : nums) {
            ms.push(i);
        }


        System.out.println(ms.min());
        System.out.println(ms.pop());
        System.out.println(ms.min());

    }
}