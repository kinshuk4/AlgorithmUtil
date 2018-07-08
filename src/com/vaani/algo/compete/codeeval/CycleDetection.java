package com.vaani.algo.compete.codeeval;

import com.vaani.algo.ds.core.list.ListNode;

import java.io.File;
import java.util.*;

public class CycleDetection {

    public static void main(String... args) throws Exception {
        Scanner sc = new Scanner(new File(args[0]));
        while (sc.hasNextLine()) {
            ListNode listNode = convertToCycledList(sc.nextLine());
            System.out.println(findCycleStart(listNode).val);
        }
    }

    private static ListNode findCycleStart(ListNode head) {
        ListNode tortoise = head.next;
        ListNode hare = head.next.next;
        while (hare.val != tortoise.val) {
            hare = hare.next.next;
            tortoise = tortoise.next;
        }
        hare = head.next;
        tortoise = tortoise.next;
        while (hare.val != tortoise.val) {
            hare = hare.next;
            tortoise = tortoise.next;
        }
        return hare;
    }

    private static ListNode convertToCycledList(String line) {
        Scanner lineScanner = new Scanner(line);
        Set<Integer> integersSet = new HashSet<>();
        ListNode previous = new ListNode(0);//dummyNode
        ListNode root = previous;
        while (lineScanner.hasNextInt()) {
            int currentValue = lineScanner.nextInt();
            ListNode listNode = new ListNode(currentValue);

            if (!integersSet.add(currentValue)) {
                ListNode<Integer> cycleStartListNode;
                cycleStartListNode = root.next;
                while (cycleStartListNode != null) {
                    if (cycleStartListNode.val == currentValue) {
                        break;
                    }
                    cycleStartListNode = cycleStartListNode.next;
                }
                previous.next = cycleStartListNode;
                break;
            } else {
                previous.next = listNode;
                previous = listNode;
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
