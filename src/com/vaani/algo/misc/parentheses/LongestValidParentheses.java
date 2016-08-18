package com.vaani.algo.misc.parentheses;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * <p>
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * <p>
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 * <p>
 * Created by Xiaomeng on 8/24/2014.
 */
public class LongestValidParentheses {
    public static void main(String[] args) {
        LongestValidParentheses test = new LongestValidParentheses();
        String s = "()(())";
        System.out.println(test.longestValidParentheses1(s));
    }

    /**
     * IStack solution
     */
    public int longestValidParentheses1(String s) {
        int max = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (ch.equals(')') && !stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                stack.pop();
                if (stack.isEmpty()) {
                    max = i + 1;
                } else {
                    max = Math.max(i - stack.peek(), max);
                }
            } else {
                stack.push(i);
            }
        }
        return max;
    }

    /**
     * DP solution
     * http://blog.csdn.net/abcbc/article/details/8826782
     * http://www.cnblogs.com/huntfor/p/3886111.html
     */
    public int longestValidParentheses2(String s) {
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                int j = i + dp[i + 1] + 1;
                if (j < s.length() && s.charAt(j) == ')') {
                    dp[i] = dp[i + 1] + 2;
                    if (j + 1 < s.length()) dp[i] += dp[j + 1];
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}
