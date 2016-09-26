package com.vaani.algo.compete.hackerrank;

/**
 * Created by andersonkmi on 7/31/2016.
 */
public class Mastermind {
    public static void main(String[] args) {
        Mastermind mastermind = new Mastermind();
        int[] results = mastermind.verifyGuess("RGYR", "RGBY");
        System.out.println(String.format("%d %d", results[0], results[1]));
    }


    public int[] verifyGuess(String guess, String solution) {
        int[] result = new int[2];
        int[] frequencies = new int[4];

        if(guess.length() != solution.length()) {
            return result;
        }

        for(int index = 0; index < guess.length(); index++) {
            if(guess.charAt(index) == solution.charAt(index)) {
                result[0]++;
            } else {
                frequencies[colorIndex(solution.charAt(index))]++;
            }
        }

        for(int index = 0; index < guess.length(); index++) {
            int colorIndex = colorIndex(guess.charAt(index));
            if(frequencies[colorIndex] > 0 && guess.charAt(index) != solution.charAt(index)) {
                result[1]++;
                frequencies[colorIndex]--;
            }
        }
        return result;
    }

    private int colorIndex(char color) {
        if(color == 'R') {
            return 0;
        } else if(color == 'Y') {
            return 1;
        } else if(color == 'G') {
            return 2;
        } else if(color == 'B') {
            return 3;
        }
        return -1;
    }
}
