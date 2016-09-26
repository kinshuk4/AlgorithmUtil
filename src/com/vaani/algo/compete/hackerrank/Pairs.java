package com.vaani.algo.compete.hackerrank;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Pairs {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); // use the Scanner class to read from stdin
        String firstLine = scan.nextLine();
        String secondLine = scan.nextLine();
        scan.close();

        Scanner firstLineScanner = new Scanner(firstLine).useDelimiter(" ");
        Integer quantity = firstLineScanner.nextInt();
        Integer delta = firstLineScanner.nextInt();

        Set<Integer> numbers = new TreeSet<>();
        Scanner secondLineScanner = new Scanner(secondLine).useDelimiter(" ");
        for(int count = 0; count < quantity; count++) {
            Integer number = secondLineScanner.nextInt();
            numbers.add(number);
        }

        int totalPairs = 0;
        for(Integer number : numbers) {
            if(numbers.contains(number + delta)) {
                totalPairs++;
            }
        }

        System.out.println(totalPairs);
    }
}
