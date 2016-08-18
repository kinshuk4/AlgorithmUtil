package com.vaani.algo.arithmetic;

/**
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 * <p>
 * Note: The numbers can be arbitrarily large and are non-negative.
 * <p>
 * Cleaner solution: http://blog.csdn.net/fightforyourdream/article/details/17370495
 */
public class MultiplyStrings {
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        String result = new String();
        for (int i = num2.length() - 1; i >= 0; i--) {
            Character single = num2.charAt(i);
            StringBuilder product = new StringBuilder(mulitplySingle(num1, single));
            for (int j = 0; j < num2.length() - i - 1; j++)
                product.append("0");
            result = add(result, product.toString());
        }
        return result;
    }

    public static String mulitplySingle(String num, char single) {
        int index = num.length() - 1;
        int singleNum = Character.getNumericValue(single);
        StringBuilder result = new StringBuilder();
        int extra = 0;
        while (index >= 0) {
            int first = Character.getNumericValue(num.charAt(index));
            int digit = (first * singleNum + extra) % 10;
            extra = (first * singleNum + extra) / 10;
            result.append(digit);
            index--;
        }
        if (extra > 0)
            result.append(extra);
        return result.reverse().toString();
    }

    public static String add(String a, String b) {
        if (a == null) return b;
        if (b == null) return a;
        StringBuilder result = new StringBuilder();
        a = new StringBuilder(a).reverse().toString();
        b = new StringBuilder(b).reverse().toString();
        int index = 0;
        int extra = 0;
        while (index < a.length() || index < b.length()) {
            int first = index < a.length() ? Character.getNumericValue(a.charAt(index)) : 0;
            int second = index < b.length() ? Character.getNumericValue(b.charAt(index)) : 0;
            int digit = (first + second + extra) % 10;
            extra = (first + second + extra) / 10;
            result.append(digit);
            index++;
        }
        if (extra > 0)
            result.append(extra);
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(MultiplyStrings.mulitplySingle("23", '6'));
        System.out.println(MultiplyStrings.add("138", "1150"));
        System.out.println(MultiplyStrings.multiply("9311", "1"));
    }
}
