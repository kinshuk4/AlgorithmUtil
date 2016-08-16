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
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (root == null) {
            return true;
        }
        
        return isSymmetricHelper(root.left, root.right);
    }
    
    public boolean isSymmetricHelper(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        } else if (n1 == null || n2 == null || n1.val != n2.val) {
            return false;
        } else {
            return isSymmetricHelper(n1.left, n2.right) && isSymmetricHelper(n1.right, n2.left);
            
        }
    }
}
