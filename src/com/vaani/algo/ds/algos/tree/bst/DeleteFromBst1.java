package com.vaani.algo.ds.algos.tree.bst;

import com.vaani.algo.ds.core.tree.TreeNode;

import static com.vaani.algo.ds.utils.BstUtil.*;

/**
 * A tree is considered a binary search tree (BST) if for each of its nodes the following is true:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and the right subtrees must also be binary search trees.
 * Removing a value x from a BST t is done in the following way:
 *
 * If there is no x in t, nothing happens;
 * Otherwise, let t' be a subtree of t such that t'.value = x.
 * If t' has a left subtree, remove the rightmost node from it and put it at the root of t';
 * Otherwise, remove the root of t' and its right subtree becomes the new t's root.
 * For example, removing 4 from the following tree has no effect because there is no such value in the tree:
 *
 *     5
 *    / \
 *   2   6
 *  / \   \
 * 1   3   8
 *        /
 *       7
 * Removing 5 causes 3 (the rightmost node in left subtree) to move to the root:
 *
 *     3
 *    / \
 *   2   6
 *  /     \
 * 1       8
 *        /
 *       7
 * And removing 6 after that creates the following tree:
 *
 *     3
 *    / \
 *   2   8
 *  /   /
 * 1   7
 * You're given a binary search tree t and an array of numbers queries. Your task is to remove queries[0], queries[1], etc., from t, step by step, following the algorithm above. Return the resulting BST.
 */
public class DeleteFromBst1 {
    //Instead of adding next inorder element , we add the right most child of left parent
    static TreeNode<Integer> deleteFromBST(TreeNode<Integer> t, int[] queries) {
        if(t==null || queries==null || queries.length==0){
            return t;
        }
        for (int key : queries) {
            if(t==null){
                return null;
            }
            TreeNode<Integer>[] searchedResult = searchWithParent(t, key, null);
            TreeNode<Integer> searched = searchedResult[0];
            TreeNode<Integer> parent = searchedResult[1];
            if (searched != null) {



                if (searched.left == null && searched.right == null) {
                    //root element without children
                    if(parent==null){
                        return null;
                    }
                    t = setParentPtr(parent, searched, null, t);
                } else if (searched.left == null) {
                    t = setParentPtr(parent, searched, searched.right, t);
                } else if (searched.right == null) {
                    t = setParentPtr(parent, searched, searched.left, t);
                } else {
                    TreeNode<Integer> curr = searched.left;
                    TreeNode<Integer> currParent = null;
                    while(curr.right!=null){
                        currParent = curr;
                        curr = curr.right;
                    }
                    searched.setVal(curr.val);
                    if(currParent!=null) {
                        if (currParent.left == curr) {
                            currParent.left = null;
                        } else {
                            currParent.right = null;
                        }
                    }else{

                            searched.left = curr.left;


                    }
                }
            }
        }

        return t;
    }

    static TreeNode<Integer> setParentPtr(TreeNode<Integer> parent, TreeNode<Integer> searched, TreeNode<Integer> newChild, TreeNode<Integer> root){
        if(parent == null){
            root = newChild;
            return root;
        }
        if(parent.left == searched){
            parent.left = newChild;
        }else{
            parent.right = newChild;
        }

        return root;
    }


    public static void main(String[] args) {
//        TreeNode<Integer> root = new TreeNode<>(5);
//        root.setLeft(new TreeNode<>(2));
//        root.setRight(new TreeNode<>(6));
//
//        root.getLeft().setLeft(new TreeNode<>(1));
//        root.getLeft().setRight(new TreeNode<>(3));
//
//        root.getRight().setRight(new TreeNode<>(8));
//        root.getRight().getRight().setLeft(new TreeNode<>(7));
        TreeNode<Integer> root = new TreeNode<>(3);
        root.setLeft(new TreeNode<>(2));
        root.setRight(new TreeNode<>(5));

        root.getLeft().setLeft(new TreeNode<>(1));


        deleteFromBST(root, new int[] {3,2,1});
    }


    static TreeNode<Integer> deleteFromBST2(TreeNode<Integer> tree, int[] queries) {
        for (int query : queries) {
            tree = deleteFromBstHelper(tree, query);
        }
        return tree;
    }

    private static TreeNode<Integer> deleteFromBstHelper(TreeNode<Integer> root, int value) {
        if (root == null) {
            return null;
        }
        if (root.val == value) {
            if (root.left == null) {
                return root.right;
            } else {
                TreeNode<Integer> m = findMaxInBst(root.left);
                m.left = deleteMaxInBst(root.left);
                m.right = root.right;
                return m;
            }
        } else {
            root.left = deleteFromBstHelper(root.left, value);
            root.right = deleteFromBstHelper(root.right, value);
        }
        return root;
    }




}
