package com.vaani.algo.string;

import java.util.ArrayList;
import java.util.List;

public class AllWordWithinKDistance {

    public AllWordWithinKDistance() {
    }

    public List<String> findAllWords(String target, List<String> pool, int k) {
        List<String> res = new ArrayList<String>();
        for (String candidate : pool) {
            if (similar(target, candidate, k)) {
                res.add(candidate);
            }
        }
        return res;
    }

    private boolean similar(String target, String candidate, int k) {
        int[][] dist = new int[target.length() + 1][candidate.length() + 1];
        for (int r = 0; r <= target.length(); ++r) {
            for (int c = 0; c <= candidate.length(); ++c) {
                if (r == 0 || c == 0) {
                    dist[r][c] = r != 0 ? r : c;
                } else {
                    int plus = target.charAt(r - 1) == candidate.charAt(c - 1) ? 0 : 1;
                    dist[r][c] = Math.min(dist[r - 1][c - 1] + plus,
                            Math.min(dist[r - 1][c], dist[r][c - 1]) + 1);
                }
            }
        }
        return dist[target.length()][candidate.length()] <= k;
    }

}
