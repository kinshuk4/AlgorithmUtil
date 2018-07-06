package com.vaani.algo.compete.cc150.chap2;

import com.vaani.algo.ds.core.list.ListNode;
import com.vaani.algo.util.Utils;

/**
 * Given a circular linked list, implement an algorithm which returns the node
 * at the beginning of the loop.
 * DEFINITION Circular linked list: A (corrupt) linked list in which a node's
 * next pointer points to an earlier node, so as to make a loop in the linked
 * list.
 * <p>
 * EXAMPLE Input: A-> B -> C -> D -> E -> C [the same C as earlier Output: C
 */
// O(1) space, O(n) time
public class GetStartOfLoopInList {
    // two pointer problem
    public ListNode findLoop(ListNode head) {
        // write implementation here

        ListNode slow = head;
        ListNode fast = head;
        // find collide
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        // no collide
        if (fast == null || fast.next == null) {
            System.out.printf("No loop for %s.\n", Utils.listNodeToString(head));
            return null;
        }

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

}
