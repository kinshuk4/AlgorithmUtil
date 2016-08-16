package com.vaani.algo.ds.stack;

import java.util.Stack;

/**
 * Sorts a stack using operations push,pop,peek and isEmpty
 *
 */
public class StackSort {

	public void sort(Stack<Integer> s){

		int x=0;
		if (!s.isEmpty()){
			x=s.pop();
			sort(s);
			insert(s,x);
		}

	}

	//At each step check if stack.peek < x, and insert below top recursively
	public void insert(Stack<Integer> s,int x){

		if (!s.isEmpty() && s.peek()>= x){

			int y=s.pop();
			insert(s, x);
			s.push(y);

		}else {
			s.push(x);
		}

	}

	public static void main(String[] a){

		StackSort ss=new StackSort();

		//initiate the stack of integers
		Stack<Integer> stack=new Stack<Integer>();
		//push the elements
		stack.push(3);stack.push(4);stack.push(1);stack.push(2);

		//sort the stack
		ss.sort(stack);
		for(int val: stack){
			System.out.print(val+" ");
		}

	}

}