package com.vaani.algo.ds.algos.tree.binary.traversal;


import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.Stack;

//https://github.com/paopao2/Algorithm-Practice/blob/master/Breadth:Depth%20First%20Search.java
public class DfsOnTree {
    static Stack<BinaryTreeNode> stack = new Stack<>();

    public static void main(String[] args) {
        BinaryTreeNode root = getABinaryTree();

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
    public static BinaryTreeNode getABinaryTree() {
        BinaryTreeNode root = new BinaryTreeNode(1);
        BinaryTreeNode l1 = new BinaryTreeNode(3);
        BinaryTreeNode r1 = new BinaryTreeNode(5);

        root.left = l1;
        root.right = r1;

        BinaryTreeNode l2 = new BinaryTreeNode(9);
        l1.left = l2;

        BinaryTreeNode l3 = new BinaryTreeNode(11);
        l2.left = l3;


        BinaryTreeNode r2 = new BinaryTreeNode(12);
        l3.left = new BinaryTreeNode(14);
        l3.right = r2;


        BinaryTreeNode r3 = new BinaryTreeNode(13);
        r2.right = r3;

        BinaryTreeNode r4 = new BinaryTreeNode(88);
        r1.right = r4;

        return root;
    }

    public static void dfsRecursive(BinaryTreeNode root) {
        if (null == root) {
            return;
        }
        root.isVisited = true;
        stack.push(root);

        if (!stack.isEmpty()) {
            BinaryTreeNode pop = stack.pop();
            System.out.print(pop.val + "\t");
            if (pop.left != null && !pop.left.isVisited) {
                dfsRecursive(pop.left);
            }
            if (pop.right != null && !pop.right.isVisited) {
                dfsRecursive(pop.right);
            }
        }
    }

    public static void dfs(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        root.isVisited = true;
        System.out.println(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode node = (BinaryTreeNode) stack.peek();
            BinaryTreeNode child = getUnvisitedChildNode(node);
            if (child != null) {
                child.isVisited = true;
                System.out.println(child);
                stack.push(child);
            } else {
                stack.pop();
            }
        }

    }

    public static BinaryTreeNode getUnvisitedChildNode(BinaryTreeNode node) {
        if (node.left != null && !node.left.isVisited) {
            return node.left;
        } else if (node.right != null && !node.left.isVisited) {
            return node.right;
        } else {
            return null;
        }


    }


}
