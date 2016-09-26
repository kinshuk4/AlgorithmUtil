package com.vaani.algo.compete.codeeval;

import java.io.File;
import java.util.Scanner;

public class LowestCommonAncestor {

    private static class Node {

        private int value;
        private Node left;
        private Node right;

        private Node(int value) {
            this.value = value;
        }
    }

    public static void main(String... args) throws Exception {
        Scanner sc = new Scanner(new File(args[0]));
        Node treeRoot = constructTree();
        while (sc.hasNextLine()) {
            String[] numbers = sc.nextLine().split(" ");
            int f = Integer.valueOf(numbers[0]);
            int s = Integer.valueOf(numbers[1]);
            Node lca = findLCA(treeRoot, f, s);
            if(lca != null){
                System.out.println(lca.value);
            }
        }
    }

    private static Node findLCA(Node root, int f, int s) {
        if (root == null) {
            return null;
        }
        if (root.value == f || root.value == s) {
            return root;
        }
        Node left = findLCA(root.left, f, s);
        Node right = findLCA(root.right, f, s);
        if (left != null && right != null) {
            return root;
        } else {
            return left != null ? left : right;
        }
    }

    private static Node constructTree() {
        Node root = new Node(30);
        root.left = new Node(8);
        root.right = new Node(52);
        root.left.left = new Node(3);
        root.left.right = new Node(20);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(29);
        return root;
    }

}
