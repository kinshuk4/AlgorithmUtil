package com.vaani.algo.compete.codesignal.challenge;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.*;

/***
 * The sum of a subtree is the sum of all the node values in that subtree, including its root.
 *
 * Given a binary tree of integers, find the most frequent sum (or sums) of its subtrees.
 *
 * Example
 *
 * For
 * t = {
 *     "value": 1,
 *     "left": {
 *         "value": 2,
 *         "left": null,
 *         "right": null
 *     },
 *     "right": {
 *         "value": 3,
 *         "left": null,
 *         "right": null
 *     }
 * }
 * the output should be
 * mostFrequentSum(t) = [2, 3, 6].
 * 1st example
 *
 * Since all the sum values in this tree occur only once, return all of them in ascending order.
 *
 * For
 * t = {
 *     "value": -2,
 *     "left": {
 *         "value": -3,
 *         "left": null,
 *         "right": null
 *     },
 *     "right": {
 *         "value": 2,
 *         "left": null,
 *         "right": null
 *     }
 * }
 * the output should be
 * mostFrequentSum(t) = [-3].
 */
public class MostFrequentSumInTree {
    static int[] mostFrequentSum(BinaryTreeNode<Integer> t) {
        if(t==null){
            return new int[]{};
        }

        HashMap<Integer, Integer> sumFrequency = new HashMap<>();
        mostFrequentSumHelper(t, sumFrequency);
        int max = 0;
        for (int count : sumFrequency.values()) {
            max = Math.max(max, count);
        }

        final int maxFinal = max;

//        List<Integer> result = sumFrequency.entrySet().stream().filter(x -> x.getValue().equals(maxFinal))
//                ;

        List<Integer> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : sumFrequency.entrySet()) {
            if (entry.getValue() == max) {
                resultList.add(entry.getKey());
            }
        }

        int[] result = new int[resultList.size()];
        for (int i = 0; i <resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;

    }

    private static int mostFrequentSumHelper(BinaryTreeNode<Integer> root, HashMap<Integer, Integer> sumFrequency) {
        if (root.left == null && root.right == null) {
            sumFrequency.merge(root.val, 1, (val, acc) -> val + acc);
            return root.val;
        }
        int left = 0;
        if (root.left != null) {
            left = mostFrequentSumHelper(root.left, sumFrequency);
        }
        int right = 0;
        if (root.right != null) {
            right = mostFrequentSumHelper(root.right, sumFrequency);
        }
        sumFrequency.merge(root.val + left + right, 1, (val, acc) -> val + acc);
        return root.val + left + right;

    }


}
