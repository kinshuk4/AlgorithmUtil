package com.vaani.algo.compete.codility.tomtom;


/*
There is a company that has a very creative way of managing its accounts. Every time they want to write down a number, they shuffle its digits in the following way: they alternatively write one digit from the front of the number and one digit from the back, then the second digit from the front and the second from the back, and so on until the length of the shuffled number is the same as that of the original.
Write a function
class Solution { public int solution(int A); }
that, given a positive integer A, returns its shuffled representation.
For example, given A = 123456 the function should return 162534.
Given A = 130 the function should return 103.
Assume that:
A is an integer within the range [0..100,000,000].
In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.

https://github.com/pcingola/Code4Fun/blob/a0a8f51d57119ccaab97badead15c970bdd72c8f/src/main/java/codility/creativeAccounting/Solution.java
 */
public class ShuffleNumber {

    public static void main(String[] args) {
        ShuffleNumber s = new ShuffleNumber();
        System.out.println(s.solution(0));
        System.out.println(s.solution(1));
        System.out.println(s.solution(130));
        System.out.println(s.solution(-0));
        System.out.println(s.solution(-1));
        System.out.println(s.solution(-130));
        System.out.println(s.solution(123456));
        System.out.println(s.solution(-123456));
    }

    public int solution(int A) {
        int sign = (A < 0 ? -1 : 1);
        char[] num = Integer.toString(sign * A).toCharArray();
        char[] numShuffled = new char[num.length];
        for (int first = 0, last = num.length - 1, i = 0; first <= last;) {
            numShuffled[i++] = num[first++];
            if (first <= last) numShuffled[i++] = num[last--];
        }
        return sign * Integer.parseInt(new String(numShuffled));
    }

}