package com.vaani.algo.compete.hackerrank.randomchallenges;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;


public class PrintBinaryTree {


    public static void main(String... args) {
        printLevelsComaSeparated(createTree());
    }

    private static <E> void printLevelsComaSeparated(BinaryTreeNode<E> root){
        if(root == null){
            return;
        }
        Queue<BinaryTreeNode> currentLevel = new LinkedList<>();
        currentLevel.offer(root);
        while (!currentLevel.isEmpty()) {
            Queue<BinaryTreeNode> nextLevel = new LinkedList<>();
            while (!currentLevel.isEmpty()) {
                BinaryTreeNode<E> n = currentLevel.remove();
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

    public static BinaryTreeNode<Character> createTree() {
        BinaryTreeNode<Character> root = new BinaryTreeNode<>('A');

        root.left = new BinaryTreeNode<>('B');
        root.right = new BinaryTreeNode<>('E');

        root.left.left = new BinaryTreeNode<>('C');
        root.left.right = new BinaryTreeNode<>('D');
        root.right.right = new BinaryTreeNode<>('E');

        root.right.right.right = new BinaryTreeNode<>('F');
        root.left.left.left = new BinaryTreeNode<>('G');
        return root;
    }

}
