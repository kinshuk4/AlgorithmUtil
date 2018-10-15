package com.vaani.algo.paradigm.dp;

import com.vaani.algo.ds.core.visual.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given the intervals [start, end], find the maximal overlaps.
 */
public class MaximalOverlap {

    /**
     * O(nlgn) time, O(n) space.
     *
     * @param intervals
     * @return
     */
    public int maximalOverlapped(List<Interval> intervals) {
        List<Endpoint> endpoints = new ArrayList<Endpoint>();
        for (Interval interval : intervals) {
            endpoints.add(new Endpoint(interval.start, true));
            endpoints.add(new Endpoint(interval.end, false));
        }

        Collections.sort(endpoints);
        int maxOverlap = 0;
        int cur = 0;
        for (Endpoint endpoint : endpoints) {
            if (endpoint.isStart) {
                ++cur;
                maxOverlap = Math.max(maxOverlap, cur);
            } else {
                --cur;
            }
        }
        return maxOverlap;
    }


    static class Endpoint implements Comparable {
        int timestamp;
        boolean isStart;

        public Endpoint(int timestamp, boolean isStart) {
            this.timestamp = timestamp;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Endpoint) {
                Endpoint other = (Endpoint) o;
                int result = Integer.compare(timestamp, other.timestamp);
                //in case a result is 0, then one which starts early should be returned
                if(result==0){
                    return Boolean.compare(((Endpoint) o).isStart, isStart);
                }else{
                    return result;
                }
            }
            return 0;
        }

        @Override
        public String toString() {
            return "Endpoint{" +
                    "timestamp=" + timestamp +
                    ", isStart=" + isStart +
                    '}';
        }
    }

    public static void main(String[] args) {
        Endpoint e1 = new Endpoint(0, true);
        Endpoint e2 = new Endpoint(0, false);
        Endpoint e3 = new Endpoint(0, true);
        List<Endpoint> list = new LinkedList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        Collections.sort(list);
        System.out.println(list);
    }

}
