package com.vaani.algo.math.fibonacci;

import java.math.BigInteger;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**https://github.com/vizdragos/playground/blob/17f5da2d95bfbf82ef71391632c73eb3a43ada39/src/main/java/ro/playground/exercise/IsFibonacci.java
 * You are given an integer, N . Write a program to determine if N is an element of the Fibonacci sequence.
 * <p>
 * The first few elements of the Fibonacci sequence are 0, 1, 1, 2, 3, 5, 8, 13 ...
 * A Fibonacci sequence is one where every element is a sum of the previous two elements in the sequence. The first two elements are 0 and 1.
 */
public class IsFibonacci {

    public static void main(String[] args) {
		/*for (int i = 0; i < 10; i++) {
			System.out.println(fibonacciClassicRecursion(i));
		}*/

		/*fibonacciJava8(10).forEach(System.out::println);*/
        System.out.println(isFibonacci(BigInteger.valueOf(10)));
    }

    // Hopefully Java 9 will introduce takeWhile(Predicate<>) in order to limit infinite stream by condition !
    private static boolean isFibonacci(BigInteger numberToCheck) {
        boolean isFibonacci = false;
        List<BigInteger> fibonacciSequence;
        for (int i = numberToCheck.intValue(); !isFibonacci; i++) {
            fibonacciSequence = fibonacciJava8(i).collect(Collectors.toList());

            if (fibonacciSequence.contains(numberToCheck)) {
                isFibonacci = true;
            } else if (fibonacciSequence.get(fibonacciSequence.size() - 1).compareTo(numberToCheck) > 0) {
                break;
            }
        }

        return isFibonacci;
    }

    private static int fibonacciClassicRecursion(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacciClassicRecursion(n - 1) + fibonacciClassicRecursion(n - 2);
    }

    private static Stream<BigInteger> fibonacciJava8(int number) {

        Tuple<BigInteger, BigInteger> seed = new Tuple<>(BigInteger.ZERO, BigInteger.ONE);

        UnaryOperator<Tuple<BigInteger, BigInteger>> f = x -> new Tuple<>(x._2, x._1.add(x._2));

        return Stream.iterate(seed, f)
                .map(x -> x._1)
                .limit(number);

        // or simpler just:
		/*Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
				.limit(10)
				.map(t -> t[0]);*/
    }
}

// from https://dzone.com/articles/do-it-java-8-recursive-and
class Tuple<T, U> {
    public final T _1;
    public final U _2;

    public Tuple(T t, U u) {
        this._1 = t;
        this._2 = u;
    }
}
