package com.vaani.algo.ds.tree.bst;

import com.vaani.algo.ds.core.tree.TreeNode;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * Created by Xiaomeng on 8/16/2014.
 */
public class ConvertSortedArrayToBST {
    public static void main(String[] args) {
        int[] num = {2, 4, 7, 9, 10, 12};
        ConvertSortedArrayToBST test = new ConvertSortedArrayToBST();
        test.sortedArrayToBST(num).display();
    }

    public TreeNode sortedArrayToBST(int[] num) {
        return sortedArrayToBST(num, 0, num.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] arr, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = sortedArrayToBST(arr, start, mid - 1);
        root.right = sortedArrayToBST(arr, mid + 1, end);
        return root;
    }
}
