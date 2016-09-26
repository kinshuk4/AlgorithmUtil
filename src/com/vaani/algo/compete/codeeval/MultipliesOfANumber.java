package com.vaani.algo.compete.codeeval;

import java.io.File;
import java.util.Scanner;

public class MultipliesOfANumber {

    public static void main(String... args) throws Exception {
        Scanner sc = new Scanner(new File(args[0]));
        while(sc.hasNextLine()){
            String[] numbers = sc.nextLine().split(",");
            int x = Integer.valueOf(numbers[0]);
            int n = Integer.valueOf(numbers[1]);
            int result = n;
            while (result < x){
                result += n;
            }
            System.out.println(result);
        }
    }

}
