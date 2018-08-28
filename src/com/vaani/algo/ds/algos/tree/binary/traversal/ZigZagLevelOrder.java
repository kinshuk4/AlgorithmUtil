package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.Stack;

import static java.lang.System.out;

;

public class ZigZagLevelOrder {

    public static void ZigZagLevelOrder(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack1 = new Stack<BinaryTreeNode>();
        Stack<BinaryTreeNode> stack2 = new Stack<BinaryTreeNode>();
        Stack<BinaryTreeNode> cur_level, next_level, temp;

        boolean left2right = true;

        cur_level = stack1;
        next_level = stack2;

        // push root in stack
        cur_level.push(root);

        while (!cur_level.empty()) {
            BinaryTreeNode node = cur_level.firstElement(); //top();
            cur_level.pop();
            if (node != null) {
                out.println(node.val + " ");
                //cout << node.data << " ";
                if (left2right) {
                    //if left to right, start pushing from left
                    next_level.push(node.left);
                    next_level.push(node.right);
                } else {
                    next_level.push(node.right);
                    next_level.push(node.left);
                }
            }
            if (cur_level.empty()) {
                left2right = !left2right;
                // swap stacks
                temp = cur_level;
                cur_level = next_level;
                next_level = temp;
            }
        }
    }

}
