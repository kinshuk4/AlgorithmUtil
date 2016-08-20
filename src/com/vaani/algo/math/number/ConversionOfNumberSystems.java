package com.vaani.algo.math.number;

import org.junit.Assert;
import org.junit.Test;

public class ConversionOfNumberSystems {

    public static String conversion(int number, int base) {
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            int i = number % base;
            switch (i) {
                case 11:
                    sb.append("A");
                    break;
                case 12:
                    sb.append("B");
                    break;
                case 13:
                    sb.append("C");
                    break;
                case 14:
                    sb.append("D");
                    break;
                case 15:
                    sb.append("E");
                    break;
                default:
                    sb.append(i);
            }
            number /= base;

        }
        return sb.reverse().toString();
    }


    public static String baseString(int num, int base) {
        String str, digit = "0123456789abcdef";
        if (num == 0) {
            return "";
        } else {
            str = baseString(num / base, base);
            return str + digit.charAt(num % base);
        }
    }

    @Test
    public void test() {
        System.out.println(conversion(1024, 16));
        System.out.println(baseString(1024, 16));
        Assert.assertEquals(conversion(1024, 16), baseString(1024, 16));
    }
}
