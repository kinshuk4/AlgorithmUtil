/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 24/08/2016
 * 
 * Hacker rank
 * 101 Hack Aug 2016
 * Problem: Designer PDF Viewer
 * Status: accepted
 * 
 * When you select a contiguous block of text in a PDF viewer, 
 * the selection is highlighted with a blue rectangle. In a new 
 * kind of PDF viewer, the selection of each word is independent 
 * of the other words; this means that each rectangular selection
 * area forms independently around each highlighted word. 
 * Consider a word consisting of lowercase English alphabetic 
 * letters, where each letter is 1mm wide. Given the height of each 
 * letter in millimeters (mm), find the total area that will be 
 * highlighted by blue rectangle in mm when the given word is selected 
 * in our new PDF viewer.
 * 
 * Input Format
 * 
 * The first line contains 26 space-separated integers describing the 
 * respective heights of each consecutive lowercase English letter 
 * (i.e., ha, hb, hc, .. hz).
 * The second line contains a single word, consisting of lowercase 
 * English alphabetic letters.
 * 
 * Output Format
 * 
 * Print a single integer denoting the area of highlighted rectangle 
 * when the given word is selected. The unit of measurement for this 
 * is square millimeters (mm), but you must only print the integer.
 * 
 * Sample Input
 * 
1 3 1 3 1 4 1 3 2 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5
abc
 *
 * Sample Output
 * 
9
 *
 *
 */

package com.vaani.algo.compete.hackerrank.hackathon.hack101._2016.aug;

import static java.util.Arrays.setAll;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Designer_PDF_Viewer {
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Designer_PDF_Viewer.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Designer_PDF_Viewer.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            int[] H = new int[26];
            setAll(H, i -> scanner.nextInt());
            String s = scanner.next();
            int h = s.chars().map(c -> H[c - 'a']).max().getAsInt();
            System.out.println(h * s.length());
        }
        scanner.close();
    }

}
