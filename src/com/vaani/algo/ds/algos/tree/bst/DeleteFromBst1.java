package com.vaani.algo.ds.algos.tree.bst;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

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
    static BinaryTreeNode<Integer> deleteFromBST(BinaryTreeNode<Integer> t, int[] queries) {
        if(t==null || queries==null || queries.length==0){
            return t;
        }
        for (int key : queries) {
            if(t==null){
                return null;
            }
            BinaryTreeNode<Integer>[] searchedResult = searchWithParent(t, key, null);
            BinaryTreeNode<Integer> searched = searchedResult[0];
            BinaryTreeNode<Integer> parent = searchedResult[1];
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
                    BinaryTreeNode<Integer> curr = searched.left;
                    BinaryTreeNode<Integer> currParent = null;
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

    static BinaryTreeNode<Integer> setParentPtr(BinaryTreeNode<Integer> parent, BinaryTreeNode<Integer> searched, BinaryTreeNode<Integer> newChild, BinaryTreeNode<Integer> root){
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
//        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(5);
//        root.setLeft(new BinaryTreeNode<>(2));
//        root.setRight(new BinaryTreeNode<>(6));
//
//        root.getLeft().setLeft(new BinaryTreeNode<>(1));
//        root.getLeft().setRight(new BinaryTreeNode<>(3));
//
//        root.getRight().setRight(new BinaryTreeNode<>(8));
//        root.getRight().getRight().setLeft(new BinaryTreeNode<>(7));
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(3);
        root.setLeft(new BinaryTreeNode<>(2));
        root.setRight(new BinaryTreeNode<>(5));

        root.getLeft().setLeft(new BinaryTreeNode<>(1));


        deleteFromBST(root, new int[] {3,2,1});
    }


    static BinaryTreeNode<Integer> deleteFromBST2(BinaryTreeNode<Integer> tree, int[] queries) {
        for (int query : queries) {
            tree = deleteFromBstHelper(tree, query);
        }
        return tree;
    }

    private static BinaryTreeNode<Integer> deleteFromBstHelper(BinaryTreeNode<Integer> root, int value) {
        if (root == null) {
            return null;
        }
        if (root.val == value) {
            if (root.left == null) {
                return root.right;
            } else {
                BinaryTreeNode<Integer> m = findMaxInBst(root.left);
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
