package com.vaani.algo.math.number;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a roman numeral, convert it to an integer.
 * <p>
 * Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Created by Xiaomeng on 8/3/2014.
 */
public class RomanToInteger {
    public static void main(String[] args) {
        RomanToInteger test = new RomanToInteger();
        System.out.println(test.romanToInt("DCXXI"));
    }

    public int romanToInt(String s) {
        Map<Character, Integer> map = buildMap();
        //MCMLIV -> 1954
        int result = 0;
        int i = 0;
        while (i < s.length()) {
            Character curr = s.charAt(i);
            if (i == s.length() - 1) {
                result += map.get(curr);
                break;
            }
            Character next = s.charAt(i + 1);
            if (map.get(curr) < map.get(next)) {
                result += map.get(next) - map.get(curr);
                i += 2;
            } else {
                result += map.get(curr);
                i++;
            }
        }
        return result;
    }

    public Map<Character, Integer> buildMap() {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        return map;
    }
}
