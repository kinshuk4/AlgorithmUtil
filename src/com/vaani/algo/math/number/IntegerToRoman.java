package com.vaani.algo.math.number;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer, convert it to a roman numeral.
 * <p>
 * Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Created by Xiaomeng on 8/3/2014.
 */
public class IntegerToRoman {
    public static void main(String[] args) {
        int num = 1954;
        IntegerToRoman test = new IntegerToRoman();
        System.out.println(test.intToRoman(num));
    }

    public String intToRoman(int num) {
        Map<Integer, Character> map = buildMap();
        StringBuilder result = new StringBuilder();
        String number = String.valueOf(num);
        int exp = number.length() - 1;
        for (int i = 0; i < number.length(); i++) {
            int digit = Character.getNumericValue(number.charAt(i));
            int base = (int) Math.pow(10, exp);
            if (digit >= 1 && digit <= 3) {
                for (int j = 1; j <= digit; j++) result.append(map.get(base));
            } else if (digit == 4) {
                result.append(map.get(base));
                result.append(map.get(base * 5));
            } else if (digit >= 5 && digit < 9) {
                result.append(map.get(base * 5));
                for (int j = 1; j <= digit - 5; j++) result.append(map.get(base));
            } else if (digit == 9) {
                result.append(map.get(base));
                result.append(map.get(base * 10));
            }
            exp--;
        }
        return result.toString();
    }

    public Map<Integer, Character> buildMap() {
        Map<Integer, Character> map = new HashMap<Integer, Character>();
        map.put(1, 'I');
        map.put(5, 'V');
        map.put(10, 'X');
        map.put(50, 'L');
        map.put(100, 'C');
        map.put(500, 'D');
        map.put(1000, 'M');
        return map;
    }
}
