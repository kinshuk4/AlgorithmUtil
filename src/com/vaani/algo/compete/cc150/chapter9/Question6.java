package com.vaani.algo.compete.cc150.chapter9;

import java.util.ArrayList;

/**
 * Implement an algorithm to print all valid (e.g., properly opened and closed)
 * combinations of n-pairs of parentheses.
 */
// O(2^n) space, O(2^n) time, a more strict bound is the catalan number 
public class Question6 {

    // available at leetcode.com online judge, question Generate Parentheses
    public static class Solution {
        public ArrayList<String> generateParenthesis(int n) {
            // Start typing your Java solution below
            // DO NOT write main() function
            ArrayList<String> res = new ArrayList<String>();
            StringBuilder sb = new StringBuilder();
            int nLeft = n;
            int nRight = n;
            generate(nLeft, nRight, sb, res);
            return res;
        }

        private void generate(int nLeft, int nRight, StringBuilder sb, ArrayList<String> res) {
            if (nLeft == 0 && nRight == 0) {
                res.add(sb.toString());
                return;
            }

            if (nLeft > 0) {
                sb.append("(");
                generate(nLeft - 1, nRight, sb, res);
                sb.deleteCharAt(sb.length() - 1);
            }

            if (nRight > nLeft) {
                sb.append(")");
                generate(nLeft, nRight - 1, sb, res);
                sb.deleteCharAt(sb.length() - 1);
            }

        }
    }

}