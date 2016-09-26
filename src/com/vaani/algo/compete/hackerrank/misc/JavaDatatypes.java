/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 20/06/2015
 * 
 * Hacker rank
 * Problem: Java Datatypes
 * Status: accepted
 * 
 * Given an integer number, you have to determine which of primitive datatypes you 
 * can use to store that number. 
 * 
 * Input Format
 * 
 * The first line will contain an integer T, which denotes the number of inputs 
 * that will follow. Each of the next T lines will contain an integer n. The number 
 * can be arbitrarily large or small!
 * 
 * Output Format
 * 
 * For each n, list all the datatypes it can be fitted into ordered by the size of the datatype.
 * 
 * Sample Input
 * 
5
-150
150000
1500000000
213333333333333333333333333333333333
-100000000000000
 * 
 * Sample Output
 * 
-150 can be fitted in:
* short
* int
* long
150000 can be fitted in:
* int
* long
1500000000 can be fitted in:
* int
* long
213333333333333333333333333333333333 can't be fitted anywhere.
-100000000000000 can be fitted in:
* long
 * 
 */

package com.vaani.algo.compete.hackerrank.misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.stream.IntStream;

public class JavaDatatypes {

    static void printTypes(BigInteger n, StringBuilder buf) {
        int c = 0;
        buf.append(n.toString());
        if (n.compareTo(BigInteger.ZERO) < 0)
            n = n.negate().subtract(BigInteger.ONE);
        while ((n = n.shiftRight(1)) != BigInteger.ZERO)
            c++;
        if (c > 63) {
            buf.append(" can't be fitted anywhere.\n");
            return;
        }
        buf.append(" can be fitted in:\n");
        if (c <= 7)
            buf.append("* byte\n");
        if (c <= 15)
            buf.append("* short\n");
        if (c <= 31)
            buf.append("* int\n");
        if (c <= 63)
            buf.append("* long\n");
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(new File("/tmp/in"));
        StringBuilder b = new StringBuilder();
        int N = scanner.nextInt();
        scanner.nextLine();
        IntStream.range(0, N).forEach((i) -> printTypes(new BigInteger(scanner.nextLine()), b));
        scanner.close();
        System.out.println(b.toString());
    }

}
