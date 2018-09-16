package com.vaani.algo.compete.codesignal.interviewpractice.dfsbfs;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.*;

import static com.vaani.algo.ds.algos.tree.binary.MaximumRootToLeafSum.maximumPathSum;

/**
 * The string "user\f\tpictures\f\t\tphoto.png\f\t\tcamera\f\tdocuments\f\t\tlectures\f\t\t\tnotes.txt" represents:
 *
 * user
 *     pictures
 *         photo.png
 *         camera
 *     documents
 *         lectures
 *             notes.txt
 * The directory user contains two sub-directories pictures and documents. pictures contains a file photo.png and an empty second-level sub-directory camera. documents contains a second-level sub-directory lectures containing a file notes.txt.
 *
 * We want to find the longest (as determined by the number of characters) absolute path to a file within our system. For example, in the second example above, the longest absolute path is "user/documents/lectures/notes.txt", and its length is 33 (not including the double quotes).
 *
 * * user
 *  *     pictures
 *  *     documents
 *  *        notes.txt
 *
 *  user/documents/notes.txt = 24
 */
public class LongestTreePath {

    static int longestPath(String fileSystem) {
        BinaryTreeNode<Integer> root = null;

        int start = -1;
        int tabCount = 0;
        int prevTabCount = 0;
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        for(int i = 0; i < fileSystem.length(); i++){
            char c = fileSystem.charAt(i);
            if(c == '\f' || i == fileSystem.length()-1){

                BinaryTreeNode<Integer> item = null;
                if(i == fileSystem.length()-1){
                    item = new BinaryTreeNode<>(i - start);
                }else{
                    item = new BinaryTreeNode<>((i-1) - start);
                }
                if(root == null){
                    //consider the string till here
                    root = item;
                    stack.add(root);
                }
                else{

                    if(prevTabCount >= tabCount){
                       for(int k = 0; k < (prevTabCount - tabCount) + 1; k++){
                            stack.pop();
                        }

                    }
                    BinaryTreeNode<Integer> parent = stack.peek();

                    if(!parent.isVisited){
                        parent.isVisited = true;
                        parent.setVal(parent.val+1); // as parent has child, we have to add "/"
                    }

                    if(parent.left!=null){
                        parent.setRight(item);
                    }else{
                        parent.setLeft(item);
                    }

                    stack.add(item);
                }
                prevTabCount = tabCount;
                tabCount = 0;
            }else if(c == '\t'){
                tabCount++;
                start = i;
            }
        }

        return maximumPathSum(root);
    }

    public static void main(String[] args) {
//        System.out.println(longestPath("user\f\tpictures\f\tdocuments\f\t\tnotes.txt"));
//        System.out.println(longestPath("user\f\tpictures\f\t\tphoto.png\f\t\tcamera\f\tdocuments\f\t\tlectures\f\t\t\tnotes.txt"));
        System.out.println(longestPath("a"));

    }
}
