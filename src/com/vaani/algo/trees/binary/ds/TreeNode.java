package com.vaani.algo.trees.binary.ds;

public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int data;
    public boolean isVisited;
    
    public TreeNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.valueOf(this.data);
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
        return builder.append(String.valueOf(root.data)).toString();
    }
}
