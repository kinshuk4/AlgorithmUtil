package com.vaani.algo.ds.stack;
import java.util.Stack;
//https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Fstack%2FMinStack.java
public class MinStack {
    public static void main(String[] args) {
        Stack<Integer> numberStack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();
        int nums[] = new int[]{3, 4, 2, 1, 9};
        for (int i : nums) {
            numberStack.push(i);
            if (!minStack.isEmpty() && i > minStack.peek()) {
                minStack.push(minStack.peek());
            } else {
                minStack.push(i);
            }
        }
        System.out.println(numberStack);
        System.out.println(minStack);
    }
}