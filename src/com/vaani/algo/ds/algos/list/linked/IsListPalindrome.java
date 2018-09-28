package com.vaani.algo.ds.algos.list.linked;

import com.vaani.algo.ds.core.list.ListNode;

import static com.vaani.algo.ds.utils.ListUtil.*;

public class IsListPalindrome {
    static boolean isListPalindrome(ListNode<Integer> l) {
        ListNode<Integer> midNode = getMidNode(l);
        System.out.println(midNode.val);
        ListNode<Integer> reverseSecond = reverseIterative(midNode);
        display(reverseSecond);
        ListNode<Integer> curr = l;
        display(curr);
        while (reverseSecond != null && curr != null) {
            System.out.println(curr.val + " --- " + reverseSecond.val);
            //converting to primitive as != will not work when comparing integers larger than 128 in java
            int v1 = curr.val;
            int v2 = reverseSecond.val;
            if (v1 != v2) {
                return false;
            }
            curr = curr.next;
            reverseSecond = reverseSecond.next;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 1000000000, -1000000000, -1000000000, 1000000000, 1};
        System.out.println(isListPalindrome(arrayToList(arr)));
    }


}
