package com.vaani.algo.misc.parentheses;

import java.util.*;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 *
 * Created by Xiaomeng on 8/23/2014.
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        if(s == null || s.isEmpty()) return true;
        Map<Character, Character> map = buildMap();
        Stack<Character> stack = new Stack<Character>();
        for(Character ch : s.toCharArray()){
            if(map.containsKey(ch)){
                stack.push(ch);
            }else{
                if(stack.isEmpty()) return false;
                Character top = stack.pop();
                if(!map.get(top).equals(ch)) return false;
            }
        }
        return stack.isEmpty();
    }

    private Map<Character, Character> buildMap(){
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        return map;
    }

    public static void main(String[] args){
        ValidParentheses test = new ValidParentheses();
        System.out.println(test.isValid("([)]"));
    }
}
