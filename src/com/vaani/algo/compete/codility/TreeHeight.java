package com.vaani.algo.compete.codility;


import com.vaani.algo.ds.core.tree.BinaryTreeNode;

public class TreeHeight {
	public static int getTreeHeight(BinaryTreeNode<Integer> T) {
		if (T == null) {
			return -1;
		}
		return 1 + Math.max(getTreeHeight(T.left), getTreeHeight(T.right));
	}
}
