package com.vaani.algo.compete.codeeval;

import java.io.File;
import java.util.*;

public class CycleDetection {

    private static class Node {
        private int value;
        private Node next;
    }

    public static void main(String... args) throws Exception {
        Scanner sc = new Scanner(new File(args[0]));
        while (sc.hasNextLine()) {
            Node node = convertToCycledList(sc.nextLine());
            System.out.println(findCycleStart(node).value);
        }
    }

    private static Node findCycleStart(Node head) {
        Node tortoise = head.next;
        Node hare = head.next.next;
        while (hare.value != tortoise.value) {
            hare = hare.next.next;
            tortoise = tortoise.next;
        }
        hare = head.next;
        tortoise = tortoise.next;
        while (hare.value != tortoise.value) {
            hare = hare.next;
            tortoise = tortoise.next;
        }
        return hare;
    }

    private static Node convertToCycledList(String line) {
        Scanner lineScanner = new Scanner(line);
        Set<Integer> integersSet = new HashSet<>();
        Node previous = new Node();
        Node root = previous;
        while (lineScanner.hasNextInt()) {
            int currentValue = lineScanner.nextInt();
            Node node = new Node();
            node.value = currentValue;
            if (!integersSet.add(currentValue)) {
                Node cycleStartNode;
                cycleStartNode = root.next;
                while (cycleStartNode != null) {
                    if (cycleStartNode.value == currentValue) {
                        break;
                    }
                    cycleStartNode = cycleStartNode.next;
                }
                previous.next = cycleStartNode;
                break;
            } else {
                previous.next = node;
                previous = node;
            }
        }
        return root;
    }

    private static void printCycle1(String line) {
        Scanner lineScanner = new Scanner(line);
        LinkedHashSet<Integer> values = new LinkedHashSet<>();
        int firstCycleValue = 0;
        while (lineScanner.hasNextInt()) {
            int currentValue = lineScanner.nextInt();
            if (!values.add(currentValue)) {
                firstCycleValue = currentValue;
                break;
            }
        }
        boolean reachedCycle = false;
        Iterator<Integer> iterator = values.iterator();
        while (iterator.hasNext()) {
            int value = iterator.next();
            if (value == firstCycleValue) {
                reachedCycle = true;
            }
            if (reachedCycle) {
                System.out.print(value);
                if (iterator.hasNext()) {
                    System.out.print(" ");
                }
            }
        }
    }

    private static void printCycle(String line) {
        Scanner lineScanner = new Scanner(line);
        int[] positions = new int[50];
        int iteratorPosition = 0;
        boolean found = false;
        while (lineScanner.hasNextInt()) {
            int number = lineScanner.nextInt();
            int valueSaved = positions[number] - 1;
            if (!found) {
                if (valueSaved != -1) {
                    positions[number] = 0;
                    found = true;
                    System.out.print(number);
                } else {
                    positions[number] = iteratorPosition + 1;
                    iteratorPosition++;
                }
            } else if (valueSaved != -1) {
                positions[number] = 0;
                System.out.print(" " + number);
            } else {
                break;
            }
        }
    }

}
