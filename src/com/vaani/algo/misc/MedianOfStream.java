package com.vaani.algo.misc;

import java.util.Map;
import java.util.TreeMap;

public class MedianOfStream {

    private long size = 0;
    private Map<Integer, Integer> hist = new TreeMap<Integer, Integer>();

    public void add(int val) {
        Integer count = hist.get(val);
        if (count == null) {
            count = 0;
        }
        ++count;
        hist.put(val, count);
        ++size;
    }

    public int getMedian() {
        long median = size / 2;

        for (Map.Entry<Integer, Integer> entry : hist.entrySet()) {
            if (median < entry.getValue()) {
                return entry.getKey();
            }
            median -= entry.getValue();
        }

        return 0;
    }

}
