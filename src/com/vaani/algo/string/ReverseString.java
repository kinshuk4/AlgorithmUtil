package com.vaani.algo.string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

//https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Fstrings%2FReverseString.java
public class ReverseString {
    private String input;
    private String output = "";//默认是null，所以此处将""赋值给它

    public ReverseString(String in) {
        input = in;
    }

    public static String getString() throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static void main(String[] args) throws Exception {
        String input, output;
        while (true) {
            System.out.println("Enter a String:");
            System.out.flush();
            input = ReverseString.getString();
            if (input.equals("")) {
                break;
            }
            ReverseString reverse = new ReverseString(input);
            output = reverse.doRev();
            System.out.println("Reversed:" + output);

        }

    }

    public String doRev() {
        int stackSize = input.length();
        Stack<Character> theStack = new Stack<>();
        for (int j = 0; j < input.length(); j++) {
            char ch = input.charAt(j);
            theStack.push(ch);
        }
        while (!theStack.isEmpty()) {
            char ch = theStack.pop();
            output = output + ch;
        }
        return output;
    }
}
