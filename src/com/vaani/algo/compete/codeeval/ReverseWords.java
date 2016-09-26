package com.vaani.algo.compete.codeeval;

import java.io.File;
import java.util.Scanner;

public class ReverseWords {

    public static void main(String... args) throws Exception {
        Scanner sc = new Scanner(new File(args[0]));
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            String[] words = line.split(" ");
            for(int i = words.length - 1; i >=0; i--){
                System.out.print(words[i]);
                if(i != 0){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
