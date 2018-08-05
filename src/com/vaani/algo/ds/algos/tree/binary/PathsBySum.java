package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Ftrees%2FGetPathsBySum.java
public class PathsBySum {
    static Stack<TreeNode> stack = new Stack<>();
    static List<List<TreeNode>> pathsList = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);//给定的树
        TreeNode l1 = new TreeNode(5);
        TreeNode l2 = new TreeNode(4);
        TreeNode r1 = new TreeNode(12);
        TreeNode r2 = new TreeNode(7);
        root.left = l1;
        root.right = r1;
        l1.left = l2;
        l1.right = r2;
        int num = 22;
        dfs(root, new ArrayList<TreeNode>());

        for (List<TreeNode> list : pathsList) {
            int temp = 0;
            for (TreeNode<Integer> t : list) {
                temp += t.val;
            }
            if (temp == num) {
                System.out.println(list);
            }
        }
    }

    public static void dfs(TreeNode root, List<TreeNode> path) {
        if (null == root) {
            return;
        }

        root.isVisited = true;

        stack.push(root);

        path.add(root);

        while (!stack.isEmpty()) {

            TreeNode pop = stack.pop();
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
