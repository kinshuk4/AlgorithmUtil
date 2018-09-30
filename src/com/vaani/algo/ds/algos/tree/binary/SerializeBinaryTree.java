package com.vaani.algo.ds.algos.tree.binary;
import com.vaani.algo.ds.core.tree.*;

import java.util.LinkedList;

import static com.vaani.algo.ds.utils.BinaryTreeUtil.getBinaryTree;

public class SerializeBinaryTree {
    // Encodes a tree to a single string.
    public static String serialize(BinaryTreeNode root) {
        if(root==null){
            return "";
        }

        StringBuilder sb = new StringBuilder();

        LinkedList<BinaryTreeNode> queue = new LinkedList<>();

        queue.add(root);
        while(!queue.isEmpty()){
            BinaryTreeNode t = queue.poll();
            if(t!=null){
                sb.append(String.valueOf(t.val) + ",");
                queue.add(t.left);
                queue.add(t.right);
            }else{
                sb.append("#,");
            }
        }

        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(serialize(getBinaryTree()));

    }
}
