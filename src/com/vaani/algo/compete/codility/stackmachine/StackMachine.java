package com.vaani.algo.compete.codility.stackmachine;

import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Alexey
 */
public class StackMachine {

    public static int calc(String S) {
        if (S == null || S.isEmpty()) return -1;
        Deque<Integer> nums = new LinkedList<>();
        int result;
        for (int i = 0; i < S.length(); i++) {
            Character c = new Character(S.charAt(i));
            if ((c == '+' || c == '*') && nums.size() >= 2) {
                final Integer a = nums.pop();
                final Integer b = nums.pop();
                if (c == '+') {
                    result = a + b;
                } else {
                    result = a * b;
                }
                System.out.println(a + " " + c + " " + b + " = " + result);
                nums.addLast(result);
            } else if (nums.size() < 3 && Character.getNumericValue(c) != -1) {
                nums.addFirst(Character.getNumericValue(c));
            } else {
                return -1;
            }
            System.out.println(Arrays.toString(nums.toArray()));
        }
        return nums.peek();
    }

    public static int calc2(String S) {
        Collection<Character> operations = new LinkedList<>();
        Deque<Integer> numbers = new LinkedList<>();

        for (int i = 0; i < S.length(); i++) {
            Character c = new Character(S.charAt(i));
            if (c == '+' || c == '*') {
                operations.add(c);
            } else {
                int numericValue = Character.getNumericValue(c);
                if (numericValue == -1) return -1;
                numbers.add(numericValue);
            }
        }
        System.out.println(Arrays.toString(operations.toArray()));
        System.out.println(Arrays.toString(numbers.toArray()));

        if (numbers.size() - operations.size() != 1) return -1;

        int result = 0;
        for (Character oper : operations) {
            final Integer a = numbers.poll();
            final Integer b = numbers.poll();
            if (oper == '+') {
                result = a + b;
            } else {
                result = a * b;
            }
            System.out.println(a + " " + oper + " " + b + " = " + result);
            numbers.addFirst(result);
            System.out.println(Arrays.toString(numbers.toArray()));
        }

        return numbers.peek();
    }
}
