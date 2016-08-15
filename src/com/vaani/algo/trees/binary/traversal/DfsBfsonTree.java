package com.vaani.algo.trees.binary.traversal;



import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.vaani.algo.trees.binary.ds.TreeNode;
//https://github.com/paopao2/Algorithm-Practice/blob/master/Breadth:Depth%20First%20Search.java
public class DfsBfsonTree {
	static Stack<TreeNode> stack = new Stack<>();

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode l1 = new TreeNode(3);
		TreeNode l2 = new TreeNode(9);
		TreeNode l3 = new TreeNode(11);
		TreeNode r1 = new TreeNode(5);
		TreeNode r2 = new TreeNode(12);
		TreeNode r3 = new TreeNode(13);
		root.left = l1;
		root.right = r1;
		l1.left = l2;
		l2.left = l3;
		l3.right = r2;
		l3.left = new TreeNode(14);
		r2.right = r3;

		TreeNode r4 = new TreeNode(88);
		r1.right = r4;
		dfsRecursive(root);
	}

	public static void dfsRecursive(TreeNode root) {
		if (null == root) {
			return;
		}
		root.isVisited = true;
		stack.push(root);

		if (!stack.isEmpty()) {
			TreeNode pop = stack.pop();
			System.out.print(pop.val + "\t");
			if (pop.left != null && !pop.left.isVisited) {
				dfsRecursive(pop.left);
			}
			if (pop.right != null && !pop.right.isVisited) {
				dfsRecursive(pop.right);
			}
		}
	}
	
	public void dfs(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		root.isVisited = true;
		System.out.println(root);
		while(!stack.isEmpty()) {
			TreeNode node = (TreeNode) stack.peek();
			TreeNode child = getUnvisitedChildNode(node);
			if(child != null) {
				child.isVisited = true;
				System.out.println(child);
				stack.push(child);
			}
			else {
				stack.pop();
			}
		}
		
	}

	private TreeNode getUnvisitedChildNode(TreeNode node) {
		if(node.left!=null && !node.left.isVisited){
			return node.left;
		}else if(node.right!=null  && !node.left.isVisited){
			return node.right;
		}else{
			return null;
		}
			
		
	}
	
	public void bfs(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		System.out.println(root);
		root.isVisited = true;
		while(!queue.isEmpty()) {
			TreeNode node =  queue.remove();
			
			TreeNode child=getUnvisitedChildNode(node);
			
			while (child!=null) {
				child.isVisited=true;
				System.out.println(child);
				queue.add(child);
			}
			
		}
		
}

	
	
}
