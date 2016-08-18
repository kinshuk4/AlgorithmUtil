package com.vaani.algo.paradigm.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetsIntersection {

    /**
     * O(nm) time.
     *
     * @param first
     * @param second
     * @return
     */
    public List<Integer> intersection(List<Integer> first, List<Integer> second) {
        List<Integer> res = new ArrayList<Integer>();

        for (int v1 : first) {
            // find v1 in v2
            for (int v2 : second) {
                if (v1 == v2) {
                    res.add(v1);
                    break;
                }
            }
        }

        return res;
    }

    /**
     * O(nlogn + mlogn), where n is the size of the larger set
     *
     * @param first
     * @param second
     * @return
     */
    public List<Integer> intersectionFast(List<Integer> first, List<Integer> second) {
        List<Integer> res = new ArrayList<Integer>();
        Set<Integer> set = new TreeSet<Integer>();

        List<Integer> smallList = null;
        if (first.size() > second.size()) {
            set.addAll(first);  // O(nlogn) time
            smallList = second;
        } else {
            set.addAll(second); // O(nlogn) time
            smallList = first;
        }

        for (int val : smallList) {
            if (set.contains(val)) { // O(logn) time each
                res.add(val);
            }
        }

        return res;
    }

}
