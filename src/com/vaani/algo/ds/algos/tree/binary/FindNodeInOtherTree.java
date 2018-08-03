package com.vaani.algo.ds.algos.tree.binary;


import com.vaani.algo.ds.core.tree.TreeNodeWithParent;

import java.util.ArrayList;
import java.util.List;

/**
 * 然后出了一个编程题：有两个一样的树A和B，每个节点都有父指针，要求写一个函数，参数是A的一个子节点x，和B的根节点，要求返回B中对应x的那个节点。也就是说A的根节点未知。
 * <p>
 * 这题挺简单，所以我没怎么想就说了先找到A的根节点，然后同时对A和B做一个DFS或者BFS来找出B中对应x的节点。面试官说可以，让我写代码，写完以后分析了一下复杂度。
 * 然后就问有没有更好的方法，我马上就意识到不需要用DFS或者BFS，只需要在找A的根节点时记录下当前路径就行了（只需记录每个子结点是父节点的第几个孩子），然后按同样的路径扫一下B树。
 * 复杂度只有O（height），面试官好像还很满意。这轮面试没有一下就想到最优解，所以我还比较担心会不会结果negative。
 */
public class FindNodeInOtherTree {
    public static TreeNodeWithParent findNode(TreeNodeWithParent node, TreeNodeWithParent root) {
        List<Integer> path = new ArrayList<Integer>();
        while (node.parent != null) {
            if (node.parent.left == node) {
                path.add(0, 0);
            } else {
                path.add(0, 1);
            }
            node = node.parent;
        }

        for (int n : path) {
            if (n == 0) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNodeWithParent rootA = new TreeNodeWithParent(5);
        rootA.left = new TreeNodeWithParent(3);
        rootA.left.parent = rootA;
        rootA.left.left = new TreeNodeWithParent(2);
        rootA.left.left.parent = rootA.left;
        rootA.left.right = new TreeNodeWithParent(1);
        rootA.left.right.parent = rootA.left;
        rootA.left.right.right = new TreeNodeWithParent(6);
        rootA.left.right.right.parent = rootA.left.right;

        TreeNodeWithParent rootB = new TreeNodeWithParent(5);
        rootB.left = new TreeNodeWithParent(3);
        rootB.left.parent = rootB;
        rootB.left.left = new TreeNodeWithParent(2);
        rootB.left.left.parent = rootB.left;
        rootB.left.right = new TreeNodeWithParent(1);
        rootB.left.right.parent = rootB.left;
        rootB.left.right.right = new TreeNodeWithParent(6);
        rootB.left.right.right.parent = rootB.left.right;

        System.out.println(findNode(rootA.left.right.right, rootB).val);
    }
}

