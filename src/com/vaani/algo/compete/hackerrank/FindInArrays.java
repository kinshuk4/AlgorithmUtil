package com.vaani.algo.compete.hackerrank;

import java.util.*;

/**
 * Created by andersonkmi on 7/25/2016.
 */
public class FindInArrays {
    public List<Integer> find(int[][] arrays) {
        Map<Integer, Integer> occurrences = new HashMap<>();
        for(int i = 0; i < arrays.length; i++) {
            List<Integer> countedNumbers = new ArrayList<>();
            for(int j = 0; j < arrays[i].length; j++) {
                Integer currentNumber = arrays[i][j];
                if(!countedNumbers.contains(currentNumber)) {
                    Integer occurrence = 0;
                    if(occurrences.containsKey(currentNumber)) {
                        occurrence = occurrences.get(currentNumber);
                    }
                    occurrence++;
                    occurrences.put(currentNumber, occurrence);
                    countedNumbers.add(currentNumber);
                }
            }
        }

        List<Integer> results = new ArrayList<>();
        Set<Integer> keys = occurrences.keySet();
        for(Integer key : keys) {
            Integer counter = occurrences.get(key);
            if(counter == 2) {
                results.add(key);
            }
        }
        return results;
    }
}
