package com.vaani.algo.ds.stack.impl;

public class ArrayStack implements IStack {
	private Object [ ] theArray;
	private int        topOfStack;
	private static final int DEFAULT_CAPACITY = 10;
	public ArrayStack( ) {
		theArray = new Object[ DEFAULT_CAPACITY ];
		topOfStack = -1;
	}
	public boolean isEmpty() {
		return topOfStack==-1;
	}
	public void makeEmpty() {
		topOfStack= -1;
	}
	public Object pop() {
		if(!isEmpty())
			return theArray[topOfStack--];
		else
			return null;
	}
	public void push(Object x) {

		if(!isFull())
			theArray[++topOfStack] = x;
	}
	public Object top() {
		return theArray[topOfStack];
	}
	public static void main(String[] args) {
		//Not relevant to our code
	}
	public boolean isFull()
	{
		return (topOfStack == theArray.length);

	}
}