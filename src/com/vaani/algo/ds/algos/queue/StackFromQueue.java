package com.vaani.algo.ds.algos.queue;

import com.vaani.algo.ds.core.stack.IStack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by kchandra on 19/08/16.
 */
public class StackFromQueue implements IStack{
    Queue<Object> queue = new LinkedList<>();


    @Override
    public void push(Object x) {
        int oldSize = queue.size();
        queue.add(x);
        for (int i=0; i<oldSize; i++)
        {
            queue.add(queue.remove());
        }
    }

    @Override
    public Object pop() {
        return queue.remove();
    }

    @Override
    public Object top() {
        return queue.peek();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public void makeEmpty() {

    }

    public static void main(String[] args) {
        StackFromQueue stk = new StackFromQueue();
        stk.push(1);
        stk.push(2);
        stk.push(3);
        stk.push(4);

        System.out.println(stk.pop());
    }
}
