/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 17/09/2015
 * 
 * Hacker rank
 * Week of code 17
 * Problem: Cross the River
 * Status: timeout
 * 
 * You're standing on a shore of a river. You'd like to reach the opposite shore.
 * The river can be described with two straight lines on the Cartesian plane, 
 * describing the shores. The shore you're standing on is Y=0 and another one is Y=H.
 * There are some rocks in the river. Each rock is described with its coordinates and 
 * the number of points you'll gain in case you step on this rock.
 * You can choose the starting position arbitrarily on the first shore. Then, you will 
 * make jumps. More precisely, you can jump to the position (X2,Y2) from the position 
 * (X1,Y1) in case |Y2−Y1|≤dH, |X2−X1|≤dW and Y2>Y1. You can jump only on the rocks 
 * and the shores.
 * What is the maximal sum of scores of all the used rocks you can obtain so that you 
 * cross the river, i.e. get to the opposite shore?
 * 
 * Input Format
 * 
 * The first line contains four single space-separated integer numbers, N,H,dH,dW,
 * denoting the number of rocks, the distance to the opposite shore, and the jump 
 * parameters dH and dW, respectively.
 * Each of the following N lines describe the rocks in the format Yi Xi Zi, where Yi 
 * and Xi are the coordinates of the rock and Zi is the number of points you'll gain in 
 * case you'll jump at this rock.
 * 
 * Output Format
 * 
 * Output the maximum possible score
 * 
 * Sample Input
 * 
5 10 3 3
2 2 7
2 3 5
5 5 -1
8 3 10
8 8 12
 *
 * Sample Output
 * 
18
 *
 */

package com.vaani.algo.compete.hackerrank.weekofcode._17;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CrossTheRiver {

    static class Rock {
        int x, y, z, l;
        long max = Long.MIN_VALUE;
        Rock(int y, int x, int z) {
            this.x = x; this.y = y; this.z = z;
        }
        @Override
        public String toString() {
            return String.format("(%d, %d, %d)", x, y, z);
        }
    }

    static Rock[] rocks;
    static List<Integer[]> levels;
    static int dH, dW, H;
    
    static List<Integer[]> readLevels() {
        List<Integer[]> levels = new ArrayList<>(100000);
        int y = rocks[0].y;
        int s = 0, e = 0;
        for (int i = 0; i < rocks.length; ++i) {
            if (y != rocks[i].y) {
                levels.add(new Integer[]{s, e});
                y = rocks[i].y;
                s = e = i;
            }
            rocks[i].l = levels.size();
            e++;
        }
        levels.add(new Integer[]{s, e});
        return levels;
    }
    
    static long findMaxPath(Rock r) {
        if (r.max != Long.MIN_VALUE) return r.max;
        if (r.y <= dH) r.max = r.z;
        if (r.l == 0) return r.max;
        int yi = r.l - 1;
        while (yi >= 0) {
            Integer[] level = levels.get(yi);
            if (r.y - rocks[level[0]].y > dH) break;
            int x = Arrays.<Rock>binarySearch(rocks, level[0], level[1], 
                    new Rock(0, r.x - dW, 0), (r1, r2) -> r1.x - r2.x);
            if (x < 0)
                x = -(x + 1);
            while (x < level[1]) {
                Rock c = rocks[x++];
                if (Math.abs(c.x - r.x) > dW)
                    break;
                long d = findMaxPath(c);
                r.max = Math.max(r.max, r.z + d);
            }
            yi--;
        }
        return r.max;
    }
    
    static long findMaxPath() {
        Arrays.sort(rocks, (r1, r2) -> r1.y == r2.y? r1.x - r2.x: r1.y - r2.y);
        levels = readLevels();
        int yi = levels.size() - 1;
        long max = Long.MIN_VALUE;
        while (yi >= 0) {
            Integer[] level = levels.get(yi);
            if (H - rocks[level[0]].y > dH)
                break;
            for (int i = level[0]; i < level[1]; ++i) {
                max = Math.max(max, findMaxPath(rocks[i]));
            }
            --yi;
        }
        return max;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null?
                new Scanner(System.in): new Scanner(CrossTheRiver.class.getResourceAsStream("CrossTheRiver1.in"));
        int N = scanner.nextInt();
        H = scanner.nextInt();
        dH = scanner.nextInt();
        dW = scanner.nextInt();
        rocks = new Rock[N];
        IntStream.range(0, N).forEach(i -> 
            rocks[i] = new Rock(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        System.out.println(findMaxPath());
        scanner.close();
    }
    
}
