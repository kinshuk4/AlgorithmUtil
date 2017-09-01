package com.vaani.algo.misc;

import com.google.common.base.Stopwatch;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Super Stack
 * Implement a simple stack that accepts the following commands and performs the operations associated with them:
 * <p>
 * push k: Push integer k onto the top of the stack.
 * pop: Pop the top element from the stack. It is guaranteed that this will not be called on an empty stack.
 * inc e k: Add k to each of the bottom e elements of the stack.
 * Complete the superStack function from the provided code. It has one parameter: an array of n strings, operations. The function must create an empty stack and perform each of the n operations in order and, after performing each operation, print the value of the stack’s top element on a new line; if the stack is empty, print EMPTY instead.
 * <p>
 * Input Format
 * <p>
 * The provided code reads the following input from stdin and passes it to the function:
 * <p>
 * The first line contains an integer, n, denoting the number of operations to perform on the stack.
 * <p>
 * Each line i of the n subsequent lines contains a space-separated string describing operations[i].
 * <p>
 * Constraints
 * <p>
 * 1 ≤ n ≤ 2 × 10^5
 * -10^9 ≤ k ≤ 10^9
 * 1 ≤ e ≤ |S|, where |S| is the size of the stack at the time of the operation.
 * It is guaranteed that pop is never called on an empty stack.
 * Output Format
 * <p>
 * After performing each operation, print the value of the stack’s top element on a new line; if the stack is empty, print EMPTY instead.
 * <p>
 * Sample Input 0
 * <p>
 * 12
 * push 4
 * pop
 * push 3
 * push 5
 * push 2
 * inc 3 1
 * pop
 * push 1
 * inc 2 2
 * push 4
 * pop
 * pop
 * Sample Output 0
 * <p>
 * 4
 * EMPTY
 * 3
 * 5
 * 2
 * 3
 * 6
 * 1
 * 1
 * 4
 * 1
 * 8
 * Explanation 0
 * <p>
 * The diagram below depicts the stack after each operation:
 * <p>
 * After each operation, we print the value denoted by Current Top on a new line.
 * <p>
 * In other words, we have an empty stack, S, we express as an array where the leftmost element is the bottom of the stack and the rightmost element is its top. We perform the following sequence of n = 12 operations as given in the operations array:
 * <p>
 * push 4: Push 4 onto the top of the stack, so S = [4]. We then print the top element, 4, on a new line.
 * pop: Pop the top element from the stack, so S = []. Because the stack is now empty, we print EMPTY on a new line.
 * push 3: Push 3 onto the top of the stack, so S = [3]. We then print the top element, 3, on a new line.
 * push 5: Push 5 onto the top of the stack, so S = [3, 5]. We then print the top element, 5, on a new line.
 * push 2: Push 2 onto the top of the stack, so S = [3, 5, 2]. We then print the top element, 2, on a new line.
 * inc 3 1: Add k = 1 to bottom e = 3 elements of the stack, so S = [4, 6, 3]. We then print the top element, 3, on a new line.
 * pop: Pop the top element from the stack, so S = [4, 6]. We then print the top element, 6, on a new line.
 * push 1: Push 1 onto the top of the stack, so S = [4, 6, 1]. We then print the top element, 1, on a new line.
 * inc 2 2: Add k = 2 to bottom e = 2 elements of the stack, so S = [6, 8, 1]. The top element is 1, so we print 1 after this operation.
 * push 4: Push 4 onto the top of the stack, so S = [6, 8, 1, 4]. We then print the top element, 4, on a new line.
 * pop: Pop the top element from the stack, so S = [6, 8, 1]. We then print the top element, 1, on a new line.
 * pop: Pop the top element from the stack, so S = [6, 8]. We then print the top element, 8, on a new line.
 */
public class SuperStack {

    public static final String POP = "pop";
    public static final String PUSH = "push";
    public static final String INC = "inc";
    public static final String EMPTY = "EMPTY";

