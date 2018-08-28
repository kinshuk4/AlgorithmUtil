package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

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
    public static String writeBinaryTree(BinaryTreeNode head) {
        StringBuilder result = new StringBuilder();
        preorder(head, result);
        return result.toString();
    }

    private static void preorder(BinaryTreeNode head, StringBuilder result) {
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

    public static BinaryTreeNode readBinaryTree(String input) {
        String[] tokens = input.split(" ");
        return deserialize(tokens);
    }

    private static BinaryTreeNode deserialize(String[] tokens) {
        if (index >= tokens.length || tokens[index].equals("#")) {
            index++;
            return null;
        }
        BinaryTreeNode root = new BinaryTreeNode(Integer.parseInt(tokens[index++]));
        root.left = deserialize(tokens);
        root.right = deserialize(tokens);
        return root;
    }


    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(30);
        root.left = new BinaryTreeNode(10);
        root.right = new BinaryTreeNode(20);
        root.left.left = new BinaryTreeNode(50);
        root.right.left = new BinaryTreeNode(45);
        root.right.right = new BinaryTreeNode(35);
        System.out.println(writeBinaryTree(root));
        String input = "30 10 50 # # # 20 45 # # 35 # # ";
        System.out.println(readBinaryTree(input).detailedToString());
    }
}
