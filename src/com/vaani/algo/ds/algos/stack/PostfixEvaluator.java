package com.vaani.algo.ds.algos.stack;

import java.security.InvalidAlgorithmParameterException;
import java.util.Stack;

import static com.vaani.algo.util.IOUtilX.readString;

public class PostfixEvaluator {
    private Stack<Integer> operandStack;
    private String input;

    public PostfixEvaluator(String s) {
        input = s;
    }

    public static void main(String[] args) throws Exception {
        String input;
        int output;
        while (true) {
            System.out.print("Enter postfix (Enter to end):");
            System.out.flush();
            input = readString();
            if (input.equals("")) {
                break;
            }
            PostfixEvaluator aParse = new PostfixEvaluator(input);
            output = aParse.doParse();
            System.out.println("Evaluates to " + output);
        }
    }



    public int doParse() throws InvalidAlgorithmParameterException {
        operandStack = new Stack<Integer>();
        char ch;
        int j;
        int num1, num2, interAns;
        for (j = 0; j < input.length(); j++) {
            ch = input.charAt(j);
            if (ch >= '0' && ch <= '9') {
                operandStack.push((int) (ch - '0'));
            } else {
                //assuming only dealing with binary operators
                if(operandStack.size()<2){
                    throw new InvalidAlgorithmParameterException("Invalid postfix expression.");
                }
                num2 = operandStack.pop();
                num1 = operandStack.pop();
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
                operandStack.push(interAns);
            }
        }
        interAns = operandStack.pop();
        return interAns;
    }
}
