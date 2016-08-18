package com.vaani.algo.ds.stack;

import java.util.Stack;

public class SortAStack {
	public static Stack<Integer> sort(Stack<Integer> stack) {
		Stack<Integer> auxiliary = new Stack<Integer>();
		while (!stack.isEmpty()) {
			Integer value = stack.pop();
			while (!auxiliary.isEmpty() && value < auxiliary.peek())
				stack.push(auxiliary.pop());
			auxiliary.push(value);
		}
		return auxiliary;
	}

//	public static IStack<Integer> sort2(IStack<Integer> s1) {
//		IStack<Integer> s2 = new IStack<Integer>();// s2:auxiliary stack
//		int a = s1.pop();
//		int b = s2.pop();
//		while (true)
//
//		{
//
//			while (a < b && !empty(s1))
//
//			{
//
//				push(a, s2);
//
//				push(b, s2);
//
//				a = pop(s1);
//
//				b = pop(s1);
//
//			}
//
//			if (empty(s1))
//
//				break;
//
//			temp = b;
//
//			push(a, s2);
//
//			while (!empty(s2))
//
//				push(pop(s2), s1);
//
//			push(temp, s1);
//
//			a = pop(s1);
//
//			b = pop(s1);
//
//		}
//
//		// after break
//
//		push(b, s1);
//
//		push(a, s1);
//
//		while (!empty(s2))
//
//			push(pop(s2), s1);
//	}
}
