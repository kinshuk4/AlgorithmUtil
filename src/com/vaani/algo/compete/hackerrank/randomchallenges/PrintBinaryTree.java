package com.vaani.algo.compete.hackerrank.randomchallenges;

import com.vaani.algo.ds.core.TreeNode;

import java.util.LinkedList;
import java.util.Queue;


public class PrintBinaryTree {


    public static void main(String... args) {
        printLevelsComaSeparated(createTree());
    }

    private static <E> void printLevelsComaSeparated(TreeNode<E> root){
        if(root == null){
            return;
        }
        Queue<TreeNode> currentLevel = new LinkedList<>();
        currentLevel.offer(root);
        while (!currentLevel.isEmpty()) {
            Queue<TreeNode> nextLevel = new LinkedList<>();
            while (!currentLevel.isEmpty()) {
                TreeNode<E> n = currentLevel.remove();
                System.out.print(n.getVal().toString());
                if(!currentLevel.isEmpty()){
                    System.out.print(',');
                }
                if(n.left != null) nextLevel.offer(n.left);
                if(n.right != null) nextLevel.offer(n.right);
            }
            if(!nextLevel.isEmpty()){
                System.out.println();
            }
            currentLevel = nextLevel;
        }
    }

    public static TreeNode<Character> createTree() {
        TreeNode<Character> root = new TreeNode<>('A');

        root.left = new TreeNode<>('B');
        root.right = new TreeNode<>('E');

        root.left.left = new TreeNode<>('C');
        root.left.right = new TreeNode<>('D');
        root.right.right = new TreeNode<>('E');

        root.right.right.right = new TreeNode<>('F');
        root.left.left.left = new TreeNode<>('G');
        return root;
    }

}
