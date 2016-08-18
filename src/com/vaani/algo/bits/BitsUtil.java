package com.vaani.algo.bits;

public class BitsUtil {
    public static int numOnesInInteger(int num) {
        int numOnes = 0;
        while (num != 0) {
            if ((num & 1) == 1) {
                numOnes++;
            }
            num = num >> 1;
        }
        return numOnes;
    }

    public static int numOnesInIntegerFaster(int num) {
        int numOnes = 0;
        while (num != 0) {
            numOnes++;
            num = num & (num - 1); // This clears the least significant bit set.
        }
        return numOnes;
    }

    public static boolean isPowerOfTwo(int num) {
        return (num != 0 && (num & (num - 1)) == 0);

    }


    public static void main(String args[]) {
        int num = 32;//1001
        int numOf1s = numOnesInInteger(num);
        System.out.println(numOf1s);

        numOf1s = numOnesInIntegerFaster(num);
        System.out.println(numOf1s);

        boolean isPower = isPowerOfTwo(num);
        System.out.println(isPower);


    }
}
