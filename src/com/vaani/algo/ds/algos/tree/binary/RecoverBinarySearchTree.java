package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.TreeNode;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * <p>
 * Recover the tree without changing its structure.
 * Note:
 * A getTreeHeight using O(n) space is pretty straight forward. Could you devise a constant space getTreeHeight?
 * <p>
 * Created by Xiaomeng on 8/16/2014.
 */
public class RecoverBinarySearchTree {
    TreeNode<Integer> pre;
    TreeNode<Integer> first;
    TreeNode<Integer> second;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(5);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
        root.left.right.left = new TreeNode(10);
        //root.display();
        RecoverBinarySearchTree recover = new RecoverBinarySearchTree();
        recover.recoverTree(root);
        root.display();
    }

    public void recoverTree(TreeNode<Integer> root) {
        inorder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    public void inorder(TreeNode<Integer> root) {
        if (root == null) return;
        inorder(root.left);
        if (pre == null) {
            pre = root;
        } else {
            if (pre.val > root.val) {
                if (first == null) first = pre;
                second = root;
            }
            pre = root;
        }
        inorder(root.right);
    }
}
