package com.vaani.algo.compete.codeeval;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NumberOfOnes {

    public static void main(String... args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(args[0]));
        while (sc.hasNextLine()) {
            int number = Integer.valueOf(sc.nextLine().trim());
            int oneCount = 0;
            while (number > 0){
                oneCount++;
                number &= (number - 1);
            }
            System.out.println(oneCount);
        }
    }

}
