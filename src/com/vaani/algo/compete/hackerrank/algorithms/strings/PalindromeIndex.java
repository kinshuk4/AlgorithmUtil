package com.vaani.algo.compete.hackerrank.algorithms.strings;

import java.util.Scanner;
//https://github.com/JoshuaYang36/Java-Exercises/blob/master/Strings/PalindroneIndex.java
/*  ----------------------------------------------------------------------------------------------------------------/
/   Problem: Given a string, return the index of the character, which if removed, will leave a palindrone. Return   /
/            -1 if the string is already a palindrone.                                                              /
/                                                                                                                   /
/   Solution: Iteration the given string from both sides, checking if each character is the same. If so move on to  /
/             next character. Else, check if it is right or left charcter that needs to be removed. Return index    /
/                                                                                                                   /
/   Test Case 1: abab                                                                                               /
/   Returns: 0                                                                                                      /
/                                                                                                                   /
/   Test Case 2: bbba                                                                                               /
/   Returns: 3                                                                                                      /
/                                                                                                                   /
/   Test Case 3: a                                                                                                  /
/   Returns: -1                                                                                                     /
/   ---------------------------------------------------------------------------------------------------------------*/
public class PalindromeIndex {

    static int makePalindrome(String s) {
        int index = -1, l = s.length();
        if (isPalindrome(s)) {
            return -1;
        }
        for (int i = 0; i < l; i++) {
            StringBuilder sb = new StringBuilder(s);
            sb.deleteCharAt(i);
            String str = sb.toString();
            if (isPalindrome(str)) {
                return i;
            }
        }
        return index;
    }

    static boolean isPalindrome(String s) {
        int l = s.length(), i, j;
        for (i = 0, j = l - 1; i < l / 2; i++, j--) {
            if ((int) s.charAt(i) != (int) s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] a) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        String s[] = new String[t];

        for (int i = 0; i < t; i++) {
            s[i] = in.next();
        }

        for (int i = 0; i < t; i++) {
            System.out.println(makePalindrome(s[i]));
        }
    }
}
