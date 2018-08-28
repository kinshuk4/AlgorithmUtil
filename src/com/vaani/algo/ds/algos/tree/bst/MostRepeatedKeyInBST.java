package com.vaani.algo.ds.algos.tree.bst;

import java.util.HashMap;
import java.util.Map;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

/* class BinaryTreeNode { */
/*   int val; */
/*   BinaryTreeNode left, right; */
/*   public BinaryTreeNode(int val) { */
/*     this.val = val; */
/*   } */
/* } */

class BST {
    private BinaryTreeNode root;

    public void insert(int val) {
        if (root == null) {
            root = new BinaryTreeNode(val);
        } else {
            insert(root, val);
        }
    }

    private BinaryTreeNode insert(BinaryTreeNode<Integer> node, int val) {
        if (node == null) {
            return new BinaryTreeNode(val);
        } else if (node.val <= val) {
            node.right = insert(node.right, val);
        } else {
            node.left = insert(node.left, val);
        }
        return node;
    }

    public BinaryTreeNode root() {
        return root;
    }

}

public class MostRepeatedKeyInBST {

    public int mostRepeatedBST(BST bst) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        mostRepeatedBST(bst.root(), counts);
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            max = Math.max(max, entry.getValue());
        }
        return max;
    }

    private void mostRepeatedBST(BinaryTreeNode<Integer> node, Map<Integer, Integer> counts) {
        if (node == null) {
            return;
        } else {
            Integer count = counts.get(node.val);
            if (count == null) {
                counts.put(node.val, 1);
            } else {
                counts.put(node.val, count + 1);
            }
            mostRepeatedBST(node.left, counts);
            mostRepeatedBST(node.right, counts);
        }
    }

}

