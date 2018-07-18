package com.vaani.algo.ds.algos.stack;

import java.util.Stack;


public class QueueFromStack {
    Stack<String> inStack = new Stack<>();
    Stack<String> outStack = new Stack<>();

    public static void main(String[] args) {
        QueueFromStack queue = new QueueFromStack();
        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");
        queue.enqueue("fourth");
        queue.enqueue("fifth");
        System.out.println("1. " + queue.dequeue());
        System.out.println("2. " + queue.dequeue());
        System.out.println("3. " + queue.dequeue());
        System.out.println("4. " + queue.dequeue());
        System.out.println("5. " + queue.dequeue());
    }

    public void enqueue(String value) {
        inStack.push(value);
    }

    public String dequeue() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }
}
