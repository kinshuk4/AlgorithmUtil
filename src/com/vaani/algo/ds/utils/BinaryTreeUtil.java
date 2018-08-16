package com.vaani.algo.ds.utils;

import com.vaani.algo.ds.core.tree.TreeNode;

public class BinaryTreeUtil {

    public static TreeNode<Integer> arrayToBinaryTree(Integer[] arr){
        TreeNode<Integer> root = new TreeNode<>(arr[0]);
        TreeNode<Integer>[] treeNodesArr = (TreeNode<Integer>[]) new TreeNode[arr.length];
        treeNodesArr[0] = root;
        for(int i =  1; i<arr.length; i++){
            TreeNode<Integer> item = new TreeNode<>(arr[i]);
            treeNodesArr[i] = item;
            int parent = (i-1) /2 ;
            if((i-1)%2 == 0){
                treeNodesArr[parent].left = item;
            }else{
                treeNodesArr[parent].right = item;
            }

        }

        return root;
    }
}
