package com.vaani.algo.ds.algos.stack;
import com.vaani.algo.ds.core.queue.IQueue;

import java.util.Stack;

/**
 * Created by kchandra on 19/08/16.
 */
public class QueueFrom2Stacks<T> implements IQueue<T> {
    Stack<T> inStack = new Stack<>();   // place where enqueued
    Stack<T> outStack = new Stack<>(); // place where dequeued


    @Override
    public void enqueue(T obj) {
        inStack.push(obj);
    }

    /**
     * Remove an element from the front of the queue.
     * @return the element removed from the front of the queue
     *   or null if the queue is empty.
     */
    @Override
    public T dequeue() {

        // if the outStack is empty and the inStack is NOT:
        if (outStack.isEmpty() && !inStack.isEmpty()) {
            // while the inStack is not empty, pop elements from inStack and push them onto outStack
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        // now pop elements from the outStack
        // if the queue is empty this will return null
        return outStack.pop();
    }





    /**
     * Return the element at the front of queue, but do not remove it
     * @return the element at the front of the queue,
     *   or null if the queue is empty.
     */
    public T front() {
        // if the q is empty, return null
        if (this.isEmpty())
            return null;

        // if outStack is empty, push all the inStack items onto the outStack (but don't remove them!)
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        // otherwise, just return the item at the top of the outStack!
        return outStack.peek();
    }

    public T peek(){
        return front();
    }


    /**
     * Is the queue empty?
     * @return true if queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    /**
     * A testing program
     */
    public static void main (String [] args)  {
        QueueFrom2Stacks<String> q = new QueueFrom2Stacks<String>();
        q.enqueue("cat");
        q.enqueue("dog");
        q.enqueue("bee");
        System.out.println("First thing dequeued is: " + q.dequeue());
        System.out.println("Next thing dequeued is: " + q.dequeue());
        System.out.println("Is it empty? : " + q.isEmpty());
        q.enqueue("eagle");
        System.out.println("Next thing dequeued is: " + q.dequeue());
        System.out.println("Next thing dequeued is: " + q.dequeue());
        System.out.println("Is it empty? : " + q.isEmpty());
        System.out.println("dequeue of empty stack: " + q.dequeue());
        q.enqueue("bear");
        System.out.println("front is: " + q.front());
        System.out.println("Next thing dequeued is: " + q.dequeue());
        q.enqueue("cat");
        q.enqueue("dog");
        q.enqueue("sheep");
        q.enqueue("cow");
        q.enqueue("eagle");
        q.enqueue("bee");
        q.enqueue("lion");
        q.enqueue("tiger");
        q.enqueue("zebra");
        q.enqueue("ant");
        System.out.println("Bigger example:");
        System.out.println("front is: " + q.front());
        System.out.println("Next thing dequeued is: " + q.dequeue());
        System.out.println("front is: " + q.front());
        System.out.println("Next thing dequeued is: " + q.dequeue());
        System.out.println("Next thing dequeued is: " + q.dequeue());
        System.out.println("Next thing dequeued is: " + q.dequeue());
        System.out.println("Next thing dequeued is: " + q.dequeue());
        System.out.println("Next thing dequeued is: " + q.dequeue());
        System.out.println("Next thing dequeued is: " + q.dequeue());
        System.out.println("Next thing dequeued is: " + q.dequeue());
        System.out.println("Next thing dequeued is: " + q.dequeue());
        System.out.println("Next thing dequeued is: " + q.dequeue());
        System.out.println("Next thing dequeued is: " + q.dequeue());
    }
}
