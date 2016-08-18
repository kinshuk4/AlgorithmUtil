package com.vaani.algo.ds.tree.binary;

import com.vaani.algo.ds.core.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 *
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 * 1         3     3      2      1
 *  \       /     /      / \      \
 *   3     2     1      1   3      2
 *  /     /       \                 \
 * 2     1         2                 3
 *
 * Created by Xiaomeng on 8/14/2014.
 */
public class UniqueBinarySearchTrees2 {
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int a, int b){
        List<TreeNode> result= new ArrayList<TreeNode>();
        if(a == b){
            result.add(new TreeNode(a));
        }else if(a > b){
            result.add(null);
        }else{
            for(int i = a; i <= b; i++){
                List<TreeNode> leftTrees = generateTrees(a, i - 1);
                List<TreeNode> rightTrees = generateTrees(i + 1, b);
                for(TreeNode leftTree : leftTrees){
                    for(TreeNode rightTree : rightTrees){
                        TreeNode root = new TreeNode(i);
                        root.left = leftTree;
                        root.right = rightTree;
                        result.add(root);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        UniqueBinarySearchTrees2 test = new UniqueBinarySearchTrees2();
        for(TreeNode root : test.generateTrees(1)){
            root.display();
        }
    }
}
