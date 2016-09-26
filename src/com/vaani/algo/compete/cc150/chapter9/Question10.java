package com.vaani.algo.compete.cc150.chapter9;

import java.util.List;

/**
 * You have a stack of n boxes, with widths w_i, heights h_i and depths d_i. The
 * boxes cannot be rotated and can only be stacked on top of one another if each
 * box in the stack is strictly larger than the box above it in width, height,
 * and depth. Implement a method to build the tallest stack possible, where the
 * height of a stack is the sum of the heights of each box.
 */
public class Question10 {

    public long talleast(List<Box> boxes) {
        // write implementaion here
        boolean[] used = new boolean[boxes.size()];
        int prevWidth = Integer.MAX_VALUE;
        int prevDepth = Integer.MAX_VALUE;
        return add(used, boxes, prevWidth, prevDepth);
    }

    private long add(boolean[] used, List<Box> boxes,
                     int prevWidth, int prevDepth) {
        long maxHeight = 0;

        for (int i = 0; i < boxes.size(); ++i) {
            Box box = boxes.get(i);
            if (!used[i]) {
                used[i] = true;
                // width as height
                if (prevWidth >= box.height && prevDepth >= box.depth
                        || prevWidth >= box.depth && prevDepth >= box.height) {
                    long height = add(used, boxes, box.height, box.depth);
                    if (height + box.width > maxHeight) {
                        maxHeight = height + box.width;
                    }
                }
                // depth as height
                if (prevWidth >= box.width && prevDepth >= box.height
                        || prevWidth >= box.height && prevDepth >= box.width) {
                    long height = add(used, boxes, box.width, box.height);
                    if (height + box.depth > maxHeight) {
                        maxHeight = height + box.depth;
                    }
                }
                // height as height
                if (prevWidth >= box.width && prevDepth >= box.depth
                        || prevWidth >= box.depth && prevDepth >= box.width) {
                    long height = add(used, boxes, box.width, box.depth);
                    if (height + box.height > maxHeight) {
                        maxHeight = height + box.height;
                    }
                }
                used[i] = false;
            }
        }
        return maxHeight;
    }

    public static class Box {
        public int width;
        public int depth;
        public int height;

        public Box(int width, int depth, int height) {
            this.width = width;
            this.depth = depth;
            this.height = height;
        }
    }

}
