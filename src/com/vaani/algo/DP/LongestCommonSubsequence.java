package com.vaani.algo.DP;

/**
 * The longest common subsequence (LCS) problem is the problem of finding the longest subsequence common to all sequences in a set of sequences (often just two sequences).
 * It differs from problems of finding common substrings: unlike substrings, subsequences are not required to occupy consecutive positions within the original sequences.
 *
 * Examples:
 * LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
 * LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
 *
 * Created by Xiaomeng on 11/5/2014.
 */
public class LongestCommonSubsequence {
    /**
     * Time(2 ^ n)
     */
    public static int findLCS(String S, String T){
        return findLCS(S, T, S.length() - 1, T.length() - 1);
    }

    public static int findLCS(String S, String T, int m, int n){
        if(m < 0 || n < 0){
            return 0;
        }else if(S.charAt(m) == T.charAt(n)){
            return findLCS(S, T, m - 1, n - 1) + 1;
        }else{
            return Math.max(findLCS(S, T, m, n - 1), findLCS(S, T, m - 1, n));
        }
    }

    /**
     * Time(m * n), Space (m * n)
     * Reference: http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
     *
     */
    public static int findLCSDP(String S, String T){
        int m = S.length();
        int n = T.length();
        int[][] memo = new int[m + 1][n + 1];

        for(int i = 0; i < m + 1; i++){
            for(int j = 0; j < n + 1; j++){
                if(i == 0 || j == 0){
                    memo[i][j] = 0;
                }else{
                    if(S.charAt(i - 1) == T.charAt(j - 1)){
                        memo[i][j] = memo[i - 1][j - 1] + 1;
                    }else{
                        memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                    }
                }
            }
        }
        return memo[m][n];
    }

    /**
     * Reference: http://www.geeksforgeeks.org/printing-longest-common-subsequence/
     *
     */
    public static String printLCS(String S, String T){
        int m = S.length();
        int n = T.length();
        int[][] memo = new int[m + 1][n + 1];

        for(int i = 0; i < m + 1; i++){
            for(int j = 0; j < n + 1; j++){
                if(i == 0 || j == 0){
                    memo[i][j] = 0;
                }else{
                    if(S.charAt(i - 1) == T.charAt(j - 1)){
                        memo[i][j] = memo[i - 1][j - 1] + 1;
                    }else{
                        memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        int i = m, j = n;
        while(i > 0 && j > 0){
            if(S.charAt(i - 1) == T.charAt(j - 1)){
                result.insert(0, S.charAt(i - 1));
                i--;
                j--;
            }else if(memo[i - 1][j] > memo[i][j - 1]){
                i--;
            }else{
                j--;
            }
        }
        return result.toString();
    }

    public static void main(String[] args){
        String S = "AGGTAB";
        String T = "GXTXAYB";

        System.out.println(findLCSDP(S, T));
        System.out.println(printLCS(S, T));
    }
}
