package com.vaani.algo.compete.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by andersonkmi on 8/7/16.
 */
public class MarkToys {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int toys = sc.nextInt();
        int money = sc.nextInt();
        int[] prices = new int[toys];

        for(int index = 0; index < toys; index++) {
            prices[index] = sc.nextInt();
        }

        Arrays.sort(prices);

        int toyQuantity = 0;
        int position = 0;

        while(position < toys) {

            if(money >= prices[position]) {
                toyQuantity++;
                money -= prices[position];
            } else {
                break;
            }
            position++;
        }

        System.out.print(toyQuantity);
    }
}
