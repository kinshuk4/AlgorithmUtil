package com.vaani.algo.compete.codility;


import com.vaani.algo.ds.core.tree.TreeNode;

public class TreeHeight {
	public static int getTreeHeight(TreeNode<Integer> T) {
		if (T == null) {
			return -1;
		}
		return 1 + Math.max(getTreeHeight(T.left), getTreeHeight(T.right));
	}
}
