package com.vaani.algo.stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class PostfixEvaluator {
    private Stack<Integer> theStack;
    private String input;

    public PostfixEvaluator(String s) {
        input = s;
    }

    public int doParse() {
        theStack = new Stack<Integer>();
        char ch;
        int j;
        int num1, num2, interAns;
        for (j = 0; j < input.length(); j++) {
            ch = input.charAt(j);
            if (ch >= '0' && ch <= '9') {
                theStack.push((int) (ch - '0'));
            } else {
                num2 = theStack.pop();
                num1 = theStack.pop();
                switch (ch) {
                    case '+':
                        interAns = num1 + num2;
                        break;
                    case '-':
                        interAns = num1 - num2;
                        break;
                    case '*':
                        interAns = num1 * num2;
                        break;
                    case '/':
                        interAns = num1 / num2;
                        break;
                    default:
                        interAns = 0;
                        break;
                }
                theStack.push(interAns);
            }
        }
        interAns = theStack.pop();
        return interAns;
}
	
    public static void main(String[] args) throws Exception {
        String input;
        int output;
        while (true) {
            System.out.print("Enter postfix:");
            System.out.flush();
            input = getString();
            if (input.equals("")) {
                break;
            }
            PostfixEvaluator aParse = new PostfixEvaluator(input);
            output = aParse.doParse();
            System.out.println("Evaluates to " + output);
        }
    }

    public static String getString() throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader buf = new BufferedReader(isr);
        String s = buf.readLine();
        return s;
}
}
