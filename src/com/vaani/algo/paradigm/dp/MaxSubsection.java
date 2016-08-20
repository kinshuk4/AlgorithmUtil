package com.vaani.algo.paradigm.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a list of sections in format of <start_time, end_time, value>.
 * The time period of the sections may be overlapped and the value in
 * the overlapped parts would be the summation of all the values of each section.
 * Given such a list of section, please find out the maximum value sum during
 * any time period.
 * <p>
 * Time complexity: O(NlogN). Space complexity O(N).
 */
public class MaxSubsection {

    public int maxValue(List<Section> sections) {
        // transform the sections to timestamps
        List<Timestamp> timestamps = new ArrayList<Timestamp>();
        for (Section section : sections) {
            timestamps.add(new Timestamp(section.startTime, true, section.value));
            timestamps.add(new Timestamp(section.endTime, false, section.value));
        }
        Collections.sort(timestamps);

        int maxValue = 0;
        int curValue = 0;
        for (Timestamp t : timestamps) {
            if (t.isStartTime) {
                curValue += t.value;
            } else {
                curValue -= t.value;
            }
            maxValue = Math.max(maxValue, curValue);
        }

        return maxValue;
    }

}

class Section {
    double startTime;
    double endTime;
    double value;

    public Section(double startTime, double endTime, double value) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.value = value;
    }
}

class Timestamp implements Comparable<Timestamp> {
    double time;
    boolean isStartTime;
    double value;

    public Timestamp(double time, boolean isStartTime, double value) {
        this.time = time;
        this.isStartTime = isStartTime;
        this.value = value;
    }

    @Override
    public int compareTo(Timestamp that) {
        int cmp = Double.compare(this.time, that.time);
        if (cmp != 0) {
            return cmp;
        } else {
            if (this.isStartTime && !that.isStartTime) {
                return -1;
            } else if (!this.isStartTime && that.isStartTime) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
