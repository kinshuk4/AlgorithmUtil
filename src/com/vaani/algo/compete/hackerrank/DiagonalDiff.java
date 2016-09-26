package com.vaani.algo.compete.hackerrank;

import java.util.Scanner;
import java.util.StringTokenizer;

public class DiagonalDiff {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); // use the Scanner class to read from stdin
        String matrixDimension = scan.nextLine();

        Integer matrixDim = Integer.parseInt(matrixDimension);
        Integer[][] matrix = new Integer[matrixDim][matrixDim];

        for(int row = 0; row < matrixDim; row++) {
            String line = scan.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line, " ");
            for(int column = 0; column < matrixDim; column++) {
                Integer value = Integer.parseInt(tokenizer.nextToken());
                matrix[row][column] = value;
            }
        }

        Integer diagonalSum = 0;
        Integer oppositeDiagonalSum = 0;
        for(int index = 0; index < matrixDim; index++) {
            diagonalSum += matrix[index][index];
            oppositeDiagonalSum += matrix[index][matrixDim - (index + 1)];
        }

        System.out.println(Math.abs(diagonalSum - oppositeDiagonalSum));
    }
}
