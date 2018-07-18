package com.vaani.algo.ds.algos.stack;

import java.util.Stack;

/**
 * Calculate the expression including the +,-,*,/,(,)
 */
public class ExpressionCalculate {

    public static void main(String[] args) {
        String str = "9*((3-1)*3 + 5)";
        ExpressionCalculate cal = new ExpressionCalculate();
        System.out.println(cal.calculate(str));
    }

    /**
     * Use two stack, the first one translate the in-order expression to post-order expression.
     * The second one calculate the result based on the post-order expression.
     *
     * @param expression
     * @return
     */
    public double calculate(String expression) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < expression.length(); ++i) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                sb.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                char cur;
                do {
                    cur = stack.pop();
                    if (cur != '(') {
                        sb.append(cur);
                    }
                } while (cur != '(');
            } else if (ch == '+' || ch == '-') {
                // pop until the top has no priority
                while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
                    sb.append(stack.pop());
                }
                stack.push(ch);
            } else if (ch == '*' || ch == '/') {
                stack.push(ch);
            }
        }
        // pop all
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());
        String postOrder = sb.toString();

        Stack<Double> resStack = new Stack<Double>();
        for (int i = 0; i < postOrder.length(); ++i) {
            char ch = postOrder.charAt(i);
            if (Character.isDigit(ch)) {
                resStack.push((double) (ch - '0'));
            } else {
                double first = resStack.pop();
                double second = resStack.pop();
                if (ch == '+') {
                    resStack.push(first + second);
                } else if (ch == '-') {
                    resStack.push(second - first);
                } else if (ch == '*') {
                    resStack.push(first * second);
                } else if (ch == '/') {
                    resStack.push(second / first);
                }
            }
        }

        return resStack.pop();
    }

}
