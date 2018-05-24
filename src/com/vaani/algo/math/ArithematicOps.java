package com.vaani.algo.math;

public class ArithematicOps {
    public static int getSumProperDivisors(int number) {
        int n = number;
        int sum = 1;

        int numOfDiv = 0;
        int loop = (int) Math.sqrt(number);

        for (int i = 2; i <= loop; i++) {
            if (n % i == 0) {
                numOfDiv += 2;
                sum += i + n / i;
            }
        }

        if (loop * loop == n) {
            numOfDiv--;
            sum -= loop;
        }


        return sum;
    }


}
