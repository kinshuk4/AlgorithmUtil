package com.vaani.algo.ds.tree;

import com.vaani.algo.ds.core.TreeNode;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * Created by Xiaomeng on 8/12/2014.
 */
public class ConstructFromInorderAndPostorder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTree(int[] inorder, int iStart, int iEnd, int[] postorder, int pStart, int pEnd){
        if(iStart > iEnd || pStart > pEnd) return null;

        int rootVal = postorder[pEnd];
        int index = iStart;
        while(index <= iEnd && inorder[index] != rootVal) index++;

        TreeNode root = new TreeNode(rootVal);

        int leftLen = index - iStart;

        root.left = buildTree(inorder, iStart, index - 1, postorder, pStart, pStart + leftLen - 1);
        root.right = buildTree(inorder, index + 1, iEnd, postorder, pStart + leftLen, pEnd - 1);

        return root;
    }

    public static void main(String[] args){
        int[] inorder = {4,2,5,1,6,3,7};
        int[] postorder = {4,5,2,6,7,3,1};

        ConstructFromInorderAndPostorder test = new ConstructFromInorderAndPostorder();
        test.buildTree(inorder, postorder).display();
    }
}
