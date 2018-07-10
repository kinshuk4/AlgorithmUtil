package com.vaani.algo.ds.algos.graph;

/**
 * Once upon a time, in a kingdom far, far away, there lived a king Byteasar II. There was nothing special neither about him, nor about his kingdom. As a mediocre ruler, he did nothing about his kingdom and preferred hunting and feasting over doing anything about his kingdom prosperity.
 *
 * Luckily, his adviser, wise magician Bitlin, was working for the kingdom welfare daily and nightly. However, since there was no one to advise him, he completely forgot about one important thing: the roads. Over the years most of the two-way roads built by Byteasar II predecessors were forgotten and no longer traversable. Only a few roads can still be used.
 *
 * Bitlin wanted each pair of cities to be connected, but couldn't find a way to figure out which roads are missing. Desperate, he turned to his magic crystal, seeking help. The crystal showed him a programmer from the distant future: you! Now you're the only one who can save the kingdom. Given the existing roads and the number of cities in the kingdom, you should use the most modern technologies and find out what roads should be built again to make each pair of cities connected. Since the magic crystal is quite old and meticulous, it will only transfer the information that is sorted properly.
 *
 * The roads to be built should be returned in an array sorted lexicographically, with each road stored as [cityi, cityj], where cityi < cityj.
 *
 * Example
 *
 * For cities = 4 and roads = [[0, 1], [1, 2], [2, 0]],
 * the output should be
 * roadsBuilding(cities, roads) = [[0, 3], [1, 3], [2, 3]].
 *
 * See the image below: the existing roads are colored black, and the ones to be built are colored red.
 */
public class RoadBuilding {
    static int[][] roadsBuilding(int cities, int[][] roads) {
        boolean[][] adj = new boolean[cities][cities];
        int maxRoads = cities * (cities - 1) / 2;
        int existingRoads = 0;
        for (int i = 0; i < roads.length; i++) {
            int[] currRoad = roads[i];
            int k = currRoad[0];
            int l = currRoad[1];
            adj[k][l] = true;
            adj[l][k] = true;
            existingRoads++;
        }



        System.out.println(maxRoads - existingRoads);

        int[][] result = new int[maxRoads - existingRoads][2];
        int k = 0;
        for (int i = 0; i < cities; i++) {
            for (int j = i; j < cities; j++) {
                if (i != j && !adj[i][j]) {
                    result[k][0] = i;
                    result[k++][1] = j;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] roads = {
                {0, 1},
                {1, 2},
                {2, 0}
        };
        System.out.println(roadsBuilding(4, roads));
    }
}
