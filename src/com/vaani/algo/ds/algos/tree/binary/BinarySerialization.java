package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.TreeNode;

/**
 * Design an algorithm and write code to serialize and deserialize a binary tree.
 * Writing the tree to a file is called ‘serialization’ and reading back from the file to reconstruct the exact same binary tree is ‘deserialization’
 * <p>
 * Reference: http://www.geeksforgeeks.org/serialize-deserialize-binary-tree/
 */
public class BinarySerialization {
    private static int index = 0;

    /**
     * 30
     * /    \
     * 10    20
     * /     /  \
     * 50    45  35
     * <p>
     * Serialized string: 30 10 50 # # # 20 45 # # 35 # #
     */
    public static String writeBinaryTree(TreeNode head) {
        StringBuilder result = new StringBuilder();
        preorder(head, result);
        return result.toString();
    }

    private static void preorder(TreeNode head, StringBuilder result) {
        if (head == null) {
            result.append("#");
            result.append(" ");
            return;
        }
        result.append(head.val);
        result.append(" ");
        preorder(head.left, result);
        preorder(head.right, result);
    }

    public static TreeNode readBinaryTree(String input) {
        String[] tokens = input.split(" ");
        return deserialize(tokens);
    }

    private static TreeNode deserialize(String[] tokens) {
        if (index >= tokens.length || tokens[index].equals("#")) {
            index++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(tokens[index++]));
        root.left = deserialize(tokens);
        root.right = deserialize(tokens);
        return root;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(30);
        root.left = new TreeNode(10);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(50);
        root.right.left = new TreeNode(45);
        root.right.right = new TreeNode(35);
        System.out.println(writeBinaryTree(root));
        String input = "30 10 50 # # # 20 45 # # 35 # # ";
        System.out.println(readBinaryTree(input).detailedToString());
    }
}
