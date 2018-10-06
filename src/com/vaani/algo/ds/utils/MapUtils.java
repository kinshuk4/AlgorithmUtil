package com.vaani.algo.ds.utils;

import java.util.*;


public class MapUtils {
    public static <T> Map<T, Integer> getFrequency(T[] list) {
        Map<T, Integer> map = new HashMap<>();
        for (T item : list) {
            if (map.containsKey(item)) {
                map.put(item, map.get(item) + 1);
            } else {
                map.put(item, 1);
            }
        }
        return map;
    }

}
