package com.vaani.algo.ds.algos.tree.binary.traversal;


import com.vaani.algo.ds.core.tree.TreeNode;

import java.util.Stack;

//https://github.com/paopao2/Algorithm-Practice/blob/master/Breadth:Depth%20First%20Search.java
public class DfsOnTree {
    static Stack<TreeNode> stack = new Stack<>();

    public static void main(String[] args) {
        TreeNode root = getABinaryTree();

        dfsRecursive(root);
    }

    // @formatter:off
    /**
     * Tree=
     *            1
     *         3    5
     *      9          88
     *   11
     * 14  12
     *       13
     *
     */
// @formatter:on
    public static TreeNode getABinaryTree() {
        TreeNode root = new TreeNode(1);
        TreeNode l1 = new TreeNode(3);
        TreeNode r1 = new TreeNode(5);

        root.left = l1;
        root.right = r1;

        TreeNode l2 = new TreeNode(9);
        l1.left = l2;

        TreeNode l3 = new TreeNode(11);
        l2.left = l3;


        TreeNode r2 = new TreeNode(12);
        l3.left = new TreeNode(14);
        l3.right = r2;


        TreeNode r3 = new TreeNode(13);
        r2.right = r3;

        TreeNode r4 = new TreeNode(88);
        r1.right = r4;

        return root;
    }

    public static void dfsRecursive(TreeNode root) {
        if (null == root) {
            return;
        }
        root.isVisited = true;
        stack.push(root);

        if (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            System.out.print(pop.val + "\t");
            if (pop.left != null && !pop.left.isVisited) {
                dfsRecursive(pop.left);
            }
            if (pop.right != null && !pop.right.isVisited) {
                dfsRecursive(pop.right);
            }
        }
    }

    public static void dfs(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        root.isVisited = true;
        System.out.println(root);
        while (!stack.isEmpty()) {
            TreeNode node = (TreeNode) stack.peek();
            TreeNode child = getUnvisitedChildNode(node);
            if (child != null) {
                child.isVisited = true;
                System.out.println(child);
                stack.push(child);
            } else {
                stack.pop();
            }
        }

    }

    public static TreeNode getUnvisitedChildNode(TreeNode node) {
        if (node.left != null && !node.left.isVisited) {
            return node.left;
        } else if (node.right != null && !node.left.isVisited) {
            return node.right;
        } else {
            return null;
        }


    }


}
