/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 13/08/2015
 * 
 * Hacker rank
 * New York Life Hackathon
 * Problem: Hide the word
 * Status: accepted
 * 
 * Bob wants to hide a secret word before sending it over email. He have invented a 
 * new encoding scheme for that!
 * Suppose he wants to encode the string s="Tom" of length n = 3. The string is indexed 
 * from 1 to n. First he takes a random permutation between 1 to n. Suppose the permutation
 * is p={2, 3, 1}. He replace s[i] with the letter in s[p[i]]. So he replace s[1] with 
 * s[p[1]]=s[2]='o', s[2] with s[p[2]]=s[3]='m' and s[3] with s[p[3]]=s[1]='T'. The new 
 * string is 'omT'.
 * To make the encoding harder to break, he performs the encoding k times. So if k=2, 
 * "Tom" becomes:
 *
 * "Tom" -> "omt" -> "mto"
 * 
 * So after 2 steps "Tom" becomes "mto". In each individual steps, encoding in all the 
 * indexes is performed simultaneously.
 * Complete a function called encode. First parameter is an integer array p, denoting the 
 * permutation. Than it will take number of steps k and the original string s as parameters.
 * Perform the encoding k times and return the encoded string.
 *
 * Constraints:
 * 1<=n<=50
 * 1<=k<=2*109
 *
 * Length of string s and array p are same.
 * 
 * Input Format
 * 
 * p, k, s
 * 
 * Output Format
 * 
 * Encoded string
 * 
 * Sample Input
 * 
p={2, 3, 1}
k=2
s="tom"
 * 
p={4, 3, 2, 1}
k=5
s="abcd"
 *
 * Sample Output
 * 
mot
 *
dcba
 *
 */


package com.vaani.algo.compete.hackerrank.hackathon.newyorklife;

import org.junit.Assert;


public class HideTheWord {

    static String[] M = new String[50 * 50 * 2];
    
    static String encode(int[] p, int k, String s) {
        if (k == 0) return s;
        String buf = s;
        int i = 0;
        M[i++] = buf;
        while (!s.equals(buf = permut(p, buf))) {
            if (i == k) return buf;
            M[i] = buf;
            i++;
        }
        return M[k % i];
    }
    
    private static String permut(int[] p, String buf) {
        char[] a = new char[buf.length()];
        for (int i = 0; i < a.length; ++i) {
            a[i] = buf.charAt(p[i] - 1);
        }
        return new String(a);
    }
    
    public static void main(String[] args) {
        Assert.assertEquals("dcba", encode(new int[]{4, 3, 2, 1}, 5, "abcd"));
    }
    
}
