package com.vaani.algo.paradigm.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Use an array to represent a river. 0 means water and 1 means stone.
 * Start point is R[0], and initial speed is 1, every step you can decide to keep current speed or increase by 1.
 * For jump over the river, you only can stay on stone for every step.
 * <p>
 * Question is what's the minimum step you have to take for jumping over the river.
 * <p>
 * 给定数组为R=[1,1,1,0,1,1,0,0]，最少3步能过河：
 * <p>
 * 第一步先提速到2，再跳到R[2]；
 * 第二步先提速到3，再跳到R[5]；
 * 第三步保持速度3，跳出数组范围，成功过河。
 */
public class JumpRiver {
    public static int getMinSteps(int[] arr) {
        int len = arr.length;
        int speed = 1;
        int i = 0;
        int step = 0;
        while (i < len) {
            if (i + speed + 1 >= len) {
                step++;
                return step;
            } else {
                speed += arr[i + speed + 1] == 1 ? 1 : 0;
                i += speed;
                step++;
            }
        }
        return step;
    }

    /**
     * https://sites.google.com/site/codingbughunter/algorithm-question-discuss
     */
    public static int jump(int[] river) {
        List<Map<Integer, Integer>> states = new ArrayList<Map<Integer, Integer>>();
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < river.length; i++) {
            states.add(new HashMap<Integer, Integer>());
        }
        states.get(0).put(1, 0);
        for (int i = 0; i < river.length; i++) {
            for (int speed : states.get(i).keySet()) {
                for (int j = 0; j < 2; j++) {
                    int nextStep = speed + i + j;
                    int step = states.get(i).get(speed) + 1;

                    if (nextStep >= river.length) {
                        min = Math.min(min, step);
                    } else if (river[nextStep] == 1) {
                        states.get(nextStep).put(speed + j, step);
                    }
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 0, 1, 1, 1, 1, 0, 0};
        //System.out.print(getMinSteps(arr));
        System.out.print(jump(arr));
    }
}
