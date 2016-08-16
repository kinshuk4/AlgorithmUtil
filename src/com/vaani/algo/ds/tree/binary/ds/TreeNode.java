package com.vaani.algo.ds.tree.binary.ds;

public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int val;
    public boolean isVisited;
    
    public TreeNode(int data) {
        this.val = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public String toString() {
        return String.valueOf(this.val);
}
    
    public String detailedToString(){
    	String result = detailedToStringHelper(this);
    	return result;
    }
    
    //Using the logic from here : http://stackoverflow.com/questions/20489834/binary-search-tree-recursive-tostring
    public String detailedToStringHelper(TreeNode root){
        StringBuilder builder = new StringBuilder();
        if (root == null)
            return "";
        builder.append(detailedToStringHelper(root.left));
        builder.append(detailedToStringHelper(root.right));
        return builder.append(String.valueOf(root.val)).toString();
    }

    public boolean insertForBst(TreeNode n){
        if (n == null)
            return false;
        if (this.val >= n.val) {
            if (this.left == null)
                this.left = n;
            else
                this.left.insertForBst(n);
        } else {
            if (this.right == null)
                this.right = n;
            else
                this.right.insertForBst(n);
        }
        return true;

    }
    
    public int size() {
        int size = 0;
        if (left != null) {
            size += left.size();
        }
        if (right != null) {
            size += right.size();
        }
        return size + 1;
    }


}
