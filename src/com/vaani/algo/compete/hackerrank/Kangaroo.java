package com.vaani.algo.compete.hackerrank;

import java.util.Scanner;

/**
 * Created by andersonkmi on 8/1/2016.
 */
public class Kangaroo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x1 = in.nextInt();
        int v1 = in.nextInt();
        int x2 = in.nextInt();
        int v2 = in.nextInt();

        if(v2 > v1) {
            System.out.println("NO");
        } else {
            boolean flag = false;
            while(x1 <= x2) {
                if(x1 == x2) {
                    flag = true;
                    break;
                } else {
                    x1 += v1;
                    x2 += v2;
                }
            }

            if(flag) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        }
    }
}
