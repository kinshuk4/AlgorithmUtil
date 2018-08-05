package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.TreeNodeWithParent;

/**
 * Given a binary tree, find the lowest common ancestor of two given nodes in the tree. Each node contains a parent pointer which links to its parent.
 */
public class LowestCommonAncestor3 {
    public static void main(String[] args) {
        TreeNodeWithParent root = new TreeNodeWithParent(6);
        root.left = new TreeNodeWithParent(2);
        root.left.parent = root;
        root.right = new TreeNodeWithParent(8);
        root.right.parent = root;
        root.left.left = new TreeNodeWithParent(0);
        root.left.left.parent = root.left;
        root.left.right = new TreeNodeWithParent(4);
        root.left.right.parent = root.left;
        root.right.left = new TreeNodeWithParent(7);
        root.right.left.parent = root.right;
        root.right.right = new TreeNodeWithParent(9);
        root.right.right.parent = root.right;
        root.left.right.left = new TreeNodeWithParent(3);
        root.left.right.left.parent = root.left.right;
        root.left.right.right = new TreeNodeWithParent(5);
        root.left.right.right.parent = root.left.right;

        LowestCommonAncestor3 test = new LowestCommonAncestor3();
        System.out.println(test.getLCA(root, root.left, root.left.right.right).val);
    }

    /**
     * Time: O(h) - h is the height of the binary tree
     * Space: O(1)
     * <p>
     * Note: The two given nodes might not come from the same tree
     */
    public TreeNodeWithParent getLCA(TreeNodeWithParent root, TreeNodeWithParent p, TreeNodeWithParent q) {
        if (root == null || q == null || q == null) return null;
        if (root == p || root == q) return root;

        int pHeight = getHeight(p);
        int qHeight = getHeight(q);
        if (pHeight > qHeight) return getLCA(root, q, p);

        int diff = qHeight - pHeight;
        while (diff > 0) {
            q = q.parent;
            diff--;
        }

        while (q != null && p != null) {
            if (p == q) return p;
            q = q.parent;
            p = p.parent;
        }
        return null;
    }

    public int getHeight(TreeNodeWithParent node) {
        int count = 0;
        while (node != null) {
            node = node.parent;
            count++;
        }
        return count;
    }
}
