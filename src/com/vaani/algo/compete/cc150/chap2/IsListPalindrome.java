package com.vaani.algo.compete.cc150.chap2;

import com.vaani.algo.ds.core.list.ListNode;

import java.util.Stack;


/**
 * Implement a function to check if a linked list is a palindrome.
 */
// O(n) space, O(n) time
public class IsListPalindrome {

    public boolean isPalindrome(ListNode<Integer> head) {
        // write implementation here
        Stack<Integer> stack = new Stack<Integer>();
        ListNode<Integer> slow = head;
        ListNode<Integer> fast = head;

        while (fast != null && fast.next != null) {
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        // if list has odd number of elements
        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            int top = stack.pop();
            if (slow.val != top) {
                return false;
            }
            slow = slow.next;
        }

        return true;
    }
}

