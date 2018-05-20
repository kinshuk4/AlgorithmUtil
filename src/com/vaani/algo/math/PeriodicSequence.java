package com.vaani.algo.math;

import java.util.*;
import java.util.stream.Collectors;

public class PeriodicSequence {
    static int periodicSequence(int s0, int a, int b, int m) {
        Map<Integer, Boolean> sequenceRepeat = new HashMap<>();
        int prevNum = 0;
        int result = func(s0, a, b, m);
        sequenceRepeat.put(result, false);
        int currNum = sequenceRepeat.size();
        while (currNum != prevNum) {
            prevNum = currNum;
            result = func(result, a, b, m);
            if (sequenceRepeat.containsKey(result)) {
                sequenceRepeat.put(result, true);
            } else {
                sequenceRepeat.put(result, false);
            }
            currNum = sequenceRepeat.size();
        }
        currNum = (int) sequenceRepeat.entrySet().stream().filter(x -> x.getValue()).count();
        while (currNum != prevNum) {
            prevNum = currNum;
            result = func(result, a, b, m);
            if (sequenceRepeat.containsKey(result)) {
                sequenceRepeat.put(result, true);
            } else {
                sequenceRepeat.put(result, false);
            }
            currNum = (int) sequenceRepeat.entrySet().stream().filter(x -> x.getValue()).count();
        }

        return currNum;

    }


    static int func(int s0, int a, int b, int m) {
        return (a * s0 + b) % m;
    }

    public static void main(String[] args) {
        System.out.println(periodicSequence(11, 2, 6, 12));
    }
}
