package com.vaani.algo.bits;

/**
 * Created by kchandra on 16/08/16.
 */
public class IntToBinaryString {

    public static String toBinaryString(int integer) {
        StringBuilder builder = new StringBuilder();
        int temp;
        while (integer >= 0) { // 0 case
            temp = integer;
            integer = (temp >> 1);
            builder.append(String.valueOf(temp % 2));
            // if insert at 0, no need to reverseIterative
            // builder.insert(0, String.valueOf(temp % 2));
            if (integer == 0)
                break;
        }
        return builder.reverse().toString();
    }
}
