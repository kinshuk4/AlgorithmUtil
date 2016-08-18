package com.vaani.algo.arithmetic;

/**
 * Given two binary strings, return their sum (also a binary string).
 * <p>
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 */
public class AddBinary {
    public static void main(String[] args) {
        String a = "1";
        String b = "111";
        AddBinary test = new AddBinary();
        System.out.println(test.addBinary(a, b));
    }

    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        a = new StringBuilder(a).reverse().toString();
        b = new StringBuilder(b).reverse().toString();

        int index = 0;
        int extra = 0;
        while (index < a.length() || index < b.length()) {
            int first = index < a.length() ? Character.getNumericValue(a.charAt(index)) : 0;
            int second = index < b.length() ? Character.getNumericValue(b.charAt(index)) : 0;
            int digit = (first + second + extra) % 2;
            extra = first + second + extra >= 2 ? 1 : 0;
            result.append(digit);
            index++;
        }

        if (extra == 1)
            result.append(1);

        return result.reverse().toString();
    }
}
