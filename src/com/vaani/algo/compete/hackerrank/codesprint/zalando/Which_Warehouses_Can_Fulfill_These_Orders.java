/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 10/06/2016
 *
 * Hacker rank
 * Zalando CodeSprint
 * Problem: Which Warehouses Can Fulfill These Orders
 * Status: accepted
 * 
 * Problem
 * 
 * At Zalando, we have several warehouses that fulfill customer 
 * orders. Each warehouse has a different set of items in their 
 * available inventory.
 * There are W warehouses and P types of products in inventory. We 
 * need to determine the minimum number of warehouses that it will 
 * take to fulfill each of B customer orders. Each order specifies 
 * how many products of a specific type a customer wants to buy.
 * To optimize the cost of delivering orders, we want to fulfill 
 * each order using a minimum number of warehouses. Given the base 
 * stock levels for each warehouse and a list of orders, print the 
 * minimum number of warehouses it will take to fulfill each of the 
 * orders; if it's not possible to fulfill an order, print .
 * 
 * Input Format
 * 
 * The first line contains three space-separated integers, W, B, 
 * and P, respectively.
 * Each line i of the W subsequent lines contains P space-separated 
 * integers describing the products that each warehouse keeps in inventory. 
 * The j number in line i specifies how many products of type j are 
 * available in warehouse number i.
 * Each line k of the subsequent lines B contains P space-separated 
 * integers describing an order. The j number in line k specifies how 
 * many products of type j the customer ordered in order number k.
 * 
 * Output Format
 * 
 * For each order, print the minimum number of warehouses needed to 
 * fulfill the order on a new line; if the order cannot be fulfilled, 
 * print -1.
 * 
 * Input
 * 
2 3 2
1 0
0 1
1 1
2 0
0 1
 * 
 * Output
 * 
2
-1
1
 * 
 */
package com.vaani.algo.compete.hackerrank.codesprint.zalando;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Which_Warehouses_Can_Fulfill_These_Orders {

    static int[][] W;
    
    static int solve(int[] o, int w) {
        if (w == W.length)
            return Arrays.stream(o).allMatch(i -> i <= 0)? 0: Integer.MAX_VALUE;
        int min = solve(o, w + 1);
        int[] no = new int[o.length];
        for (int i = 0; i < W[w].length; i++)
            no[i] = o[i] - W[w][i];
        int r = solve(no, w + 1);
        r = r == Integer.MAX_VALUE? r: r + 1;
        return Math.min(min, r);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.getProperty("local") == null? 
                System.in: Which_Warehouses_Can_Fulfill_These_Orders.class.getResourceAsStream("Which_Warehouses_Can_Fulfill_These_Orders.in"));
        W = new int[scanner.nextInt()][];
        int B = scanner.nextInt();
        int P = scanner.nextInt();
        for (int i = 0; i < W.length; i++) {
            W[i] = new int[P];
            Arrays.setAll(W[i], j -> scanner.nextInt());
        }
        for (int i = 0; i < B; i++) {
            int[] o = new int[P];
            Arrays.setAll(o, j -> scanner.nextInt());
            int r = solve(o, 0);
            System.out.println(r == Integer.MAX_VALUE? -1: r);
        }
        scanner.close();
    }

}
