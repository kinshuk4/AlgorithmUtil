package com.vaani.algo.ds.algos.convert;

/**
 * Created by kchandra on 21/08/16.
 * http://www.geeksforgeeks.org/in-place-convert-a-given-binary-tree-to-doubly-linked-list/
 * http://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/
 */
public class ConvertBinaryTreeToDLL {

//    BinaryTreeNode bintree2listUtil(DLLNode node)
//    {
//        // Base case
//        if (node == null)
//            return null;
//
//        // Convert the left subtree and link to root
//        if (node.prev != null)
//        {
//            // Convert the left subtree
//            BinaryTreeNode left = bintree2listUtil(node.prev);
//
//            // Find inorder predecessor. After this loop, left
//            // will point to the inorder predecessor
//            for (; left.right != null; left = left.right);
//
//            // Make root as next of the predecessor
//            left.right = DLLNode.convertDLLNodeToTreeNode(node);
//
//            // Make predecssor as previous of root
//            node.prev = DLLNode.convertTreeNodeToDLLNode(node);;
//        }
//
//        // Convert the right subtree and link to root
//        if (node.right != null)
//        {
//            // Convert the right subtree
//            ListNode right = bintree2listUtil(node.right);
//
//            // Find inorder successor. After this loop, right
//            // will point to the inorder successor
//            for (; right.left != null; right = right.left);
//
//            // Make root as previous of successor
//            right.left = node;
//
//            // Make successor as next of root
//            node.right = right;
//        }
//
//        return node;
//    }
//
//    // The main function that first calls bintree2listUtil(), then follows
//    // step 3 of the above algorithm
//
//    BinaryTreeNode bintree2list(DLLNode node)
//    {
//        // Base case
//        if (node == null)
//            return null;
//
//        // Convert to DLL using bintree2listUtil()
//        BinaryTreeNode treeNode = bintree2listUtil(node);
//
//        // bintree2listUtil() returns root node of the converted
//        // DLL.  We need pointer to the leftmost node which is
//        // head of the constructed DLL, so move to the leftmost node
//        while (node.left != null)
//            node = node.left;
//
//        return node;
//    }
}
