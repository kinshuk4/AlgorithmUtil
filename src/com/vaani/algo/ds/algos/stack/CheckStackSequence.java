package com.vaani.algo.ds.algos.stack;

import java.util.Stack;

//Validate Pop Sequence of IStack
//http://www.sigmainfy.com/blog/interview-question-6-validate-pop-sequence-of-stack.html
//https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Fstack%2FCheckStackSequence.java
public class CheckStackSequence {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 4, 5};
        int[] nums2 = new int[]{4, 5, 3, 2, 1};
        boolean check = check(nums1, nums2);
        System.out.println(check);

        int[] nums3 = new int[]{1, 4, 5, 2, 3};
        boolean check1 = check(nums1, nums3);
        System.out.println(check1);

        int[] nums4 = new int[]{4, 3, 5, 1, 2};
        boolean check2 = check(nums1, nums4);
        System.out.println(check2);

        int[] nums5 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] nums6 = new int[]{3, 8, 7, 9, 6, 5, 4, 2, 1};
        boolean check3 = check(nums5, nums6);
        System.out.println(check3);

    }

    public static boolean check(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack();
        int current = 0;
        for (int i = 0; i < nums1.length; i++) {
            if (nums2[current] == nums1[i]) {//This element stack immediately after the stack, so the current i and increments instantly
                current++;
            } else {
                if (!stack.isEmpty() && nums2[current] == stack.peek()) {
                    current++;
                    stack.pop();
                    stack.push(nums1[i]);//Do not forget to push new elements onto the stack
                } else {
                    stack.push(nums1[i]);
                }
            }
        }
        while (current < nums2.length && nums2[current] == stack.peek() && !stack.isEmpty()) {
            current++;
            stack.pop();
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }
}
