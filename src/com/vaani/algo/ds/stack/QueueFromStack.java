package com.vaani.algo.ds.stack;

import java.util.Stack;


public class QueueFromStack {
    Stack<String> inStack = new Stack<>();
    Stack<String> outStack = new Stack<>();

    public static void main(String[] args) {
        QueueFromStack queue = new QueueFromStack();
        queue.enqueue(new String("first"));
        queue.enqueue(new String("second"));
        queue.enqueue(new String("third"));
        queue.enqueue(new String("fourth"));
        queue.enqueue(new String("fifth"));
        System.out.println("1. " + queue.dequeue());
        System.out.println("2. " + queue.dequeue());
        System.out.println("3. " + queue.dequeue());
        System.out.println("4. " + queue.dequeue());
        System.out.println("5. " + queue.dequeue());
    }

    public void enqueue(String value) {
        inStack.push(value);
    }

    public Object dequeue() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }
}
