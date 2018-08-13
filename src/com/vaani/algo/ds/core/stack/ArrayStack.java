package com.vaani.algo.ds.core.stack;

import java.lang.reflect.Array;

public class ArrayStack<T> implements IStack<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] theArray;
    private int topOfStack;

    public ArrayStack() {
        theArray = (T[]) new Object[DEFAULT_CAPACITY];
        topOfStack = -1;
    }

//    private void initArray(Class<T> clazz, ) {
//        theArray = (T[]) Array.newInstance(clazz, capacity);
//    }

    public static void main(String[] args) {
        //Not relevant to our code
    }

    public boolean isEmpty() {
        return topOfStack == -1;
    }

    public void makeEmpty() {
        topOfStack = -1;
    }


    public T pop() {
        if (!isEmpty())
            return theArray[topOfStack--];
        else
            return null;
    }

    public void push(T x) {
        if (!isFull())
            theArray[++topOfStack] = x;
    }

    public T top() {
        return theArray[topOfStack];
    }

    public boolean isFull() {
        return (topOfStack == theArray.length);

    }
}