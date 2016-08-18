package com.vaani.algo.paradigm.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * A thief wants to steal money from the whole street, but he cannot steal two
 * consecutive houses as the house owners will immediately notify their neighbors.
 * Given the amount of money in each house, how can the thief maximize his swag?
 * <p>
 * Time complexity O(N), space Complexity O(N).
 */
public class BurglarizingHouse {

    public List<Integer> bestValue(int[] houses) {
        List<Integer> index = new ArrayList<Integer>();
        int[] values = new int[houses.length];

        for (int i = 0; i < houses.length; ++i) {
            int robCur = houses[i] + (i >= 2 ? values[i - 2] : 0);
            int noRobCur = i >= 1 ? values[i - 1] : 0;
            if (robCur >= noRobCur) {
                index.add(i);
                values[i] = robCur;
            } else {
                values[i] = noRobCur;
            }
        }

        for (int i = index.size() - 1; i > 0; --i) {
            if (index.get(i - 1) == index.get(i) - 1) {
                index.remove(i - 1);
                --i;
            }
        }

        return index;
    }

}
