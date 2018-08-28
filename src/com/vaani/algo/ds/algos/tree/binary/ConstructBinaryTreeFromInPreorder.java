package com.vaani.algo.ds.algos.tree.binary;
/*


Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, if tree looks like this:

    1
   / \
  2   3
 /   / \
4   5   6
then the traversals will be as follows:

Inorder traversal: [4, 2, 1, 5, 3, 6]
Preorder traversal: [1, 2, 4, 3, 5, 6]
Given the inorder and preorder traversals of a binary tree t, but not t itself, restore t and return it.


*/

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

public class ConstructBinaryTreeFromInPreorder {
    public static BinaryTreeNode<Integer> buildTree(int[] preorder, int[] inorder) {
        int preLength = preorder.length;
        int inLength = inorder.length;

        if (preLength != inLength) {
            return null;
        }

        return buildTreeHelper(preorder, inorder, 0, preLength - 1, 0, inLength - 1);
    }

    public static BinaryTreeNode<Integer> buildTreeHelper(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preEnd < preStart || inEnd < inStart) {
            return null;
        }

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(preorder[preStart]);

        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                int leftPreStart = preStart + 1; // left node will be next node if not null
                int rightPreStart = preEnd - inEnd + i + 1; // diff of 2 nodes
                int leftPreEnd = preStart - inStart + i;
                root.left = buildTreeHelper(preorder, inorder, leftPreStart, leftPreEnd, inStart, i - 1);
                root.right = buildTreeHelper(preorder, inorder, rightPreStart, preEnd, i + 1, inEnd);
            }
        }

        return root;
    }

    BinaryTreeNode<Integer> buildTree2(int in[], int pre[], int inStrt, int inEnd, Integer preIndex)
    {
        if (inStrt > inEnd)
            return null;

        /* Pick current node from Preorder traversal using preIndex
           and increment preIndex */
        BinaryTreeNode<Integer> tNode = new BinaryTreeNode<Integer>(pre[preIndex++]);

        /* If this node has no children then return */
        if (inStrt == inEnd)
            return tNode;

        /* Else find the index of this node in Inorder traversal */
        int inIndex = search(in, inStrt, inEnd, tNode.val);

        /* Using index in Inorder traversal, construct left and
           right subtress */
        tNode.left = buildTree2(in, pre, inStrt, inIndex - 1, preIndex);
        tNode.right = buildTree2(in, pre, inIndex + 1, inEnd, preIndex);

        return tNode;
    }

    /* Function to find index of value in arr[start...end]
     The function assumes that value is present in in[] */
    int search(int arr[], int strt, int end, int value)
    {
        int i;
        for (i = strt; i <= end; i++)
        {
            if (arr[i] == value)
                return i;
        }
        return i;
    }

}