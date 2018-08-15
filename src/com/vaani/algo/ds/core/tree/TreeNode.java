package com.vaani.algo.ds.core.tree;

import com.vaani.algo.ds.utils.BstUtil;

import java.util.*;


public class TreeNode<T> {
    public TreeNode<T> left;
    public TreeNode<T> right;
    public T val;
    public boolean isVisited;

    public TreeNode(T data) {
        this.val = data;
    }

    public TreeNode(TreeNode<T> left, T data, TreeNode<T> right) {
        this.left = left;
        this.val = data;
        this.right = right;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
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


    public String toString2() {
        return "Tree{" +
                "value=" + val +
                ", left=" + (left != null ? left.val + "..." : "null") +
                ", right=" + (right != null ? right.val + "..." : "null") +
                '}';
    }

     boolean equalTrees(TreeNode<T> t1, TreeNode<T> t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return Objects.equals(t1.val, t2.val)
                && equalTrees(t1.left, t2.left)
                && equalTrees(t1.right, t2.right);
    }


}
