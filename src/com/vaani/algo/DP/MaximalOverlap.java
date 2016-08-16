package com.vaani.algo.DP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given the intervals [start, end], find the maximal overlaps.
 *
 */
public class MaximalOverlap {
  
  static class Interval {
    int start, end;
    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }
  
  /**
   * O(nlgn) time, O(n) space.
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
      }
      else {
        --cur;
      }
    }
    return maxOverlap;
  }
  
  class Endpoint implements Comparable {
    int timestamp;
    boolean isStart;
    public Endpoint(int timestamp, boolean isStart) {
      this.timestamp = timestamp;
      this.isStart = isStart;
    }
    @Override
    public int compareTo(Object o) {
      if (o instanceof Endpoint) {
        Endpoint other = (Endpoint)o;
        return Integer.compare(timestamp, other.timestamp);
      }
      return 0;
    }
  }
  

}
