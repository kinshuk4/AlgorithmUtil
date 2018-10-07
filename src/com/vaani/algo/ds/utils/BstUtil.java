package com.vaani.algo.ds.utils;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;
import com.vaani.algo.ds.core.tree.BstNode;

public class BstUtil<T> {
    private BstUtil() {

    }

    public static <T extends Comparable> BstNode<T> findLowestCommonAncestor(BstNode<T> node, T num1, T num2) {
        while (node != null) {
            if (node.val.compareTo(num1) > 0 && node.val.compareTo(num2) > 0)
                node = node.left;
            else if (node.val.compareTo(num1) < 0 && node.val.compareTo(num2) < 0)
                node = node.right;
            else
                return node;
        }
        return null;
    }


    public static <T extends Comparable> BinaryTreeNode<T> search(BinaryTreeNode<T> root, T key) {
        // Base Cases: root is null or key is present at root
        if (root == null || root.val == key)
            return root;

        // val is greater than root's key
        if (root.val.compareTo(key) > 0)
            return search(root.left, key);

        // val is less than root's key
        return search(root.right, key);
    }

    public static <T extends Comparable> BinaryTreeNode<T>[] searchWithParent(BinaryTreeNode<T> root, T key, BinaryTreeNode<T> parent) {
        // Base Cases: root is null or key is present at root
        if (root == null || root.val == key)
            return new BinaryTreeNode[]{root, parent};
        parent = root;
        // val is greater than root's key
        if (root.val.compareTo(key) > 0)
            return searchWithParent(root.left, key, parent);

        // val is less than root's key
        return searchWithParent(root.right, key, parent);
    }


    public static <T extends Comparable> BinaryTreeNode<T> findMaxInBst(BinaryTreeNode<T> root) {
        BinaryTreeNode<T> x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public static <T extends Comparable> BinaryTreeNode<T> findMin(BinaryTreeNode<T> root) {
        BinaryTreeNode<T> x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public static <T extends Comparable> BinaryTreeNode<T> deleteMaxInBst(BinaryTreeNode<T> root) {
        BinaryTreeNode<T> prev = null;
        BinaryTreeNode<T> curr = root;
        while (curr.right != null) {
            prev = curr;
            curr = curr.right;
        }
        if (curr != root) {
            prev.right = curr.left;
            return root;
        }
        return curr.left;
    }

    public static <T extends Comparable> boolean find(BinaryTreeNode<T> root, int id) {
        BinaryTreeNode<T> current = root;
        while (current != null) {
            if (current.val.equals(id)) {
                return true;
            } else if (current.val.compareTo(id) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public static <T extends Comparable> boolean delete(BinaryTreeNode<T> root, T id) {
        BinaryTreeNode<T> parent = root;
        BinaryTreeNode<T> current = root;
        boolean isLeftChild = false;
        while (!current.val.equals(id)) {
            parent = current;
            if (current.val.compareTo(id) > 0) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                return false;
            }
        }
        //if i am here that means we have found the node
        //Case 1: if node to be deleted has no children
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild == true) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        //Case 2 : if node to be deleted has only one child
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.left != null && current.right != null) {

            //now we have found the minimum element in the right sub tree
            BinaryTreeNode<T> successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }

    public static <T extends Comparable> BinaryTreeNode getSuccessor(BinaryTreeNode<T> deleleNode) {
        BinaryTreeNode<T> successsor = null;
        BinaryTreeNode<T> successsorParent = null;
        BinaryTreeNode<T> current = deleleNode.right;
        while (current != null) {
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }
        //check if successor has the right child, it cannot have left child for sure
        // if it does have the right child, add it to the left of successorParent.
//        successsorParent
        if (successsor != deleleNode.right) {
            successsorParent.left = successsor.right;
            successsor.right = deleleNode.right;
        }
        return successsor;
    }

    public static <T extends Comparable> void insert(BinaryTreeNode<T> root, T id) {
        BinaryTreeNode<T> newNode = new BinaryTreeNode<T>(id);
        if (root == null) {
            root = newNode;
            return;
        }
        BinaryTreeNode<T> current = root;
        BinaryTreeNode<T> parent = null;
        while (true) {
            parent = current;
            if (id.compareTo(current.val) <= 0) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    public static <T extends Comparable> boolean insertRecursive(BinaryTreeNode<T> root, T n) {

        if (n == null)
            return false;
        if (root.val.compareTo(n) <= 0) {
            if (root.left == null) {
                BinaryTreeNode<T> node = new BinaryTreeNode<>(n);
                root.left = node;
                return true;
            } else {
                return insertRecursive(root.left, n);
            }
        } else {
            if (root.right == null) {
                BinaryTreeNode<T> node = new BinaryTreeNode<>(n);
                root.right = node;
                return true;
            } else {
                return insertRecursive(root.right, n);
            }
        }
    }

    public void display(BinaryTreeNode<T> root) {
        if (root != null) {
            display(root.left);
            System.out.print(" " + root.val);
            display(root.right);
        }
    }


}