    public static void main(String[] args) {

        String[] operations = //readOperations();
                {"push 4", "pop", "push 3", "push 5", "push 2", "inc 3 1", "pop", "push 1", "inc 2 2", "push 4", "pop", "pop"};

        //Arrays.stream(operations).forEach(System.out::println);
        operations = enrichNumberOfOperations(operations, 17);

        checkExecutionDuration(SuperStack::superStack, operations);
    }

    private static String[] enrichNumberOfOperations(String[] operations, int factor) {
        List<String> operationsList = new ArrayList<>(Arrays.asList(operations));
        for (int i = 0; i < factor; i++) {
            operationsList.addAll(operationsList);
        }

        System.out.println("Number of enriched operations = " + operationsList.size());
        return operationsList.subList(0, 1_000_000).toArray(new String[999_999]); // takes around 5 seconds to execute
    }

    private static String[] readOperations() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Number of operations = ");
            Integer size = Integer.valueOf(scanner.nextLine());
            String[] operations = new String[size];
            for (int i = 0; i < size; i++) {
                System.out.println(String.format("op[%d]=", i));
                operations[i] = scanner.nextLine();
            }

            return operations;
        }
    }

    private static <T> void checkExecutionDuration(Consumer<T> runner, T param) {
        Stopwatch timer = Stopwatch.createStarted();
        runner.accept(param);
        timer.stop();
        System.out.println(String.format("Execution time: %d %s", timer.elapsed(TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS));
    }

    /**
     * Complete the function below.
     */
    static void superStack(String[] operations) {
        if (operations[0].toLowerCase().startsWith(POP)) {
            throw new IllegalArgumentException("It's not allowed to have the first operation - pop !");
        }

        Consumer<Integer> printer = number -> System.out.println(number == null ? EMPTY : number);

        LinkedList<Integer> superStack = new LinkedList<>();
        for (String operation : operations) {
            if (operation.toLowerCase().startsWith(POP)) {
                superStack.pollFirst();
            } else if (operation.toLowerCase().startsWith(PUSH)) {
                Integer number = Integer.valueOf(operation.split(" +")[1]);
                superStack.push(number);
            } else if (operation.toLowerCase().startsWith(INC)) {
                String[] opTokens = operation.split(" +");
                Integer elementsToUpdate = Integer.valueOf(opTokens[1]);
                Integer valueToAdd = Integer.valueOf(opTokens[2]);

                for (int i = superStack.size() - 1; i >= superStack.size() - elementsToUpdate; i--) {
                    superStack.set(i, superStack.get(i) + valueToAdd);
                }
            }

            printer.accept(superStack.peekFirst());
        }

    }



	/*private static void superStack(String[] operations) {
		if (operations[0].toLowerCase().startsWith("pop")) {
			throw new IllegalArgumentException("It's not allowed to have first operation 'pop' !");
		}
		Consumer<Integer> printer = number -> System.out.println(number == null ? "EMPTY" : number);
		LinkedList<Integer> superStack = new LinkedList<>();
		Arrays.stream(operations)
				.forEach(op -> {
					if (op.toLowerCase().startsWith("pop")) {
						superStack.pollFirst();
					} else if (op.toLowerCase().startsWith("push")) {
						Integer number = Integer.valueOf(op.split(" +")[1]);
						superStack.push(number);
					} else if (op.toLowerCase().startsWith("inc")) {
						String[] opTokens = op.split(" +");
						Integer elementsToUpdate = Integer.valueOf(opTokens[1]);
						Integer valueToAdd = Integer.valueOf(opTokens[2]);
						for (int i = superStack.size() - 1; i >= superStack.size() - elementsToUpdate; i--) {
							superStack.set(i, superStack.get(i) + valueToAdd);
						}
					}
					printer.accept(superStack.peekFirst());
				});
	}*/
}

