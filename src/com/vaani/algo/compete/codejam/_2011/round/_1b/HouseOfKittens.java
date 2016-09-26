/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Code jam
 * Round 1B 2011
 * Problem C. House of Kittens
 * Small input: correct
 * Large input: time limit
 * 
 * Problem
 * 
 * You have recently adopted some kittens, and now you want to make 
 * a house for them. On the outside, the house will be shaped like 
 * a convex polygon with N vertices. On the inside, it will be divided 
 * into several rooms by M interior walls connecting vertices along 
 * straight lines. No two walls will ever cross, but there might be 
 * multiple walls touching a single vertex.
 * So why is your house of kittens going to be so special? At every 
 * vertex, you are going to build a pillar entirely out of catnip! 
 * Kittens will be able to play with any pillar that touches the room 
 * they are in, giving them a true luxury home.
 * To make the house even more exciting, you want to use different 
 * flavors of catnip. A single pillar can only use one flavor, but 
 * different pillars can use different flavors. There is only one problem. 
 * If some room does not have access to all the catnip flavors in the 
 * house, then the kittens in that room will feel left out and be sad.
 * Your task is to choose what flavor of catnip to use for each vertex 
 * in such a way that (a) every flavor is accessible from every room, 
 * and (b) as many flavors as possible are used.
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T test 
 * cases follow.
 * Each test case consists of three lines. The first line gives N and M, 
 * the number of vertices and interior walls in your cat house. The second 
 * lines gives space-separated integers U1, U2, ..., UM describing where 
 * each interior wall begins. The third lines gives space-separated integers 
 * V1, V2, ..., VM describing where each interior wall ends.
 * More precisely, if the vertices of your cat house are labeled 1, 2, ..., N 
 * in clockwise order, then the interior walls are between vertices U1 and 
 * V1, U2 and V2, etc.
 * 
 * Output
 * 
 * For each test case, output two lines. The first should be "Case #x: C", 
 * where x is the case number, and C is the maximum number of catnip flavors 
 * that can be used. The second line should contain N space-separated 
 * integers: "y1 y2 ... yN", where yi is an integer between 1 and C indicating 
 * which catnip flavor you assigned to vertex i.
 * If there are multiple assignments with C flavors, you may output any of them.
 * 
 * Sample
 * 
 * Input
 * 2
 * 4 1
 * 2
 * 4
 * 8 3
 * 1 1 4
 * 3 7 7
 * 
 * Output
 * Case #1: 3
 * 1 2 1 3
 * Case #2: 3
 * 1 2 3 1 1 3 2 3
 * 
 */

package com.vaani.algo.compete.codejam._2011.round._1b;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HouseOfKittens {
    
    static class Vertex {
        public Vertex(int i2) {
            this.i = i2;
        }
        int i;
        int c = -1;
        Set<Integer> rooms = new HashSet<>();
        @Override
        public String toString() {
            return "v: " + i + " c: " + c;
        }
    }
    
    @SuppressWarnings("serial")
    static List<List<Vertex>> retrieveRooms(List<Vertex> vertices, List<Integer> walls, int[] U, int[] V) {
        if (walls.isEmpty())
            return new ArrayList<List<Vertex>>(){{add(vertices);}};
        int wall = walls.remove(0);
        int lo = U[wall];
        int hi = V[wall];
        List<Vertex> l = new ArrayList<>();
        List<Vertex> r = new ArrayList<>();
        for (Vertex v: vertices) {
            if (lo == v.i || hi == v.i) {
                l.add(v);
                r.add(v);
            } else if (lo < v.i && v.i < hi) {
                l.add(v);
            } else {
                r.add(v);
            }
        }
        List<Integer> wl = new ArrayList<>();
        List<Integer> wr = new ArrayList<>();
        for (int w: walls) {
            if (lo <= U[w] && U[w] <= hi && lo <= V[w] && V[w] <= hi) {
                wl.add(w);
            } else {
                wr.add(w);
            }
        }
        List<List<Vertex>> res = retrieveRooms(l, wl, U, V);
        res.addAll(retrieveRooms(r, wr, U, V));
        return res;
    }
    
    static int build(int n, int[] u, int[] v, List<Vertex> vertices) {
        IntStream.range(0, n).forEach((i) -> vertices.add(new Vertex(i)));
        List<List<Vertex>> rooms = retrieveRooms(vertices, u, v);
        int min = rooms.stream().min((r1, r2) -> r1.size() - r2.size()).get().size();
        int[] colors = new int[min];
        Arrays.setAll(colors, (i) -> i);
        if (!color(vertices, 0, colors, rooms))
            throw new RuntimeException();
        return colors.length;
    }

    private static boolean color(List<Vertex> vertices, int i, int[] colors,
            List<List<Vertex>> rooms) 
    {
        if (i == vertices.size())
            return true;
        Vertex v = vertices.get(i);
        List<List<Vertex>> adj = rooms.stream().filter((r) -> v.rooms.contains(r)).collect(Collectors.toList());
        nextColor: for (int c: byPriority(colors, adj)) {
            v.c = c;

            nextRoom: for (int r: v.rooms) {
                List<Vertex> room = rooms.get(r);
                
                Set<Integer> uniq = new HashSet<>();
                for (Vertex u: room) {
                    if (u.c == -1) {
                        continue nextRoom;
                    }
                    uniq.add(u.c);
                }
                if (uniq.size() < colors.length)
                    continue nextColor;
            }
            
            if (color(vertices, i + 1, colors, rooms))
                return true;
        }
        v.c = -1;
        return false;
    }

    private static int[] byPriority(int[] colors, List<List<Vertex>> rooms) {
        Set<Integer> uniq = new HashSet<>();
        IntStream.range(0, colors.length).forEach((i) -> uniq.add(colors[i]));
        for (List<Vertex> r: rooms) {
            for (Vertex v: r) {
                uniq.remove(v.c);
            }
        }
        int[] res = new int[colors.length];
        int i = 0;
        for (int c: uniq) 
            res[i++] = c;
        while (i < res.length)
            res[i++] = colors[i - uniq.size() - 1];
        return res;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/tmp/in"));
        int T = scanner.nextInt();
        for (int t = 1 ; t <= T; ++t) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] U = new int[m];
            Arrays.setAll(U, (i) -> scanner.nextInt() - 1);
            int[] V = new int[m];
            Arrays.setAll(V, (i) -> scanner.nextInt() - 1);
            List<Vertex> vertices = new ArrayList<>();
            int s = build(n, U, V, vertices);
            System.out.format("Case #%d: %s\n%s\n", t, s, vertices.stream().
                    map((v) -> "" + (v.c + 1)).
                        collect(Collectors.joining(" ")));
        }
        scanner.close();
    }

    private static List<List<Vertex>> retrieveRooms(List<Vertex> vertices, int[] U, int[] V) {
        if (U.length != V.length)
            throw new RuntimeException();
        List<Integer> walls = new ArrayList<>();
        IntStream.range(0, U.length).forEach((i) -> walls.add(i));
        List<List<Vertex>> rooms = retrieveRooms(vertices, walls, U, V);
        IntStream.range(0, rooms.size()).forEach((i) -> rooms.get(i).forEach((v) -> v.rooms.add(i)));
        return rooms;
    }

}
