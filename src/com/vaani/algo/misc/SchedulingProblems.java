package com.vaani.algo.misc;

import com.vaani.algo.ds.core.visual.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Different scheduling problems
 */
public class SchedulingProblems {
    /**
     * Find the maximum number of overlapping intervals.
     * <p>
     * For example, given a series of the following int-based intervals [[1,2], [3,4], [2,10]]
     * the following intervals overlap: [1,2] with [2,10], [3,4] with [2,10], hence the maximum
     * number of overlapping intervals is 2.
     * <p>
     * 这个解法有个注意事项，如果interval 1的start time 和interval 2 的end time相等
     * ，应该把 interval 2的 end time排在前面（ｉｎｃｒｅａｓｉｎｇ　ｏｒｄｅｒ）
     * 不然像（２，　３），(3, 4)这样的可能返回2，实际应该返回1.
     * <p>
     * Reference:
     * https://gist.github.com/Thinkscape/5341248
     * http://stackoverflow.com/questions/18365107/maximum-no-of-overlaps-of-all-time-intervals?answertab=active#tab-top
     * <p>
     * Time: O(nlogn)
     */
    public static int findMax(List<Interval> intervals) {
        List<Integer> list = new ArrayList<Integer>();
        for (Interval interval : intervals) {
            list.add(interval.start);
            list.add(-interval.end);
        }

        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (Math.abs(o1) == Math.abs(o2)) {
                    return o1 - o2;
                } else {
                    return Math.abs(o1) - Math.abs(o2);
                }
            }
        };
        Collections.sort(list, comparator);

        int max = 0;
        int counter = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > 0) {
                counter++;
            } else {
                counter--;
            }
            max = Math.max(max, counter);
        }
        return max;
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<Interval>();
        intervals.add(new Interval(4, 5));
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 3));
        intervals.add(new Interval(3, 4));


        System.out.println(findMax(intervals));
    }

    /**
     * Given, the starting and ending time of different meetings, what is the minimum number of conference rooms that will be enough to accommodate all of them. E.g,
     * <p>
     * Meeting I – 8:30 – 12:50
     * Meeting II – 10:15 – 11:30
     * Meeting III – 11:45 – 1:30
     * <p>
     * Output: 2
     * <p>
     * Same about the first one? Think about it.
     * <p>
     * Reference:
     * http://www.mitbbs.com/article_t1/JobHunting/32832867_0_1.html
     * http://www.geeksforgeeks.org/amazon-interview-set-111-campus/
     */
    public int fileMinMeetingRooms(List<Interval> intervals) {
        List<Integer> list = new ArrayList<Integer>();
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (Math.abs(o1) == Math.abs(o2)) {
                    return o1 - o2;
                } else {
                    return Math.abs(o1) - Math.abs(o2);
                }
            }
        };

        for (Interval interval : intervals) {
            list.add(interval.start);
            list.add(-interval.end);
        }
        System.out.println(list);
        Collections.sort(list, comparator);
        System.out.println(list);

        int max = 0;
        int counter = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > 0) {
                counter++;
            } else {
                counter--;
            }
            max = Math.max(max, counter);
        }
        return max;
    }

    /**
     * Given a set of n jobs with [start time, end time, cost] find a subset so that no 2 jobs overlap and the cost is maximum
     * <p>
     * Reference:
     * http://cs.stackexchange.com/questions/11265/find-non-overlapping-scheduled-jobs-with-maximum-cost (last reply)
     * http://stackoverflow.com/questions/4354988/finding-subset-of-disjunctive-intervals-with-maximal-weights
     * http://www.cs.princeton.edu/courses/archive/spr05/cos423/lectures/06dynamic-programming.pdf (check this one)
     */
    public int findMaxCost(List<Interval> intervals) {
        //TODO
        return 0;
    }

    /**
     * Activity selection problem
     * <p>
     * You are given n activities with their start and finish times.
     * Select the maximum number of activities that can be performed by a single person, assuming that a person can only work on a single activity at a time.
     * <p>
     * Reference: http://www.geeksforgeeks.org/greedy-algorithms-set-1-activity-selection-problem/
     */
    public int findMaxActivities(List<Interval> intervals) {
        //TODO
        return 0;
    }
}
