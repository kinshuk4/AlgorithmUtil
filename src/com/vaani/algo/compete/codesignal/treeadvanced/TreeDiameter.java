package com.vaani.algo.compete.codesignal.treeadvanced;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.*;

import static com.vaani.algo.ds.algos.tree.binary.TreeDiameter.getBinaryTreeDiameter;

/**
 * f course a tree height can be calculated as the length of the longest path in it (it is also called tree diameter). So, now you have a task you need to solve, so go ahead!
 *
 * Example
 *
 * For n = 10 and
 *
 * tree = [[2, 5], [5, 7], [5, 1], [1, 9], [1, 0], [7, 6], [6, 3], [3, 8], [8, 4]]
 * the output should be treeDiameter(n, tree) = 7.
 *
 *
 *
 * The longest path is the path from vertex 4 to one vertex 9 or 0.
 *
 * Input/Output
 *
 * [execution time limit] 3 seconds (java)
 *
 * [input] integer n
 *
 * The number of vertices in the structure you drew in your notebook.
 *
 * Guaranteed constraints:
 * 1 ≤ n ≤ 104.
 *
 * [input] array.array.integer tree
 *
 * Edges of the tree. tree[i] for each valid i contains two elements and represents an edge that connects tree[i][0] and tree[i][1].
 * It is guaranteed that given graph is a tree, i.e. it is connected and has no cycles.
 *
 * Guaranteed constraints:
 * tree.length = n - 1,
 * tree[i].length = 2,
 * 0 ≤ tree[i][j] < n.
 */
public class TreeDiameter {
    static int treeDiameter(int n, int[][] tree) {
        Map<Integer, BinaryTreeNode<Integer>> map = new HashMap<>();
        BinaryTreeNode<Integer> root = null;
        for(int[] edge: tree){
            BinaryTreeNode<Integer> node = new BinaryTreeNode<>(edge[1]);
            map.put(edge[1], node);
            if(map.containsKey(edge[0])){
                BinaryTreeNode<Integer> parent = map.get(edge[0]);
                if(parent.left!=null){
                    parent.right = node;
                }else{
                    parent.left = node;
                }
            }else{
                BinaryTreeNode<Integer> parent = new BinaryTreeNode<>(edge[0]);
                if(root == null){
                    root = parent;
                }
                map.put(edge[0], node);
                parent.setLeft(node);
            }

        }

        return getBinaryTreeDiameter(root);


    }

    public static void main(String[] args) {
        int[][] edges = {
                {2, 5},
                {5, 7},
                {5, 1},
                {1, 9},
                {1, 0},
                {7, 6},
                {6, 3},
                {3, 8},
                {8, 4}
        };
        System.out.println(treeDiameter(10, edges));
    }

}
