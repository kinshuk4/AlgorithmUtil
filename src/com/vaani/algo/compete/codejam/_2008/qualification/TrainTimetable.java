/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Code jam
 * Qualification Round 2008
 * Problem B. Train Timetable
 * Small input: correct
 * Large input: correct
 * 
 * Problem
 * 
 * A train line has two stations on it, A and B. Trains can take trips from A to B or 
 * from B to A multiple times during a day. When a train arrives at B from A (or 
 * arrives at A from B), it needs a certain amount of time before it is ready to take 
 * the return journey - this is the turnaround time. For example, if a train arrives at 
 * 12:00 and the turnaround time is 0 minutes, it can leave immediately, at 12:00.
 * 
 * A train timetable specifies departure and arrival time of all trips between A and B.
 * The train company needs to know how many trains have to start the day at A and B in 
 * order to make the timetable work: whenever a train is supposed to leave A or B, there 
 * must actually be one there ready to go. There are passing sections on the track, so 
 * trains don't necessarily arrive in the same order that they leave. Trains may not 
 * travel on trips that do not appear on the schedule.
 * 
 * Input
 * 
 * The first line of input gives the number of cases, N. N test cases follow.
 * Each case contains a number of lines. The first line is the turnaround time, T, in 
 * minutes. The next line has two numbers on it, NA and NB. NA is the number of trips 
 * from A to B, and NB is the number of trips from B to A. Then there are NA lines giving
 * the details of the trips from A to B.
 * Each line contains two fields, giving the HH:MM departure and arrival time for that
 * trip. The departure time for each trip will be earlier than the arrival time. All 
 * arrivals and departures occur on the same day. The trips may appear in any order - they
 * are not necessarily sorted by time. The hour and minute values are both two digits, 
 * zero-padded, and are on a 24-hour clock (00:00 through 23:59).
 * After these NA lines, there are NB lines giving the departure and arrival times for the
 * trips from B to A.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: " followed by the number of
 * trains that must start at A and the number of trains that must start at B.
 * 
 * Sample
 * 
 * Input
 * 2
 * 5
 * 3 2
 * 09:00 12:00
 * 10:00 13:00
 * 11:00 12:30
 * 12:02 15:00
 * 09:00 10:30
 * 2
 * 2 0
 * 09:00 09:01
 * 12:00 12:02
 * 
 * Output
 * Case #1: 2 2
 * Case #2: 2 0
 * 
 */

package com.vaani.algo.compete.codejam._2008.qualification;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class TrainTimetable {

    static class Trip {
        // train number for this trip
        int t = -1;
        // is this a first trip of a train
        boolean c;
        int s, e;
        Trip(int s, int e) { this.s = s; this.e = e; }
    }
    
    static int lastTrain = 0;
    static int stopDelay = 0;
    
    static int[] findNumOfTrains(Trip[] a, Trip[] b, int stopDelay) {
        lastTrain = 0;
        TrainTimetable.stopDelay = stopDelay * 60;
        greedyTrip(a, b);
        greedyTrip(b, a);
        int ab = (int) IntStream.range(0, a.length).filter((i) -> a[i].c).count();
        int ba = (int) IntStream.range(0, b.length).filter((i) -> b[i].c).count();
        return new int[]{ab, ba};
    }
    
    private static void greedyTrip(Trip[] a, Trip[] b) {
        for (int i = 0; i < a.length; ++i) {
            if (a[i].t != -1)
                continue;
            greedyTrip(a, b, i);
            a[i].c = true;
            lastTrain++;
        }
    }

    private static void greedyTrip(Trip[] a, Trip[] b, int i) {
        a[i].c = false;
        a[i].t = lastTrain;
        for (int j = 0; j < b.length; ++j) {
            if (b[j].s < a[i].e + stopDelay)
                continue;
            if (b[j].t != -1) {
                if (!b[j].c) {
                    continue;
                }
                a[i].c = b[j].c;
                b[j].c = false;
                a[i].t = b[j].t;
                break;
            }
            greedyTrip(b, a, j);
            break;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/tmp/in"));
        int N = scanner.nextInt();
        for (int i = 1 ; i <= N; ++i) {
            int t = scanner.nextInt();
            int na = scanner.nextInt();
            int nb = scanner.nextInt();
            List<Trip> a = new ArrayList<>();
            for (int j = 0; j < na; ++j) {
                a.add(new Trip(LocalTime.parse(scanner.next()).toSecondOfDay(), 
                        LocalTime.parse(scanner.next()).toSecondOfDay()));
            }
            List<Trip> b = new ArrayList<>();
            for (int j = 0; j < nb; ++j) {
                b.add(new Trip(LocalTime.parse(scanner.next()).toSecondOfDay(), 
                        LocalTime.parse(scanner.next()).toSecondOfDay()));
            }
            Comparator<Trip> cmp = (t1, t2) -> t1.s - t2.s;
            a.sort(cmp);
            b.sort(cmp);
            int[] r = findNumOfTrains(a.toArray(new Trip[0]), b.toArray(new Trip[0]), t);
            System.out.format("Case #%d: %d %d\n", i, r[0], r[1]);
        }
        scanner.close();
    }

}
