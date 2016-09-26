package com.vaani.algo.compete.cc150.chapter9;

/**
 * Implement the "paint fill" function that one might see on many image editing
 * programs. That is, given a screen (represented by a two-dimensional array of
 * colors), a point, and a new color, fill in the surrounding area until the
 * color changes from the original color.
 */
public class Question7 {

    public void fill(char[][] screen, int x, int y, char color) {
        // write the implementation here
        if (screen == null || screen.length == 0 || screen[0].length == 0) {
            return;
        }
        char originalColor = screen[x][y];
        boolean[][] visit = new boolean[screen.length][screen[0].length];
        fill(screen, visit, x, y, color, originalColor);
    }

    private void fill(char[][] screen, boolean[][] visit, int x, int y, char color, char originalColor) {
        if (visit[x][y] || screen[x][y] != originalColor) {
            return;
        }
        visit[x][y] = true;
        screen[x][y] = color;
        if (y + 1 < screen[0].length) {
            fill(screen, visit, x, y + 1, color, originalColor);
        }
        if (y - 1 >= 0) {
            fill(screen, visit, x, y - 1, color, originalColor);
        }
        if (x + 1 < screen.length) {
            fill(screen, visit, x + 1, y, color, originalColor);
        }
        if (x - 1 >= 0) {
            fill(screen, visit, x - 1, y, color, originalColor);
        }
    }

}
