package com.vaani.algo.compete.cc150.chapter9;

/**
 * Imagine a robot sitting on the upper left corner of an X by Y grid. The robot
 * can only move in two directions: right and down. How many possible paths are
 * there for the robot to go from (0, 0) to (X, Y)?
 * <p>
 * FOLLOW UP Imagine certain spots are "off limits", such that the robot cannot
 * step on them.
 */
// O(nm) space, O(nm) time
public class Question2 {

    // available at leetcode.com online judge, question Unique Path and Unique
    // Path II

    public class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            // Start typing your Java solution below
            // DO NOT write main() function
            if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
                return 0;
            }
            int[][] paths = new int[obstacleGrid.length][obstacleGrid[0].length];
            paths[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;
            for (int i = 0; i < paths.length; ++i) {
                for (int j = 0; j < paths[i].length; ++j) {
                    if (obstacleGrid[i][j] == 1) {
                        paths[i][j] = 0;
                    } else {
                        if (i != 0) {
                            paths[i][j] += paths[i - 1][j];
                        }
                        if (j != 0) {
                            paths[i][j] += paths[i][j - 1];
                        }
                    }
                }
            }
            return paths[paths.length - 1][paths[0].length - 1];
        }
    }

}

