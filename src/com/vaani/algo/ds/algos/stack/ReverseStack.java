package com.vaani.algo.ds.algos.stack;

/**
 * Created by kchandra on 18/08/16.
 */
import java.util.*;

class ReverseStack {

    Stack<Integer> stack = new Stack<Integer>();

    void reverse() {
        if (!stack.isEmpty()) {
            int top = stack.pop();
            reverse();
            putInBottom(top);
        }
    }

    private void putInBottom(int popped) {

        if (stack.size() == 0) {
            stack.push(popped);
            return;
        }
        int element = stack.pop();
        putInBottom(popped);
        stack.push(element);
    }

    public void reverseStackUsingAuxStack() {
        int count = stack.size();
        Stack<Integer> tempStack = new Stack<>();
        for (int i = 0; i < count - 1; i++) {
            int temp = stack.pop();
            for (int j = 0; j < count - (i + 1); j++) {
                if (!stack.isEmpty()) {
                    tempStack.push(stack.pop());
                }
            }
            stack.push(temp);
            for (int k = 0; k < count - (i + 1); k++) {
                if (!tempStack.isEmpty()) {
                    stack.push(tempStack.pop());
                }
            }
        }

    }


    int pop() {
        return stack.pop();
    }

    Boolean isEmpty() {
        return stack.isEmpty();
    }

    void push(int elem) {
        stack.push(elem);
    }

    public void display(){
        for(int i: stack){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        ReverseStack stk = new ReverseStack();
        stk.push(1);
        stk.push(2);
        stk.push(3);
        stk.push(4);

        stk.reverse();

        stk.display();
    }

}