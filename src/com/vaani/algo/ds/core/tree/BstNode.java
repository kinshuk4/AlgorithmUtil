package com.vaani.algo.ds.core.tree;

public class BstNode<T extends Comparable> {
    public BstNode<T> left;
    public BstNode<T> right;
    public T val;
    public boolean isVisited;

    public BstNode(T data) {
        this.val = data;
    }

    public BstNode(BstNode left, T data, BstNode right) {
        this.left = left;
        this.val = data;
        this.right = right;
    }

    public BstNode getLeft() {
        return left;
    }

    public void setLeft(BstNode left) {
        this.left = left;
    }

    public BstNode getRight() {
        return right;
    }

    public void setRight(BstNode right) {
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
}
