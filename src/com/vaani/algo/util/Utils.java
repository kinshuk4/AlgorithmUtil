package com.vaani.algo.util;

import com.vaani.algo.ds.core.list.ListNode;

public class Utils {

    public static void printMatrix(double[][] matrix) {
        System.out.println(matrix.length);

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                System.out.print(matrix[i][j] + ' ');
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printMatrix(int[][] matrix) {
        System.out.println(matrix.length);

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printListNode(ListNode head) {
        System.out.printf(String.format("%s\n", listNodeToString(head)));
    }

    public static String listNodeToString(ListNode head) {
        ListNode cur = head;
        StringBuffer sb = new StringBuffer();
        sb.append('[');
        while (cur != null) {
            sb.append(cur.val);
            sb.append(' ');
            cur = cur.next;
        }
        sb.append("]");
        return sb.toString();
    }

}
