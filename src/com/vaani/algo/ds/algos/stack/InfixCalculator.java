package com.vaani.algo.ds.algos.stack;

import java.util.*;

/**
 * Write a function that calculates input strings with operators +.-,*,/ e.g. "5 + 5 * 6" should output 35
 */
public class InfixCalculator {
    public static int calculate(String input) {
        Map<String, Integer> precedence = new HashMap<String, Integer>();
        precedence.put("(", -1);
        precedence.put("+", 1);
        precedence.put("-", 1);
        precedence.put("*", 2);
        precedence.put("/", 2);

        Stack<String> stack = new Stack<String>();
        List<String> postfix = new ArrayList<String>();

        List<String> tokens = parse(input);
        for (String token : tokens) {
            if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.add(stack.pop());
                }
                stack.pop();
            } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                while (!stack.isEmpty() && precedence.get(token) <= precedence.get(stack.peek())) {
                    postfix.add(stack.pop());
                }
                stack.push(token);
            } else {
                postfix.add(token);
            }
        }

        while (!stack.isEmpty()) {
            postfix.add(stack.pop());
        }

        Stack<Integer> stack2 = new Stack<Integer>();
        for (String token : postfix) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int operand2 = stack2.pop();
                int operand1 = stack2.pop();
                if (token.equals("+")) {
                    stack2.push(operand1 + operand2);
                } else if (token.equals("-")) {
                    stack2.push(operand1 - operand2);
                } else if (token.equals("*")) {
                    stack2.push(operand1 * operand2);
                } else if (token.equals("/")) {
                    stack2.push(operand1 / operand2);
                }
            } else {
                stack2.push(Integer.parseInt(token));
            }
        }
        return stack2.pop();
    }

    public static List<String> parse(String input) {
        List<String> result = new ArrayList<String>();
        int i = 0;
        while (i < input.length()) {
            char ch = input.charAt(i);
            if (ch >= '0' && ch <= '9') {
                int j = i + 1;
                while (j < input.length() && input.charAt(j) >= '0' && input.charAt(j) <= '9') j++;
                result.add(input.substring(i, j));
                i = j;
            } else {
                result.add(String.valueOf(ch));
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String input1 = "(300+23)*(43-21)/(1+1)";    // --> 3553
        String input2 = "(4+8)*(6-5)/((3-2)*(2+2))"; // -->3
        String input3 = "3+5*6/2";                   // -->18
        System.out.println(calculate(input1));
        System.out.println(calculate(input2));
        System.out.println(calculate(input3));
    }
}
