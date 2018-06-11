package com.vaani.algo.ds.core.tree;

/**
 * Created by Xiaomeng on 11/2/2014.
 */
public class TreeNodeWithParent {
    public TreeNodeWithParent left;
    public TreeNodeWithParent right;
    public TreeNodeWithParent parent;
    public int val;

    public TreeNodeWithParent(int val) {
        this.val = val;
    }
}
