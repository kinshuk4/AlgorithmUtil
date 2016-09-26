package com.vaani.algo.compete.codeeval;

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by mykola on 05.05.15.
 */
public class StackImplementation {

    public static void main(String... args) throws Exception {
        Scanner sc = new Scanner(new File(args[0]));
        while (sc.hasNextLine()) {
            Deque<Integer> deque = new LinkedList<>();
            Scanner lineScanner = new Scanner(sc.nextLine());
            while(lineScanner.hasNextInt()){
                deque.push(lineScanner.nextInt());
            }
            int counter = 0;
            while (!deque.isEmpty()){
                int value = deque.pollFirst();
                if(counter % 2 == 0){
                    System.out.print(value + " ");
                }
                counter ++;
            }
            System.out.println();
        }
    }
}
