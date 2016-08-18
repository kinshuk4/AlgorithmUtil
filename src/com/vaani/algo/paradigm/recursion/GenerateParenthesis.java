package com.vaani.algo.paradigm.recursion;

import java.util.Stack;

/**
 * Generate all valid parenthesis consists of (, [, {, }, ], ).
 */
public class GenerateParenthesis {

    public static void generate(int n1, int n2, int n3) {
        int leftSmall = n1, rightSmall = n1;
        int leftMid = n2, rightMid = n2;
        int leftBig = n3, rightBig = n3;
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<Character>();
        generate(leftSmall, rightSmall, leftMid, rightMid, leftBig, rightBig, stack, sb);
    }

    public static void generate(int leftSmall, int rightSmall, int leftMid, int rightMid, int leftBig, int rightBig, Stack<Character> stack, StringBuilder sb) {
        if (leftSmall == 0 && rightSmall == 0 && leftMid == 0 && rightMid == 0 && leftBig == 0 && rightBig == 0) {
            System.out.println(sb.toString());
            return;
        }

        if (!stack.isEmpty()) { // can append any left parenthesis and append corresponding right parenthesis
            char leftPar = stack.peek();

            if (leftSmall > 0) {
                sb.append('(');
                stack.push('(');
                generate(leftSmall - 1, rightSmall, leftMid, rightMid, leftBig, rightBig, stack, sb);
                stack.pop();
                sb.deleteCharAt(sb.length() - 1);
            }

            if (leftMid > 0) {
                sb.append('[');
                stack.push('[');
                generate(leftSmall, rightSmall, leftMid - 1, rightMid, leftBig, rightBig, stack, sb);
                stack.pop();
                sb.deleteCharAt(sb.length() - 1);
            }

            if (leftBig > 0) {
                sb.append('{');
                stack.push('{');
                generate(leftSmall, rightSmall, leftMid, rightMid, leftBig - 1, rightBig, stack, sb);
                stack.pop();
                sb.deleteCharAt(sb.length() - 1);
            }

            if (leftPar == '(' && rightSmall > leftSmall) {
                sb.append(')');
                stack.pop();
                generate(leftSmall, rightSmall - 1, leftMid, rightMid, leftBig, rightBig, stack, sb);
                stack.push(leftPar);
                sb.deleteCharAt(sb.length() - 1);
            } else if (leftPar == '[' && rightMid > leftMid) {
                sb.append(']');
                stack.pop();
                generate(leftSmall, rightSmall, leftMid, rightMid - 1, leftBig, rightBig, stack, sb);
                stack.push(leftPar);
                sb.deleteCharAt(sb.length() - 1);
            } else if (leftPar == '{' && rightBig > leftBig) {
                sb.append('}');
                stack.pop();
                generate(leftSmall, rightSmall, leftMid, rightMid, leftBig, rightBig - 1, stack, sb);
                stack.push(leftPar);
                sb.deleteCharAt(sb.length() - 1);
            }

        } else { // stack is empty
            if (leftSmall > 0) {
                sb.append('(');
                stack.push('(');
                generate(leftSmall - 1, rightSmall, leftMid, rightMid, leftBig, rightBig, stack, sb);
                stack.pop();
                sb.deleteCharAt(sb.length() - 1);
            }

            if (leftMid > 0) {
                sb.append('[');
                stack.push('[');
                generate(leftSmall, rightSmall, leftMid - 1, rightMid, leftBig, rightBig, stack, sb);
                stack.pop();
                sb.deleteCharAt(sb.length() - 1);
            }

            if (leftBig > 0) {
                sb.append('{');
                stack.push('{');
                generate(leftSmall, rightSmall, leftMid, rightMid, leftBig - 1, rightBig, stack, sb);
                stack.pop();
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int small = 2;
        int mid = 1;
        int big = 0;
        generate(small, mid, big);
    }

}
