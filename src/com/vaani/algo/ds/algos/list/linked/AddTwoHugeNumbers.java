package com.vaani.algo.ds.algos.list.linked;

import com.vaani.algo.ds.core.list.ListNode;

import static com.vaani.algo.ds.utils.ListUtil.arrayToList;
import static com.vaani.algo.ds.utils.ListUtil.display;
import static com.vaani.algo.ds.utils.ListUtil.reverseIterative;

/**
 * You're given 2 huge integers represented by linked lists. Each linked list element is a number from 0 to 9999 that represents a number with exactly 4 digits. The represented number might have leading zeros. Your task is to add up these huge integers and return the result in the same format.
 * <p>
 * Example
 * <p>
 * For a = [9876, 5432, 1999] and b = [1, 8001], the output should be
 * addTwoHugeNumbers(a, b) = [9876, 5434, 0].
 * <p>
 * Explanation: 987654321999 + 18001 = 987654340000.
 * <p>
 * For a = [123, 4, 5] and b = [100, 100, 100], the output should be
 * addTwoHugeNumbers(a, b) = [223, 104, 105].
 * <p>
 * Explanation: 12300040005 + 10001000100 = 22301040105.
 * <p>
 * The first number, without its leading zeros.
 * <p>
 * Guaranteed constraints:
 * 0 ≤ a size ≤ 104,
 * 0 ≤ element value ≤ 9999.
 * <p>
 * Guaranteed constraints:
 * 0 ≤ b size ≤ 104,
 * 0 ≤ element value ≤ 9999.
 */
public class AddTwoHugeNumbers {
    static ListNode<Integer> addTwoHugeNumbers(ListNode<Integer> a, ListNode<Integer> b) {
        a = reverseIterative(a);
        b = reverseIterative(b);
        display(a);
        display(b);
        ListNode<Integer> result = null;
        ListNode<Integer> curr = null;
        int carry = 0;
        while (a != null && b != null) {
            int k = a.val + b.val + carry;
            int sum = k % 10000;

            if (result == null) {
                result = new ListNode<Integer>(sum);
                curr = result;
            } else {
                ListNode<Integer> newNode = new ListNode<>(sum);
                curr.next = newNode;
                curr = curr.next;
            }
            //update the carry for next iteration
            carry = k / 10000;

            a = a.next;
            b = b.next;
        }

        ListNode<Integer> remainingList = null;
        if (a != null) {
            remainingList = a;
        } else if (b != null) {
            remainingList = b;
        }
        while (remainingList != null) {
            int k = remainingList.val + carry;
            int sum = k % 10000;

            if (result == null) {
                result = new ListNode<Integer>(sum);
                curr = result;
            } else {
                ListNode<Integer> newNode = new ListNode<>(sum);
                curr.next = newNode;
                curr = curr.next;
            }
            //update the carry for next iteration
            carry = k / 10000;

            remainingList = remainingList.next;
        }
        if (carry != 0) {
            ListNode<Integer> newNode = new ListNode<>(carry);
            curr.next = newNode;
        }

        return reverseIterative(result);
    }

    public static void main(String[] args) {
        display(addTwoHugeNumbers(arrayToList(new Integer[]{1234, 1234, 0}), arrayToList(new Integer[]{0})));
    }
}
