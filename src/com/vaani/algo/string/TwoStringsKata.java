package com.vaani.algo.string;


import java.io.*;
import java.util.*;

public class TwoStringsKata {
    public String check(String string1, String string2) {
        char[] string1Letters = string1.toLowerCase().toCharArray();
        char[] string2Letters = string2.toLowerCase().toCharArray();
        int[][] matches = new int[26][2];
        for (int i = 0; i < string1Letters.length; i++) {
            matches[string1Letters[i] - 'a'][0]++;
        }
        for (int i = 0; i < string2Letters.length; i++) {
            if (matches[string2Letters[i] - 'a'][0] > 0) {
                return "YES";
            }
        }
        return "NO";
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        TwoStringsKata s = new TwoStringsKata();
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        while (in.hasNextLine()) {
            String str1 = in.next();
            String str2 = in.next();
            System.out.println(s.check(str1, str2));
        }
    }
}
