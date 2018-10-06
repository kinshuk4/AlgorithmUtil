package com.vaani.algo.ds.utils;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeMapUtils {
    private int getMedian(TreeSet<Integer> treeSet) {
        int n = treeSet.size();
        if (n == 1) {
            return treeSet.iterator().next();
        } else if (n == 0) {
            return -1;
        }
        Iterator<Integer> iterator = treeSet.iterator();
        int i = 0;
        int center = n / 2 - 1;
        int retValue = 0;
        while (iterator.hasNext()) {
            if (i > center) break;
            i++;
            retValue = iterator.next();
        }
        if (n % 2 == 0) {
            retValue += iterator.next();
        } else {
            retValue = iterator.next();
        }

        return retValue;
    }
}
