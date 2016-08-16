package com.vaani.algo.misc;

import com.vaani.algo.ds.core.TreeNode;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class ValidateBinarySearchTree {
    TreeNode previous;
    boolean valid;
    public boolean isValidBST(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        previous = null;
        valid = true;
        isValid(root);
        return valid;
    }
    
    public void isValid(TreeNode root) {
        if (root == null) {
            return;
        }
        isValid(root.left);
        if (previous != null && previous.val >= root.val) {
            valid = false;
            return;
        }
        previous = root;
        isValid(root.right);
    }
    
}
