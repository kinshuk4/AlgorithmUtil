package com.vaani.algo.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 * <p>
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 * <p>
 * For example,
 * <p>
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 */
public class TwoSum3 {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    public void add(int number) {
        if (!map.containsKey(number)) {
            map.put(number, 1);
        } else {
            map.put(number, map.get(number) + 1);
        }
    }

    public boolean find(int value) {
        for (int key : map.keySet()) {
            int target = value - key;
            if (target == key) {
                if (map.get(key) > 1) return true;
            } else {
                if (map.containsKey(target)) return true;
            }
        }
        return false;
    }
}
