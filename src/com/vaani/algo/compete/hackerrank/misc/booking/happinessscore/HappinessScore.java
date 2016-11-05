

import org.apache.commons.lang3.NotImplementedException;

import java.io.*;
import java.util.*;

public class HappinessScore {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int numTotal = scan.nextInt();
        int[] score = new int[numTotal];
        for (int i = 0; i < numTotal; i++) {
           score[i] = scan.nextInt();
            }
        System.out.println(findPrime(score));
     
        }
    public static int findPrime(int[] score) {
        throw new NotImplementedException("Not implemented yet.");
}
        
}
