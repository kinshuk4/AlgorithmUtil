/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 09/06/2016
 * 
 * Weaveworks Codility
 * Problem: Task 1
 * Status: passed
 * 
 * Problem
 * 
 * Given a string find a longest substring in it which satisfies following
 * requirements:
 * 
 * - it should not contain digits
 * - it should contain at least one upper letter
 * 
 * Input Format
 * 
 * String
 * 
 * Output Format
 * 
 * Length of maximum substring 
 * 
 * Sample Input
 * 
a0ba
A
Ya0Uaa9aaaaaaaaaaaaaa
 * 
 * Sample Output
 * 
-1
1
3
 * 
 */

package com.vaani.algo.compete.codility.longestpass;

import static org.junit.Assert.assertEquals;

public class Task1_Longest_password_substring {

//    public int solution(String S) {
//        int s = 0;
//        int e = 0;
//        boolean isValid = false;
//        int max = -1;
//        S += '0';
//        for (int i = 0; i < S.length(); i++) {
//            char ch = S.charAt(i);
//            if (Character.isUpperCase(ch)) {
//                isValid = true;
//            } else if (Character.isDigit(ch)) {
//                if (isValid && (e - s > max)) {
//                    max = e - s;
//                }
//                isValid = false;
//                s = i + 1;
//                e = s;
//                continue;
//            }
//            e++;
//        }
//        return max;
//    }

    public static int solution(final String pass) {
        int validPassLength = -1;
        final String [] strs = pass.split("[0-9]");
        for (String s : strs) {
            final int subStringMaxLength = subStringPassLength(s);
            if(subStringMaxLength > validPassLength) {
                validPassLength = subStringMaxLength;
            }
        }
        return validPassLength;
    }

    private static int subStringPassLength(final String subString) {
        final char [] chars = subString.toCharArray();
        int subStringMaxLength = -1;
        for (int i = 0 ; i < chars.length ; i ++) {
            if (Character.isUpperCase(chars[i])) {
                subStringMaxLength = chars.length;
                break;
            }
        }
        return subStringMaxLength;
    }
//    public static void main(String[] args){
//        System.out.println(solution("a0Ba"));
//    }
    public static int solve(String S) {
        return new Task1_Longest_password_substring().solution(S);
    }
    
    public static void main(String[] args) {
        assertEquals(2, solve("a0Ba"));
        assertEquals(-1, solve("123"));
        assertEquals(3, solve("a0Ba9aaB"));
        assertEquals(3, solve("a0Ba9aaB8asasasd9Aa"));
        assertEquals(-1, solve("a0bb"));
        assertEquals(4, solve("AAAA"));
        assertEquals(3, solve("aBa"));
        assertEquals(-1, solve(""));
        assertEquals(1, solve("12333A"));
        assertEquals(-1, solve("43aa4a"));
        assertEquals(2, solve("43aa4aA"));
    }
    
}
