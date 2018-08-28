package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * <p>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * Created by Xiaomeng on 8/12/2014.
 */
public class ConstructFromPreorderAndInorder {
    public static void main(String[] args) {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder = {4, 2, 5, 1, 6, 3, 7};

        ConstructFromPreorderAndInorder test = new ConstructFromPreorderAndInorder();
        test.buildTree(preorder, inorder).display();
    }

    public BinaryTreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public BinaryTreeNode buildTree(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        if (pStart > pEnd || iStart > iEnd) return null;

        int rootVal = preorder[pStart];

        int index = iStart;
        while (index <= iEnd && inorder[index] != rootVal) index++;

        BinaryTreeNode root = new BinaryTreeNode(rootVal);
        int leftLen = index - iStart;
        int rightLen = iEnd - index;

        root.left = buildTree(preorder, pStart + 1, pStart + leftLen, inorder, iStart, index - 1);
        root.right = buildTree(preorder, pStart + leftLen + 1, pStart + leftLen + rightLen, inorder, index + 1, iEnd);

        return root;
    }
}
