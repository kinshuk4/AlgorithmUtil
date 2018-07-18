package com.vaani.algo.ds.algos.stack;

import java.util.Stack;

/**
 * Created by kchandra on 18/08/16.
 */
public class QueueFromOneStack {
    Stack<String> stack = new Stack<>();
    public void enqueue(String elem) {
        if (!stack.empty()) {
            String topElem = stack.pop();
            enqueue(elem);
            stack.push(topElem);
        }
        else
            stack.push(elem);
    }

    public String dequeue() {
        return stack.pop();
    }

    public static void main(String[] args) {
        QueueFromOneStack queue = new QueueFromOneStack();
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
}
