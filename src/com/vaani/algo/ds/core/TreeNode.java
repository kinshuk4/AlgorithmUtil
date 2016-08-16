package com.vaani.algo.ds.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Xiaomeng on 5/3/2014.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int n){
        this.val = n;
    }

    public void display(){
        for(List<Integer> level : levelOrder(this)){
            System.out.println(level);
        }
    }

    private List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null) return result;

        Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
        Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
        queue1.add(root);
        List<Integer> level = new ArrayList<Integer>();

        while(!queue1.isEmpty()){
            TreeNode node = queue1.poll();
            level.add(node.val);
            if(node.left != null) queue2.add(node.left);
            if(node.right != null) queue2.add(node.right);
            if(queue1.isEmpty()){
                result.add(level);
                level = new ArrayList<Integer>();
                queue1.addAll(queue2);
                queue2.clear();
            }
        }
        return result;
    }


}
