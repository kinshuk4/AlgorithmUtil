package com.vaani.algo.compete.hackerrank;

import java.util.Stack;

/**
 * Created by andersonkmi on 7/23/2016.
 */
public class VerifyParenthesisBalance {
    public boolean isBalanced(String sentence) {
        Stack<Character> characters = new Stack<>();

        char[] elements = sentence.toCharArray();
        for(int index = 0; index < elements.length; index++) {
            if(elements[index] == '(' || elements[index] == '[' || elements[index] == '{') {
                characters.push(elements[index]);
            } else if(elements[index] == ')') {
                if(!characters.isEmpty() && characters.peek() == '('){
                    characters.pop();
                } else {
                    return false;
                }
            } else if(elements[index] == ']') {
                if(!characters.isEmpty() && characters.peek() == '['){
                    characters.pop();
                } else {
                    return false;
                }
            } else if(elements[index] == '}') {
                if(!characters.isEmpty() && characters.peek() == '{'){
                    characters.pop();
                } else {
                    return false;
                }
            }
        }

        if(characters.isEmpty()) {
            return true;
        }
        return false;
    }
}
