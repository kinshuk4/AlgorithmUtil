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


    /**
     * Tree =
     *         20
     *        /
     *       8
     *      / \
     *     4  12
     *       /  \
     *      10  14
     * @return
     */
    public static BinaryTreeNode<Integer> getBinaryTree(){
        BinaryTreeNode root = new BinaryTreeNode(20);
        root.left = new BinaryTreeNode(8);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(12);
        root.left.right.left = new BinaryTreeNode(10);
        root.left.right.right = new BinaryTreeNode(14);
        return root;
    }
}
