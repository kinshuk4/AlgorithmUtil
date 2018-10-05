package com.vaani.algo.ds.core.visual;


public class Interval implements Comparable<Interval> {
    public int start;
    public int end;
    public int value; // some value between interval

    public Interval() {
        this(0, 0);
    }

    public Interval(int start, int end) {
        this(start, end, 0);
    }

    public Interval(int start, int end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    @Override
    public int compareTo(Interval o) {
        if (start < o.start) {
            return -1;
        } else if (start > o.start) {
            return 1;
        } else {
            if (end < o.end) {
                return -1;
            } else if (end > end) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
