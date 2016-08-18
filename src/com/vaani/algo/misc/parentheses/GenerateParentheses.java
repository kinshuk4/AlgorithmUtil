package com.vaani.algo.misc.parentheses;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * For example, given n = 3, a solution set is:
 * <p>
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 * <p>
 * Created by Xiaomeng on 8/23/2014.
 */
public class GenerateParentheses {
    List<String> result;
    StringBuilder single;

    public static void main(String[] args) {
        GenerateParentheses test = new GenerateParentheses();
        for (String s : test.generateParenthesis(2)) {
            System.out.println(s);
        }
    }

    public List<String> generateParenthesis(int n) {
        result = new ArrayList<String>();
        single = new StringBuilder();
        if (n == 0) return result;
        dfs(n, n);
        return result;
    }

    public void dfs(int left, int right) {
        if (left == 0 && right == 0) {
            result.add(new String(single));
            return;
        }

        if (left > 0) {
            single.append('(');
            dfs(left - 1, right);
            single.deleteCharAt(single.length() - 1);
        }

        if (left < right) {
            single.append(')');
            dfs(left, right - 1);
            single.deleteCharAt(single.length() - 1);
        }
    }

    /*
    * Wrong solution:
    * Cannot generate (())(()) when n = 4
    * */
    public List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<String>();
        if (n == 0) return result;
        if (n == 1) {
            result.add("()");
            return result;
        }
        List<String> prevStrings = generateParenthesis2(n - 1);
        for (String s : prevStrings) {
            if (!result.contains("(" + s + ")")) result.add("(" + s + ")");
            if (!result.contains("()" + s)) result.add("()" + s);
            if (!result.contains(s + "()")) result.add(s + "()");
        }
        return result;
    }
}
