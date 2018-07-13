package com.vaani.algo.ds.algos.list.linked;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class LargeNumber {

    List<Integer> largeNumber;

    public LargeNumber(String number) {
        largeNumber = new LinkedList<Integer>();
        createNumber(number);
    }

    private static SumDigit sum(Integer num1, Integer num2, int carry) {
        SumDigit result;
        if (num1 == null && num2 == null) {
            if (carry == 0)
                result = null;
            else
                result = new SumDigit(carry, 0);
        } else if (num1 != null && num2 != null) {
            int temp = num1 + num2 + carry;
            int digit = temp % 10;
            carry = temp / 10;

            result = new SumDigit(digit, carry);

        } else if (num1 != null) {
            int temp = num1 + carry;
            int digit = temp % 10;
            carry = temp / 10;

            result = new SumDigit(digit, carry);
        } else {
            // only num2 is not null here
            int temp = num2 + carry;
            int digit = temp % 10;
            carry = temp / 10;

            result = new SumDigit(digit, carry);
        }

        return result;
    }

    public static String sum(String nu1, String nu2) {
        LargeNumber num1 = new LargeNumber(nu1);
        LargeNumber num2 = new LargeNumber(nu2);
        num1.sum(num2);
        return num1.toString();
    }

    public static void main(String[] args) {
        LargeNumber nu1 = new LargeNumber("13422");
        LargeNumber nu2 = new LargeNumber("789123");
        nu1.sum(nu2);
        System.out.println(nu1.toString());
    }

    private void createNumber(String string) {


        char[] array = string.toCharArray();

        int[] intArray = new int[array.length];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = array[i] - '0';
            largeNumber.add(intArray[i]);
        }
    }

    public List<Integer> getList() {
        return largeNumber;
    }
    //	public void sum(ListNode num1, ListNode num2) {
    //		ListNode n1 = reverseIterative(num1);
    //		ListNode n2 = reverseIterative(num2);
    //
    //		int carry = 0;
    //		ListNode sum = sum(n1, n2, carry);
    //
    //		sum = reverseIterative(sum);
    //
    //		return sum;
    //	}
    //
    //	private static ListNode<Integer> sum(ListNode<Integer> num1, ListNode<Integer> num2, int carry) {
    //		ListNode<Integer> result;
    //		if(num1 == null && num2 == null) {
    //			if(carry == 0)
    //				result = null;
    //			else
    //				result = new ListNode<Integer>(carry);
    //
    //		}else if(num1 != null && num2 != null) {
    //			int temp = num1.data + num2.data + carry;
    //			int digit = temp % 10;
    //			carry = temp / 10;
    //
    //			result = new ListNode<Integer>(digit);
    //			result.next = sum(num1.next, num2.next, carry);
    //		} else if(num1 != null) {
    //			int temp = num1.data + carry;
    //			int digit = num1.data % 10;
    //			carry = temp / 10;
    //
    //			result = new ListNode(digit);
    //			result.next = sum(num1.next, null, carry);
    //		} else {
    //			// only num2 is not null here
    //			int temp = num2.data + carry;
    //			int digit = num2.data % 10;
    //			carry = temp / 10;
    //
    //			result = new ListNode(digit);
    //			result.next = sum(null, num2.next, carry);
    //		}
    //
    //		return result;
    //	}

    public void sum(LargeNumber largeNumber2) {
        sum(this.largeNumber, largeNumber2.getList());
    }

    public void sum(String largeStr) {
        LargeNumber num2 = new LargeNumber(largeStr);
        sum(num2);
    }

    private void sum(List<Integer> list1, List<Integer> list2) {
        Collections.reverse(list1);
        Collections.reverse(list2);
//		list1.reverseIterative();
//		list2.reverseIterative();

        System.out.println(list1.toString() + "----" + list2.toString());

        int carry = 0;

        LinkedList<Integer> sumList = new LinkedList<Integer>();
        Iterator<Integer> iter1 = list1.iterator();
        Iterator<Integer> iter2 = list2.iterator();
        SumDigit sumOfDig = new SumDigit(0, 0);
        while (iter1.hasNext() || iter2.hasNext() || sumOfDig.getCarry() != 0) {
            sumOfDig = sum(iter1.next(), iter2.next(), carry);
            if (sumOfDig != null) {
                sumList.add(sumOfDig.getDigit());
                carry = sumOfDig.getCarry();
            } else break;
        }

//		sumList.reverseIterative();
        Collections.reverse(sumList);
        this.largeNumber = sumList;


    }

    public String toString() {
        return largeNumber.toString();
    }

}
