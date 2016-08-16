package com.vaani.algo.ds.core;

/**
 * Created by Xiaomeng on 8/21/2014.
 */
public class Interval {
    public int start;
    public int end;

    public Interval(){
        start = 0;
        end = 0;
    }

    public Interval(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
