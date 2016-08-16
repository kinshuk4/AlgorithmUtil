package com.vaani.algo.ds.tree;

import com.vaani.algo.ds.core.TreeNode;

import java.util.*;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * For example:
 * Given binary tree {1,#,2,3},
 *  1
 *   \
 *    2
 *   /
 *  3
 *
 * return [3,2,1].
 *
 * Created by Xiaomeng on 8/7/2014.
 */
public class PostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;

        Stack<TreeNode> tmpStack = new Stack<TreeNode>();
        Stack<TreeNode> outStack = new Stack<TreeNode>();
        tmpStack.push(root);

        while(!tmpStack.isEmpty()){
            TreeNode node = tmpStack.pop();
            outStack.push(node);
            if(node.left != null) tmpStack.add(node.left);
            if(node.right != null) tmpStack.add(node.right);
        }

        while(!outStack.isEmpty()){
            result.add(outStack.pop().val);
        }
        return result;
    }

    public static void main(String[] args){
        PostorderTraversal test = new PostorderTraversal();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        System.out.println(test.postorderTraversal(root));
    }
}
