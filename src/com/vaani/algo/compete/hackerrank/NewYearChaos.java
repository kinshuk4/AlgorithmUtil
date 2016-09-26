package com.vaani.algo.compete.hackerrank;

import java.util.Scanner;


/**
 * Created by andersonkmi on 7/23/16.
 */
public class NewYearChaos {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int testCases = in.nextInt();

        while(testCases > 0) {
            testCases--;

            int quantity = in.nextInt();
            int[] numbers = new int[quantity];
            for(int index = 0; index < numbers.length; index++) {
                numbers[index] = in.nextInt();
            }

            int[] frequency = new int[quantity + 1];
            boolean isChaos = false;
            boolean isFinished = false;
            int counter = 0;
            while(!isFinished) {
                isFinished = true;

                for(int index = 0; index < quantity - 1; index++) {
                    if(numbers[index] > numbers[index + 1]) {
                        frequency[numbers[index]]++;
                        if(frequency[numbers[index]] > 2) {
                            isChaos = true;
                            isFinished = true;
                            break;
                        }

                        counter++;
                        int temp = numbers[index];
                        numbers[index] = numbers[index + 1];
                        numbers[index + 1] = temp;
                        isFinished = false;
                    }
                }
            }

            if(isChaos) {
                System.out.println("Too chaotic");
            } else {
                System.out.println(counter);
            }
        }
    }
}
