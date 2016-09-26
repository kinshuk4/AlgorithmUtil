package com.vaani.algo.compete.hackerrank;

import java.util.Scanner;

/**
 * Created by andersonkmi on 8/7/2016.
 */
public class AngryProfessor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        //System.out.println("Test cases: " + testCases);

        if(testCases > 0) {
            // Process test cases
            while(testCases-- > 0) {
                int studentsQuantity = sc.nextInt();
                int threshold = sc.nextInt();
                int onTimeStudents = 0;
                //System.out.println("Quantity: " + studentsQuantity);
                //System.out.println("Threshold: " + threshold);

                while(studentsQuantity-- > 0) {
                    int studentArrivalTime = sc.nextInt();
                    //System.out.println("Student arrival time: " + studentArrivalTime);
                    if(studentArrivalTime <= 0) {
                        onTimeStudents++;
                    }
                }

                if(onTimeStudents < threshold) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }
}
