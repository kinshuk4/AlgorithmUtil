package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import static com.vaani.algo.ds.algos.tree.binary.IsSameTree.isSameTree;

//https://app.codesignal.com/interview-practice/task/mDpAJnDQkJqaYYsCg
public class IsSubTree {
    static boolean isSubtree(BinaryTreeNode<Integer> t1, BinaryTreeNode<Integer> t2) {
        if (t2 == null) {
            return true;
        } else if (t1 == null) {
            return false;
        }

        if (t1.val.equals(t2.val)) {
            return isSameTree(t1, t2);
        }
        return isSubtree(t1.left, t2) || isSubtree(t1.right, t2);
    }



    //This logic will fail
    boolean isSubtree_Wrong(BinaryTreeNode<Integer> t1, BinaryTreeNode<Integer> t2) {
        if (t2 == null) {
            return true;
        } else if (t1 == null) {
            return false;
        }
        //Why it is wrong - when value is same, tree should match and not check for subtree
        if (t1.val.equals(t2.val)) {
            return isSubtree_Wrong(t1.left, t2.left) && isSubtree_Wrong(t1.right, t2.right);
        } else {
            return isSubtree_Wrong(t1.left, t2) || isSubtree_Wrong(t1.right, t2);
        }
    }
}
