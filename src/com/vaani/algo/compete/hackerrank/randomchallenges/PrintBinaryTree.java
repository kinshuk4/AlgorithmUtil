package com.vaani.algo.compete.hackerrank.randomchallenges;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by mykola on 06.05.15.
 */
public class PrintBinaryTree {

    public static class TreeNode<E> {

        public E value;
        public TreeNode<E> left;
        public TreeNode<E> right;

        public TreeNode(E value) {
            this.value = value;
        }
    }

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
                System.out.print(n.value.toString());
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
