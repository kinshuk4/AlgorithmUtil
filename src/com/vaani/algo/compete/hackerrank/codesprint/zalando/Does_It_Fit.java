/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 04/06/2016
 *
 * Hacker rank
 * Zalando CodeSprint
 * Problem: Does it fit
 * Status: accepted
 * 
 * Problem
 * 
 * At Zalando, we love automating manual work! Some day, we'll 
 * probably have only robots working in our warehouses and people 
 * managing them. :) For the time being, there are warehouse 
 * employees working hard to provide the best service.
 * Bromwich, one of the warehouse employees who is responsible for 
 * packing items into parcels, needs your help automating things a 
 * little. He wants to know if an item can be packed into a specific 
 * parcel. Because items come in different shapes and sizes (e.g., 
 * shoes, dresses, multiple accessories, etc.) and may need to be 
 * rotated to fit in the box, this problem is not so easy to solve. 
 * For the purposes of this contest, we've made it a little easier.
 * Let's say we have a list of geometric shapes that we'll call shapes. 
 * Each element in describes an element we're trying to ship. There 
 * are two types of shapes:
 * - Circle: C r describes a circle with radius r.
 * - Rectangle: R w h describes a rectangle with width w and height h.
 * Given the specifications for a 2D parcel with width W and height H, 
 * determine which items in will fit into this parcel.
 * Can you help Bromwich get his warehouse into ship-shape?
 * 
 * Input Format
 * 
 * The first line contains two space-separated integers denoting the 
 * respective values for W (the parcel width) and H (the parcel height).
 * The second line contains a single integer, N, denoting the number of shapes.
 * Each of the subsequent lines describes a shape in one of two ways:
 *  C r denotes a circle with radius r
 *  R w h denotes rectangle with width w and height h
 * 
 * Output Format
 * 
 * On a new line for each shape, print YES if the shape fits the parcel; 
 * otherwise, print NO. You should have a total of N lines of output.
 * 
 * Input
 * 
4 5
3
R 1 2
R 5 5
C 2
 * 
 * Output
 * 
YES
NO
YES
 * 
 */
package com.vaani.algo.compete.hackerrank.codesprint.zalando;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Does_It_Fit {

    static int W, H;
    
    static double shadow(double h, double w) {
        double ac2 = h * h + w * w; 
        double fc = Math.sqrt(ac2 - Math.pow(H, 2));
        double cab = Math.toDegrees(Math.atan(h / w));
        double fac = Math.toDegrees(Math.atan(fc / H));
        double baf = cab + fac;
        double bae = 90 - baf;
        double d = h * Math.cos(Math.toRadians(180 - 90 - bae)) + w * Math.cos(Math.toRadians(bae));
//        System.out.println(d);
        return d;
    }
    
    static boolean doesFit(int h, int w) {
        int max = IntStream.of(H, W).max().getAsInt();
        int min = IntStream.of(H, W).min().getAsInt();
        H = min;
        W = max;
        max = IntStream.of(h, w).max().getAsInt();
        min = IntStream.of(h, w).min().getAsInt();
        h = min;
        w = max;
        if (H >= h && W >= w) return true;
        if (H < h) return false;
        return shadow(h, w) < W;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.getProperty("local") == null? 
                System.in: Does_It_Fit.class.getResourceAsStream("Does_It_Fit.in"));
        W = scanner.nextInt();
        H = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String[] a = scanner.nextLine().split(" ");
            boolean fits = false;
            switch (a[0]) {
            case "C": 
                int h = Integer.parseInt(a[1]) * 2;
                fits = doesFit(h, h);
                break;
            case "R": 
                fits = doesFit(Integer.parseInt(a[1]), Integer.parseInt(a[2])); 
                break;
            }
            System.out.println(fits? "YES": "NO");
        }
        
        scanner.close();
    }

}
