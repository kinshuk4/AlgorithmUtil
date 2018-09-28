package com.vaani.algo.ds.algos.list.linked;

import com.vaani.algo.ds.core.list.ListNode;

import static com.vaani.algo.ds.utils.ListUtil.arrayToList;

/**
 * Note: Try to solve this task in O(list size) time using O(1) additional space, since this is what you'll be asked during an interview.
 * <p>
 * Given a singly linked list of integers l and a non-negative integer n, move the last n list nodes to the beginning of the linked list.
 * <p>
 * Example
 * <p>
 * For l = [1, 2, 3, 4, 5] and n = 3, the output should be
 * rearrangeLastN(l, n) = [3, 4, 5, 1, 2];
 * For l = [1, 2, 3, 4, 5, 6, 7] and n = 1, the output should be
 * rearrangeLastN(l, n) = [7, 1, 2, 3, 4, 5, 6].
 */
public class RearrangeList {
    static ListNode<Integer> rearrangeLastN(ListNode<Integer> l, int n) {
        if (l == null) {
            return null;
        }

        if(n==0){
            return l;
        }

        ListNode first = l, second = l;

        for (int i = 0; i < n; i++) {
            if (second == null) {
                return null;
            }
            second = second.next;

        }

        //we are already at the end of the list
        if(second == null){
            return first; // or return l;
        }

        while (second.next != null) {
            first = first.next;
            second = second.next;
        }

//        first.display(); 2 3 4 5
//        second.display(); 5
        ListNode result = first.next; //point result to nth element from last
        first.next = null; // break the list at nth element
        second.next = l; // point the last element of the list to start of list
        return result;
    }

    public static void main(String[] args) {
        rearrangeLastN(arrayToList(new Integer[]{1, 2, 3, 4, 5}), 3).display();
    }
}
