package com.vaani.algo.ds.algos.stack;

import com.vaani.algo.util.IOUtilX;

import java.io.IOException;
import java.util.Stack;

/*https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Fstack%2FBracketsApp.java
 * 
 */
public class BracketMatchChecker {
    private String input;

    public BracketMatchChecker(String in) {
        this.input = in;
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (true) {
            System.out.println("Enter string containing delimiters:");
            System.out.flush();
            input = IOUtilX.readString();
            if (input.equals("")) {
                break;
            }
            BracketMatchChecker theChecker = new BracketMatchChecker(input);
            theChecker.check(input);
        }
    }

    public boolean check(String input) {
        Stack<Character> theStack = new Stack<>();
        for (int j = 0; j < input.length(); j++) {
            char ch = input.charAt(j);
            switch (ch) {
                case '{':
                case '[':
                case '(':
                    theStack.push(ch);
                    break;
                case '}':
                case ']':
                case ')':
                    if (!theStack.isEmpty()) {
                        char chx = theStack.peek();
                        if ((ch == '}' && chx != '{') || (ch == ']' && chx != '[')
                                || (ch == ')' && chx != '(')) {
                            System.out.println("Error:" + ch + " at " + (j + 1));
                            break;
                        }else{
                            //it is fine
                            theStack.pop();
                        }
                    } else {
                        System.out.println("Error:" + ch + " at " + (j + 1));
                        //stack is empty, but somehow closing parenthesis came, that means a problem
                        return false;

                    }
                default:
                    break;
            }
        }
        if (!theStack.isEmpty()) {
            System.out.println("Error:missing right delimiter");
            return false;
        } else {
            System.out.println("It is legitimate!");
            return true;
        }
    }


}
