package com.vaani.algo.misc;

/**
 * Write a Java program that prints the numbers from 1 to 50. But for multiples of three print "Fizz" instead of the number and for the multiples of five print "Buzz".
 * For numbers which are multiples of both three and five print "FizzBuzz"
 * <p>
 * Created by Xiaomeng on 10/8/2014.
 */
public class FizzBuzz {
    public static void main(String[] args) {
        FizzBuzz test = new FizzBuzz();
        test.fizzBuzz();
    }

    public void fizzBuzz() {
        for (int i = 1; i < 50; i++) {
            if (i % 3 == 0 && i % 5 == 0)
                System.out.println("FizzBuzz");
            else if (i % 3 == 0)
                System.out.println("Fizz");
            else if (i % 5 == 0)
                System.out.println("Buzz");
            else
                System.out.println(i);
        }
    }
}
