package com.vaani.algo.ds.algos.stack;

import com.vaani.algo.util.IOUtilX;

import java.io.IOException;
import java.util.Stack;

//https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Fstack%2FInfixApp.java
public class InfixToPostfix {
    private Stack<Character> operatorStack;
    private String input;
    private String output = "";

    public InfixToPostfix(String in) {
        input = in;
        operatorStack = new Stack<Character>();
    }

    public static void main(String[] args) throws IOException {
        String input, output;
        while (true) {
            System.out.println("Enter infix (Enter to skip):");
            System.out.flush();
            input = IOUtilX.readString();
            if (input.equals("")) {
                break;
            }
            InfixToPostfix theTrans = new InfixToPostfix(input);
            output = theTrans.transformInfixToPostfix();
            System.out.println("Postfix is " + output + "\n");
        }

    }


    public String transformInfixToPostfix() {
        for (int j = 0; j < input.length(); j++) {
            char ch = input.charAt(j);

            switch (ch) {
                case '+':
                case '-':
                case '*':
                case '/':
                case '(':
                case '^':
                case '$':
                    handleOperator(ch);
                    break;
                case ')':
                    handleCloseParenthesis(ch);
                    break;
                default:
                    output = output + ch;
                    break;
            }
        }
        while (!operatorStack.isEmpty()) {

            output = output + operatorStack.pop();
        }

        return output;
    }

    public int getOperatorPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
            case '$':
                return 3;
            case '(':
                return 0;
            case '#':
                return -1;
            default:
                return 8;
        }
    }

    public void handleOperator(char operator) {
        int precedence = getOperatorPrecedence(operator);
        while (!operatorStack.isEmpty()) {
            char opTop = operatorStack.pop();

            int precedenceTop = getOperatorPrecedence(opTop);

            if (precedenceTop < precedence) {
                operatorStack.push(opTop);
                break;
            } else {
                output = output + opTop;
            }

        }
        operatorStack.push(operator);
    }

    public void handleCloseParenthesis(char ch) {
        while (!operatorStack.isEmpty()) {
            char chx = operatorStack.pop();
            if (chx == '(') {
                break;
            } else {
                output = output + chx;
            }
        }
    }
}
