package com.vaani.algo.compete.codesignal.arcade.smoothsailing;

import java.util.*;

public class ReverseParentheses {


    public static String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        Map<Integer, StringBuilder> map = new HashMap<>();

        int openBracket = 0;
        map.put(openBracket, sb);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openBracket++;
                map.put(openBracket, new StringBuilder());
            } else if (c == ')') {
                openBracket--;
                map.get(openBracket).append(map.get(openBracket + 1).reverse());
            } else {
                map.get(openBracket).append(c);

            }

        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseParentheses("a(bcdefghijkl(mno)p)q"));
    }

}
