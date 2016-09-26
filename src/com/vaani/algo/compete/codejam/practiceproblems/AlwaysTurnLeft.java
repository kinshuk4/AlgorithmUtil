/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 10/04/2014
 * 
 * Code jam
 * Practice Problems
 * Problem B. Always Turn Left
 * Small input: correct
 * Large input: StackOverflowError
 * 
 * Problem
 * 
 * You find yourself standing outside of a perfect maze. A maze is defined as 
 * "perfect" if it meets the following conditions:
 * - It is a rectangular grid of rooms, R rows by C columns.
 * - There are exactly two openings on the outside of the maze: 
 * the entrance and the exit. The entrance is always on the north wall, 
 * while the exit could be on any wall.
 * - There is exactly one path between any two rooms in the maze (that is, 
 * exactly one path that does not involve backtracking).
 * You decide to solve the perfect maze using the "always turn left" algorithm, 
 * which states that you take the leftmost fork at every opportunity. If you hit 
 * a dead end, you turn right twice (180 degrees clockwise) and continue. (If you 
 * were to stick out your left arm and touch the wall while following this algorithm, 
 * you'd solve the maze without ever breaking contact with the wall.) Once you finish 
 * the maze, you decide to go the extra step and solve it again (still always turning 
 * left), but starting at the exit and finishing at the entrance.
 * The path you take through the maze can be described with three characters: 'W' 
 * means to walk forward into the next room, 'L' means to turn left (or 
 * counterclockwise) 90 degrees, and 'R' means to turn right (or clockwise) 90 degrees.
 * You begin outside the maze, immediately adjacent to the entrance, facing the maze. 
 * You finish when you have stepped outside the maze through the exit. For example, if 
 * the entrance is on the north and the exit is on the west, your path through the 
 * following maze would be WRWWLWWLWWLWLWRRWRWWWRWWRWLW:
 *  __ 
 * |  __|
 * | |_ |
 * |__  |
 *   _| |
 * |____|
 * 
 * If the entrance and exit were reversed such that you began outside the west wall 
 * and finished out the north wall, your path would be WWRRWLWLWWLWWLWWRWWRWWLW. Given 
 * your two paths through the maze (entrance to exit and exit to entrance), your code 
 * should return a description of the maze.
 * 
 * Input
 * 
 * The first line of input gives the number of cases, N. N test cases follow. Each case 
 * is a line formatted as
 * 
 * entrance_to_exit exit_to_entrance
 * 
 * All paths will be at least two characters long, consist only of the characters 'W', 
 * 'L', and 'R', and begin and end with 'W'.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x:" by itself. The next R 
 * lines give a description of the R by C maze. There should be C characters in each 
 * line, representing which directions it is possible to walk from that room. Refer to
 * the following legend:
 * 
 * Character       Can walk north?     Can walk south?     Can walk west?      Can walk east?  
 * 1                           Yes                  No                 No                  No
 * 2                            No                 Yes                 No                  No
 * 3                           Yes                 Yes                 No                  No
 * 4                            No                  No                Yes                  No
 * 5                           Yes                  No                Yes                  No
 * 6                            No                 Yes                Yes                  No
 * 7                           Yes                 Yes                Yes                  No
 * 8                            No                  No                 No                 Yes
 * 9                           Yes                  No                 No                 Yes
 * a                            No                 Yes                 No                 Yes
 * b                           Yes                 Yes                 No                 Yes
 * c                            No                  No                Yes                 Yes
 * d                           Yes                  No                Yes                 Yes
 * e                            No                 Yes                Yes                 Yes
 * f                           Yes                 Yes                Yes                 Yes
 * 
 * For each test case you should output one line containing "Case #T: S" (quotes for clarity)
 * where T is the number of the test case and S is the number of non-empty increasing 
 * subsequences mod 1 000 000 007. 
 * 
 * Sample
 * 
 * Input
 * 
 * 2
 * WRWWLWWLWWLWLWRRWRWWWRWWRWLW WWRRWLWLWWLWWLWWRWWRWWLW
 * WW WW
 * 
 * Output
 * 
 * Case #1:
 * ac5
 * 386
 * 9c7
 * e43
 * 9c5
 * Case #2:
 * 3
 * 
 */

package com.vaani.algo.compete.codejam.practiceproblems;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


public class AlwaysTurnLeft {

    static enum Direction {
        NORTH, WEST, SOUTH, EAST;
    }

    static class Cell {
        Point p = new Point();
        int m;
        @Override
        public String toString() {
            return p.toString() + " - " + m;
        }
    }

    private static Direction inverse(Direction d) {
        switch (d) {
        case NORTH: return Direction.SOUTH;
        case SOUTH: return Direction.NORTH;
        case EAST: return Direction.WEST;
        case WEST: return Direction.EAST;
        default: throw new RuntimeException();
        }
    }

    private static void addPass(Cell cell, Direction d) {
        switch (d) {
        case NORTH: cell.m |= 1; break;
        case SOUTH: cell.m |= 2; break;
        case WEST: cell.m |= 4; break;
        case EAST: cell.m |= 8; break;
        default: throw new RuntimeException();
        }
    }
    
    private static Entry<Cell, Direction> walk(Map<Point, Cell> map, Cell cell, Direction from, String path) {
        map.put(cell.p, cell);
        addPass(cell, from);
        Direction to = null;
        Point toPoint = null;
        switch (from) {
        case NORTH:
            if (path.startsWith("RW")) {
                to = Direction.WEST;
                toPoint = new Point(cell.p.x - 1, cell.p.y);
                path = path.substring(2);
            } else if (path.startsWith("RRW")) {
                to = from;
                toPoint = new Point(cell.p.x, cell.p.y + 1);
                path = path.substring(3);
            } else if (path.startsWith("W")) {
                to = Direction.SOUTH;
                toPoint = new Point(cell.p.x, cell.p.y - 1);
                path = path.substring(1);
            } else if (path.startsWith("LW")) {
                to = Direction.EAST;
                toPoint = new Point(cell.p.x + 1, cell.p.y);
                path = path.substring(2);
            } else throw new RuntimeException();
            break;
        case EAST:
            if (path.startsWith("RW")) {
                to = Direction.NORTH;
                toPoint = new Point(cell.p.x, cell.p.y + 1);
                path = path.substring(2);
            } else if (path.startsWith("RRW")) {
                to = from;
                toPoint = new Point(cell.p.x + 1, cell.p.y);
                path = path.substring(3);
            } else if (path.startsWith("W")) {
                to = Direction.WEST;
                toPoint = new Point(cell.p.x - 1, cell.p.y);
                path = path.substring(1);
            } else if (path.startsWith("LW")) {
                to = Direction.SOUTH;
                toPoint = new Point(cell.p.x, cell.p.y - 1);
                path = path.substring(2);
            } else throw new RuntimeException();
            break;
        case SOUTH:
            if (path.startsWith("RW")) {
                to = Direction.EAST;
                toPoint = new Point(cell.p.x + 1, cell.p.y);
                path = path.substring(2);
            } else if (path.startsWith("RRW")) {
                to = from;
                toPoint = new Point(cell.p.x, cell.p.y - 1);
                path = path.substring(3);
            } else if (path.startsWith("W")) {
                to = Direction.NORTH;
                toPoint = new Point(cell.p.x, cell.p.y + 1);
                path = path.substring(1);
            } else if (path.startsWith("LW")) {
                to = Direction.WEST;
                toPoint = new Point(cell.p.x - 1, cell.p.y);
                path = path.substring(2);
            } else throw new RuntimeException();
            break;
        case WEST:
            if (path.startsWith("RW")) {
                to = Direction.SOUTH;
                toPoint = new Point(cell.p.x, cell.p.y - 1);
                path = path.substring(2);
            } else if (path.startsWith("RRW")) {
                to = from;
                toPoint = new Point(cell.p.x - 1, cell.p.y);
                path = path.substring(3);
            } else if (path.startsWith("W")) {
                to = Direction.EAST;
                toPoint = new Point(cell.p.x + 1, cell.p.y);
                path = path.substring(1);
            } else if (path.startsWith("LW")) {
                to = Direction.NORTH;
                toPoint = new Point(cell.p.x, cell.p.y + 1);
                path = path.substring(2);
            } else throw new RuntimeException();
            break;
        default:
            throw new RuntimeException();
        }
        if (path.isEmpty())
            return new SimpleEntry<>(cell, to);
        Cell toCell = map.get(toPoint);
        if (toCell == null) {
            toCell = new Cell();
            toCell.p = toPoint;
        }
        return walk(map, toCell, inverse(to), path);
    }

    private static char[][] describeMaze(String forward, String backward) {
        Cell cell = new Cell();
        Map<Point, Cell> map = new HashMap<>();
        Entry<Cell, Direction> exit = walk(map, cell, Direction.NORTH, forward.substring(1));
        walk(map, exit.getKey(), exit.getValue(), backward.substring(1));
        Comparator<Point> cmp = (p1, p2) -> p1.x <= p2.x && p1.y <= p2.y ? -1: 1;
        Point min = map.keySet().stream().min(cmp).get();
        Point max = map.keySet().stream().max(cmp).get();
        char[][] rooms = new char[max.y - min.y + 1][];
        Arrays.setAll(rooms, (i) -> new char[max.x - min.x + 1]);
        for (int y = max.y, r = 0; y >= min.y; y--, r++) {
            for (int x = min.x, c = 0; x <= max.x; x++, c++) {
                rooms[r][c] = Integer.toHexString(map.get(new Point(x, y)).m).charAt(0);
            }
        }
        return rooms;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //      String forward = "WRWWLWWLWWLWLWRRWRWWWRWWRWLW";
        //      String backward = "WWRRWLWLWWLWWLWWRWWRWWLW";
        //      String forward = "WRWLWWLWLWRRWW";
        //      String backward = "WLWRWWRWLW";
        //      String forward = "WW";
        //      String backward = "WW";
        Scanner scanner = new Scanner(new File("/tmp/in"));
        int N = scanner.nextInt();
        scanner.nextLine();
        for (int n = 1 ; n <= N; ++n) {
            String[] paths = scanner.nextLine().split(" ");
            char[][] rooms = describeMaze(paths[0], paths[1]);
            System.out.println("Case #" + n + ":");
            for (int i = 0; i < rooms.length; ++i) {
                for (int j = 0; j < rooms[i].length; ++j)
                    System.out.print(rooms[i][j]);
                System.out.println();
            }
        }
        scanner.close();
    }
}



