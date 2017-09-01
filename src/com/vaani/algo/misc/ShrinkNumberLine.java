package com.vaani.algo.misc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;

/**
 * Shrinking Number Line
 * <p>
 * We have an array of n integers, point, and an integer, k. We can perform either of the following operations once for each point[i] in point:
 * <p>
 * Increment point[i] by k.
 * Decrement point[i] by k.
 * We want to perform an operation on each point[i] such that the difference between the maximum and minimum values in the array after performing all operations is minimal.
 * <p>
 * Complete the minimize function in the from the provided code. It has two parameters:
 * <p>
 * An array of n integers, point, where the value of each element corresponds to a point on the number line.
 * An integer, k, denoting the value that each element must either be incremented or decremented by.
 * The function must perform one operation on each pointi such that the absolute difference between the maximum and minimum values in the modified point array is minimal, then return an integer denoting the minimal difference between the modified array’s new maximum and minimum values.
 * <p>
 * Input Format
 * <p>
 * The provided code reads the following input from stdin and passes it to the function:
 * <p>
 * The first line contains an integer, n, denoting the number of elements in the point array.
 * <p>
 * Each line i of the n subsequent lines (where 0 ≤ i < n) contains an integer describing point[i].
 * <p>
 * The last line contains an integer, k.
 * <p>
 * Constraints
 * <p>
 * 1 ≤ n ≤ 100
 * 1 ≤ k ≤ 10^5
 * -10^5 ≤ point[i] ≤ 10^5
 * Output Format
 * <p>
 * The function must perform one operation on each pointi such that the absolute difference between the maximum and minimum values in the modified point array is minimal, then return an integer denoting this minimal difference between the array’s new maximum and minimum values. This is printed to stdout by the provided code.
 * <p>
 * Sample Input 0
 * <p>
 * 3
 * -3
 * 0
 * 1
 * 3
 * Sample Output 0
 * <p>
 * 3
 * Explanation 0
 * <p>
 * We have point = [-3, 0, 1] and k = 3. If we add k to the first element and subtract it from the latter two elements, we get point = [-3 + 3, 0 − 3, 1 − 3] = [0, -3, -2]. We then take the absolute difference between the maximum and minimum values in point, which is |0 − -3| = 3, and return 3 as our answer.
 * <p>
 * Sample Input 1
 * <p>
 * 3
 * 4
 * 7
 * -7
 * 5
 * Sample Output 1
 * <p>
 * 4
 * Explanation 1
 * <p>
 * We have point = [4, 7, -7] and k = 5. If we subtract k from the first and second elements and add k to the third element, we get point = [4 − 5, 7 − 5, -7 + 5] = [-1, 2, -2]. We then take the absolute difference between the maximum and minimum values in point, which is |-2 − 2| = 4, and return 4 as our answer.
 * <p>
 * Sample Input 2
 * <p>
 * 2
 * -100000
 * 100000
 * 100000
 * Sample Output 2
 * <p>
 * 0
 * Explanation 2
 * <p>
 * We have point = [-100000, 100000] and k = 100000. If we add k to the first element and subtract k from the second element, we get point = [-100000 + 100000, 100000 − 100000] = [0, 0]. We then take the absolute difference between the two values in point, which is |0 − 0| = 0 as there are only two elements, and return 0 as our answer.
 */
public class ShrinkNumberLine {

    public static void main(String[] args) {

        //Integer[] point = {-3, 0, 1};
        //Integer k = 3;
        Integer[] point = {4, 7, 0, 0, -7};
        Integer k = 5;

		/*Integer[] point;
		Integer k;
		try (Scanner scanner = new Scanner(System.in)) {
			point = readPoint(scanner);
			k = readKValue(scanner);
			Arrays.stream(point).forEach(System.out::println);
			System.out.println("k = " + k);
		}*/

        System.out.println("Minimal difference is = " + calculateMinDifference(point, k));
    }

    private static Integer[] readPoint(Scanner scanner) {
        System.out.println("Array size = ");
        Integer size = Integer.valueOf(scanner.next());
        Integer[] point = new Integer[size];
        for (int i = 0; i < size; i++) {
            System.out.println(String.format("point[%d] = ", i));
            point[i] = Integer.valueOf(scanner.next());
        }
        return point;
    }

    private static Integer readKValue(Scanner scanner) {
        System.out.println("k = ");
        return Integer.valueOf(scanner.next());
    }

    private static Integer calculateMinDifference(Integer[] point, Integer k) {
        // WITH CUSTOM COLLECTOR
		/*Stats<Integer> stats = Arrays.stream(point)
				.map(p -> {
					if (p < 0) return p + k;
					if (p > 0) return p - k;
					return -k;
					// if (p == 0) trebuie comparat cu min si max ?!
				})
				.collect(Stats.collector());
			return Math.abs(stats.max() - stats.min());*/

        // WITH REDUCE OPERATION
        Optional<Integer[]> stats = Arrays.stream(point)
                .map(p -> {
                    if (p < 0) return p + k;
                    if (p > 0) return p - k;
                    return -k;
                    // if (p == 0) trebuie comparat cu min si max ?!
                })
                .map(i -> new Integer[]{i /* "min"*/, i  /*"max"*/})
                .reduce((firstPair, secondPair) -> new Integer[]{
                        // The min of the min elements.
                        firstPair[0].compareTo(secondPair[0]) < 0 ? firstPair[0] : secondPair[0],
                        // The max of the max elements.
                        firstPair[1].compareTo(secondPair[1]) > 0 ? firstPair[1] : secondPair[1]
                });

        return Math.abs(stats.get()[1] - stats.get()[0]);
    }
}


class Stats<T> {

    final Comparator<? super T> comparator;

    private int count;
    private T min;
    private T max;

    public Stats(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public int count() {
        return count;
    }

    public T min() {
        return min;
    }

    public T max() {
        return max;
    }

    private void accept(T val) {
        if (count == 0)
            min = max = val;
        else if (comparator.compare(val, min) < 0)
            min = val;
        else if (comparator.compare(val, max) > 0)
            max = val;

        count++;
    }

    private Stats<T> combine(Stats<T> that) {
        if (this.count == 0) return that;
        if (that.count == 0) return this;

        this.count += that.count;
        if (comparator.compare(that.min, this.min) < 0)
            this.min = that.min;
        if (comparator.compare(that.max, this.max) > 0)
            this.max = that.max;

        return this;
    }

    public static <T> Collector<T, Stats<T>, Stats<T>> collector(Comparator<? super T> comparator) {
        return Collector.of(
                () -> new Stats<>(comparator),
                Stats::accept,
                Stats::combine,
                Collector.Characteristics.UNORDERED, Collector.Characteristics.IDENTITY_FINISH
        );
    }

    public static <T extends Comparable<? super T>> Collector<T, Stats<T>, Stats<T>> collector() {
        return collector(Comparator.naturalOrder());
    }
}
