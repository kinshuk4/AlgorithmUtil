package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Ftrees%2FGetPathsBySum.java
public class PathsBySum {
    static Stack<BinaryTreeNode> stack = new Stack<>();
    static List<List<BinaryTreeNode>> pathsList = new ArrayList<>();

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(10);//给定的树
        BinaryTreeNode l1 = new BinaryTreeNode(5);
        BinaryTreeNode l2 = new BinaryTreeNode(4);
        BinaryTreeNode r1 = new BinaryTreeNode(12);
        BinaryTreeNode r2 = new BinaryTreeNode(7);
        root.left = l1;
        root.right = r1;
        l1.left = l2;
        l1.right = r2;
        int num = 22;
        dfs(root, new ArrayList<BinaryTreeNode>());

        for (List<BinaryTreeNode> list : pathsList) {
            int temp = 0;
            for (BinaryTreeNode<Integer> t : list) {
                temp += t.val;
            }
            if (temp == num) {
                System.out.println(list);
            }
        }
    }

    public static void dfs(BinaryTreeNode root, List<BinaryTreeNode> path) {
        if (null == root) {
            return;
        }

        root.isVisited = true;

        stack.push(root);

        path.add(root);

        while (!stack.isEmpty()) {

            BinaryTreeNode pop = stack.pop();
            if (pop.left != null && !pop.left.isVisited) {
                dfs(pop.left, path);
            }
            if (pop.right != null && !pop.right.isVisited) {
                dfs(pop.right, path);
            }
            if (pop.left == null && pop.right == null) {

                pathsList.add(new ArrayList<>(path));
            }
            path.remove(pop);
        }
    }
}
