package com.vaani.algo.trees.binary;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.vaani.algo.ds.utils.TreeNode;

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
        int num = 22;//给定的整数
        dfs(root, new ArrayList<TreeNode>());
        //这样就获取到了所有路径的集合
        for (List<TreeNode> list : pathsList) {
            int temp = 0;
            for (TreeNode t : list) {
                temp += t.data;
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
        //设置访问标记
        root.isVisited = true;
        //入栈
        stack.push(root);
        //加入链表
        path.add(root);
        //开始弹栈
        while (!stack.isEmpty()) {
            //在弹栈的同时也要将该结点从路径中移除，但是在移除前要判断是否已经是一条完整的路径，如果是一条完整的路径，则存储之
            TreeNode pop = stack.pop();
            if (pop.left != null && !pop.left.isVisited) {
                dfs(pop.left, path);
            }
            if (pop.right != null && !pop.right.isVisited) {
                dfs(pop.right, path);
            }
            if (pop.left == null && pop.right == null) {
                //说明到了叶子结点了
                pathsList.add(new ArrayList<>(path));
            }
            path.remove(pop);//从一条路径中移除
        }
}
}
