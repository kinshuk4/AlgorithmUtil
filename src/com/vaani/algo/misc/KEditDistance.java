package com.vaani.algo.misc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KEditDistance {
    public Set<String> kDistance(String target, List<String> words, int k) {
        Set<String> res = new HashSet<String>();
        for (String candidate : words) {
            if (similar(target, candidate, k)) {
                res.add(candidate);
            }
        }
        return res;
    }

    private boolean similar(String target, String candidate, int k) {
        int first = target.length();
        int second = candidate.length();

        int[][] dist = new int[first + 1][second + 1];
        for (int r = 0; r <= first; ++r) {
            for (int c = 0; c <= second; ++c) {
                if (r == 0 && c == 0) {
                    dist[r][c] = 0;
                } else if (r == 0) {
                    dist[r][c] = c;
                } else if (c == 0) {
                    dist[r][c] = r;
                } else {
                    if (target.charAt(r - 1) == candidate.charAt(c - 1)) {
                        dist[r][c] = dist[r - 1][c - 1];
                    } else {
                        dist[r][c] = Math.min(dist[r - 1][c - 1], Math.min(dist[r][c - 1], dist[r - 1][c])) + 1;
                    }
                    if (r == c && dist[r][c] > k) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
