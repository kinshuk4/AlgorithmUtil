package com.vaani.algo.array.matrix;

/*https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Fmatrix%2FMatrix.java
 * */

public class MatrixMultiplication {
    static int[][] a = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    static int[][] b = new int[][]{{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
    static int[][] c = new int[a.length][b.length];

    public static void main(String[] args) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                for (int k = 0; k < b[j].length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        for (int temp[] : c) {
            for (int col : temp) {
                System.out.print(col + "\t");
            }
            System.out.println();
        }
    }
}
