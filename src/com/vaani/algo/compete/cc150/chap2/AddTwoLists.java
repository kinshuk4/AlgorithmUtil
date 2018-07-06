package com.vaani.algo.compete.cc150.chap2;

import com.vaani.algo.ds.core.list.ListNode;

/**
 * You have two numbers represented by a linked list, where each node contains a
 * single digit. The digits are stored in reverse order, such that the 1s digit
 * is at the head of the list. Write a function that adds the two numbers and
 * return the sum as a linked list.
 */
public class AddTwoLists {

    // this question is available at leetcode, question Add Two Numbers
  /*
  You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
  Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
  Output: 7 -> 0 -> 8
   */

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    // O(1) space, O(n) time
    public class Solution {
        public ListNode addTwoNumbers(ListNode<Integer> l1, ListNode<Integer> l2) {
            // Start typing your Java solution below
            // DO NOT write main() function
            int borrow = 0;
            ListNode sum = new ListNode(0);
            ListNode sumCur = sum;
            while (l1 != null || l2 != null) {
                int val = 0;
                if (l1 != null) {
                    val += l1.val;
                    l1 = l1.next;
                }
                if (l2 != null) {
                    val += l2.val;
                    l2 = l2.next;
                }
                val += borrow;
                if (val >= 10) {
                    val -= 10;
                    borrow = 1;
                } else {
                    borrow = 0;
                }
                sumCur.next = new ListNode(val);
                sumCur = sumCur.next;
            }
            if (borrow == 1) {
                sumCur.next = new ListNode(1);
            }
            return sum.next;
        }
    }


}
