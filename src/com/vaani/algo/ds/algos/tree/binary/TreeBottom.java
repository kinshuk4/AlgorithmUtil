package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.*;

/**
 * You are given a recursive notation of a binary tree: each node of a tree is represented as a set of three elements:
 * <p>
 * value of the node;
 * left subtree;
 * right subtree.
 * So, a tree can be written as (value left_subtree right_subtree). It is guaranteed that 1 ≤ value ≤ 109. If a node doesn't exist then it is represented as an empty set: (). For example, here is a representation of a tree in the given picture:
 * <p>
 * (2 (7 (2 () ()) (6 (5 () ()) (11 () ()))) (5 () (9 (4 () ()) ())))
 * <p>
 * <p>
 * Your task is to obtain a list of nodes, that are the most distant from the tree root, in the order from left to right.
 * <p>
 * In the notation of a node its value and subtrees are separated by exactly one space character.
 * <p>
 * Example
 * <p>
 * For
 * <p>
 * tree = "(2 (7 (2 () ()) (6 (5 () ()) (11 () ()))) (5 () (9 (4 () ()) ())))"
 * the output should be
 * treeBottom(tree) = [5, 11, 4].
 * <p>
 * Input/Output
 * <p>
 * [execution time limit] 3 seconds (java)
 * <p>
 * [input] string tree
 * <p>
 * Guaranteed constraints:
 * 5 ≤ tree.length ≤ 120.
 */
public class TreeBottom {
    static int[] treeBottom(String tree) {
        BinaryTreeNode<String> root = null;
        BinaryTreeNode<String> currParent = null;
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode<String> treeNode = null;
        for (int i = 0; i < tree.length(); i++) {
            char c = tree.charAt(i);
            char d = ')';
            if (i < tree.length() - 1) {
                d = tree.charAt(i + 1);
            }
            switch (c) {
                case '(':
                    int j = i+1;

                    StringBuilder sb = new StringBuilder();
                    if(tree.charAt(j) == ')'){
                        sb.append(')');
                    }else{
                        while(tree.charAt(j)!=' ' && j < tree.length()){
                            sb.append(tree.charAt(j));
                            j++;
                        }
                    }

                    treeNode = new BinaryTreeNode<>(sb.toString());

                    if (root == null) {
                        root = treeNode;
                    }

                    stack.push(treeNode);
                    break;
                case ')':
                    BinaryTreeNode<String> child = null;
                    if(!stack.empty())
                        child= stack.pop();
                    if(!stack.empty()){
                        currParent = stack.peek();
                        if (currParent.getLeft() == null) {
                            currParent.setLeft(child);
                        } else {
                            currParent.setRight(child);
                        }
                    }


                case ' ':
                    continue;


            }
        }


        fixTree(root);

        int height = heightOfTree(root);

        System.out.println(height);

        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);
        while(height > 1){
            height--;
            Queue<BinaryTreeNode> q2 = new LinkedList<>();
            while(!q.isEmpty()){
                BinaryTreeNode treeNode1 = q.poll();
                if(treeNode1.getLeft()!=null){
                    ((LinkedList<BinaryTreeNode>) q2).add(treeNode1.getLeft());
                }
                if(treeNode1.getRight()!=null){
                    ((LinkedList<BinaryTreeNode>) q2).add(treeNode1.getRight());
                }
            }
            q = q2;
        }

        int[] result = new int[q.size()];
        int i = 0;
        while(!q.isEmpty()){
            result[i++] = Integer.valueOf((String)q.poll().getVal());
        }

        return result;
    }

    static void fixTree(BinaryTreeNode<String> root){
        if(root == null){
            return;
        }

        if(root.getLeft()!=null && !root.getLeft().getVal().equals(")")){
            fixTree(root.getLeft());
        }else{
            root.setLeft(null);
        }

        if(root.getRight()!=null && !root.getRight().getVal().equals(")")){
            fixTree(root.getRight());
        }else{
            root.setRight(null);
        }

        return;
    }

    static int heightOfTree(BinaryTreeNode root) {
        if (null == root)
            return 0;
        int hLeftSub = heightOfTree(root.left);
        int hRightSub = heightOfTree(root.right);
        return Math.max(hLeftSub, hRightSub) + 1;
    }

    public static void main(String[] args) {
        String treeStr = "(2 (7 (2 () ()) (6 (5 () ()) (11 () ()))) (5 () (9 (4 () ()) ())))";
        System.out.println(Arrays.toString(treeBottom(treeStr)));
    }
}
