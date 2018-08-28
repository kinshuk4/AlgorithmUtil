package com.vaani.algo.ds.utils;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

public class BinaryTreeUtil {

    public static BinaryTreeNode<Integer> arrayToBinaryTree(Integer[] arr){
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(arr[0]);
        BinaryTreeNode<Integer>[] treeNodesArr = (BinaryTreeNode<Integer>[]) new BinaryTreeNode[arr.length];
        treeNodesArr[0] = root;
        for(int i =  1; i<arr.length; i++){
            BinaryTreeNode<Integer> item = new BinaryTreeNode<>(arr[i]);
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
