package com.vaani.algo.string.perm;

//https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Fstrings%2FCombination.java
public class Combination {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        char[] strs = new char[]{'a', 'b', 'c', 'd', 'e'};
        for (int i = 1; i <= strs.length; i++) {
            combination(strs, 0, i);
        }

        combination();
    }


    public static void combination(char[] strs, int start, int number) {
        if (number <= 0 || start >= strs.length) {
            System.out.println(sb.toString());
            return;
        }
        sb.append(strs[start]);
        combination(strs, start + 1, number - 1);
        sb.deleteCharAt(sb.length() - 1);
        if (strs.length - start > number) {
            combination(strs, start + 1, number);
        }

    }


    public static void combination() {
        String[] str = {"a", "b", "c"};
        int n = str.length;
        int nbit = 1 << n;
        for (int i = 0; i < nbit; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = 1 << j;
                if ((tmp & i) != 0) { // & 表示与。两个位都为1时，结果才为1
                    System.out.print(str[j]);
                }
            }
            System.out.println();
        }
    }
}
