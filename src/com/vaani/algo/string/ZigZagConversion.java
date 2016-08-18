package com.vaani.algo.string;

import java.util.ArrayList;
import java.util.List;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * <p>
 * And then read line by line: "PAHNAPLSIIGYIR"
 * <p>
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string text, int nRows);
 * <p>
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * <p>
 * Created by Xiaomeng on 9/13/2014.
 */
public class ZigZagConversion {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int nRows = 3;
        ZigZagConversion test = new ZigZagConversion();
        System.out.println(test.convert(s, nRows));
    }

    public String convert(String s, int nRows) {
        List<List<Character>> matrix = new ArrayList<List<Character>>();
        for (int i = 0; i < nRows; i++) {
            matrix.add(new ArrayList<Character>());
        }

        int index = 0;
        while (index < s.length()) {
            for (int i = 0; i < nRows; i++) {
                if (index >= s.length()) break;
                matrix.get(i).add(s.charAt(index));
                index++;
            }

            for (int i = nRows - 2; i >= 1; i--) {
                if (index >= s.length()) break;
                matrix.get(i).add(s.charAt(index));
                index++;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                result.append(matrix.get(i).get(j));
            }
        }
        return result.toString();
    }
}
