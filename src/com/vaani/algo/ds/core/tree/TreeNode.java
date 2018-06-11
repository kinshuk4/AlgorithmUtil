package com.vaani.algo.ds.core.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class TreeNode<T> {
    public TreeNode left;
    public TreeNode right;
    public T val;
    public boolean isVisited;

    public TreeNode(T data) {
        this.val = data;
    }

    public TreeNode(TreeNode left, T data, TreeNode right){
        this.left = left;
        this.val = data;
        this.right = right;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public String toString() {
        return String.valueOf(this.val);
    }

    public String detailedToString() {
        String result = detailedToStringHelper(this);
        return result;
    }

    //Using the logic from here : http://stackoverflow.com/questions/20489834/binary-search-tree-recursive-tostring
    public String detailedToStringHelper(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        if (root == null)
            return "";
        builder.append(detailedToStringHelper(root.left));
        builder.append(detailedToStringHelper(root.right));
        return builder.append(String.valueOf(root.val)).toString();
    }

    public  boolean insertForBst(TreeNode<Integer> n) {
        if (n == null)
            return false;
        int val2 = (Integer) val;
        if (val2 >= n.val) {
            if (this.left == null)
                this.left = n;
            else
                this.left.insertForBst(n);
        } else {
            if (this.right == null)
                this.right = n;
            else
                this.right.insertForBst(n);
        }
        return true;

    }

    public void display() {
        System.out.println(this.detailedToString());
    }

    public int size() {
        int size = 0;
        if (left != null) {
            size += left.size();
        }
        if (right != null) {
            size += right.size();
        }
        return size + 1;
    }

    private List<List<Integer>> levelOrder(TreeNode<Integer> root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;

        Queue<TreeNode<Integer>> queue1 = new LinkedList<TreeNode<Integer>>();
        Queue<TreeNode<Integer>> queue2 = new LinkedList<TreeNode<Integer>>();
        queue1.add(root);
        List<Integer> level = new ArrayList<Integer>();

        while (!queue1.isEmpty()) {
            TreeNode<Integer> node = queue1.poll();
            level.add(node.val);
            if (node.left != null) queue2.add(node.left);
            if (node.right != null) queue2.add(node.right);
            if (queue1.isEmpty()) {
                result.add(level);
                level = new ArrayList<Integer>();
                queue1.addAll(queue2);
                queue2.clear();
            }
        }
        return result;
    }


}
