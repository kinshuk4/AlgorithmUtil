package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import static com.vaani.algo.compete.codility.TreeHeight.getTreeHeight;

public class TreeDiameter {
    /**
     * Time Complexity: O(n^2) - so bad
     */
    int getDiameterBad(BinaryTreeNode<Integer> root)
    {
        /* base case if tree is empty */
        if (root == null)
            return 0;

        /* get the height of left and right sub trees */
        int lheight = getTreeHeight(root.left);
        int rheight = getTreeHeight(root.right);

        /* get the diameter of left and right subtrees */
        int ldiameter = getDiameterBad(root.left);
        int rdiameter = getDiameterBad(root.right);

        /* Return max of following three
          1) Diameter of left subtree
         2) Diameter of right subtree
         3) Height of left subtree + height of right subtree + 1 */
        return Math.max(lheight + rheight + 1,
                Math.max(ldiameter, rdiameter));

    }

    private static int[] getDiameter(BinaryTreeNode root) {
        int[] result = new int[]{0,0};    //1st element: diameter, 2nd: height
        if (root == null)  {
            return result;
        }
        int[] leftResult = getDiameter(root.getLeft());
        int[] rightResult = getDiameter(root.getRight());

        int height = Math.max(leftResult[1], rightResult[1]) + 1;

        int rootDiameter = leftResult[1] + rightResult[1] + 1;
        int leftDiameter = leftResult[0];
        int rightDiameter = rightResult[0];

        result[0] = Math.max(rootDiameter, Math.max(leftDiameter, rightDiameter));
        result[1] = height;

        return result;
    }

    public static int getBinaryTreeDiameter(BinaryTreeNode root)
    {
        return getDiameter(root)[0];
    }
}
