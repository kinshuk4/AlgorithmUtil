package com.vaani.algo.ds.algos.tree.bst;

import com.vaani.algo.ds.core.list.ListNode;
import com.vaani.algo.ds.core.tree.BinaryTreeNode;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * Created by Xiaomeng on 8/16/2014.
 */
public class ConvertSortedListToBST {
    ListNode head;

    public static void main(String[] args) {
        ConvertSortedListToBST test = new ConvertSortedListToBST();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        test.sortedListToBST2(head).display();
    }

    /**
     * Native getTreeHeight as Convert Sorted Array to BST.
     * Top-down approach
     * Time: O(NlogN)
     *
     * @param head
     * @return
     */
    public BinaryTreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new BinaryTreeNode(head.val);
        ListNode preMid = getPreMid(head);
        ListNode mid = preMid.next;
        preMid.next = null;
        ListNode first = head, second = mid.next;
        BinaryTreeNode root = new BinaryTreeNode(mid.val);
        root.left = sortedListToBST(first);
        root.right = sortedListToBST(second);
        return root;
    }

    public ListNode getPreMid(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode first = head, second = head.next.next;
        while (second != null && second.next != null) {
            first = first.next;
            second = second.next.next;
        }
        return first;
    }

    /**
     * Bottom-up approach
     * Time: O(n)
     * Reference: http://leetcode.com/2010/11/convert-sorted-list-to-balanced-binary.html
     *
     * @param head
     * @return
     */
    public BinaryTreeNode sortedListToBST2(ListNode head) {
        ListNode node = head;
        this.head = head;
        int len = 0;
        while (node != null) {
            node = node.next;
            len++;
        }
        return sortedListToBST2(0, len - 1);
    }

    public BinaryTreeNode sortedListToBST2(int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        BinaryTreeNode left = sortedListToBST2(start, mid - 1);
        BinaryTreeNode root = new BinaryTreeNode(head.val);
        head = head.next;
        root.left = left;
        root.right = sortedListToBST2(mid + 1, end);
        return root;
    }
}
