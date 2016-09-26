/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 12/06/2016
 *
 * Hacker rank
 * BlackRock CodeSprint
 * Problem: Portfolio Manager
 * Status: accepted
 * 
 * Problem
 * 
 * As a Portfolio Manager, you recently came across a hot 
 * new technology startup. You're confident that this is a 
 * great opportunity and want all of the portfolios you manage 
 * to have a stake in this startup. To reduce the risk of this 
 * venture, you must find a way to invest the maximum possible 
 * amount of money in the new company for as many of your portfolios 
 * as possible without violating any of your company's risk 
 * tolerance rules.
 * 
 * The set of Portfolios you manage are organized in a binary tree 
 * structure, where each portfolio is associated with at most two 
 * other child portfolios and each portfolio has at most one parent 
 * portfolio. A portfolio also has an integer attribute, A, that 
 * denotes the amount of funds the portfolio currently has available 
 * for making new investments.
 * 
 * When selecting portfolios for your new investment idea, a risk 
 * violation is triggered if you select portfolios that are directly 
 * related to each other. Given your client portfolios as a serialized 
 * binary tree, find and print the maximum amount of money you can 
 * invest in this tech stock without violating the risk tolerance rule.
 * 
 * Input Format
 * 
 * Input is in the form of a serialized binary tree representing the
 * portfolios. For the purpose of this challenge, assume the cash 
 * available for each portfolio is an integer. The serialization of a 
 * binary tree follows a level-order traversal, where # signifies a 
 * path terminator where no node exists below.
 * 
 *    1
 *   / \
 *  2   3
 *     /
 *    4
 *     \
 *      5
 * 
 * The binary tree above is serialized as 1 2 3 # # 4 # # 5 
 * There are two lines of input. The first line contains an integer 
 * denoting the number of portfolios, N. The second line contains some 
 * number of space-separated values defining the serialized representation 
 * of the portfolios in the format specified above.
 * 
 * Output Format
 * 
 * Print a single integer denoting the maximum amount of money you can
 * invest in this new tech stock.
 * 
 * Input
 * 
6
3 4 5 1 3 # 1
 * 
 * Output
 * 
9
 * 
 */
package com.vaani.algo.compete.hackerrank.codesprint.blackrock;

import static java.math.BigInteger.ZERO;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Portfolio_Manager {

    static class Node {
        Node l, r;
        long a;
        BigInteger v;
    }
    
    static Node R = new Node(); 
    
    static void buildTreeFromHeap(Scanner scanner) {
        scanner.nextLong();
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(R);
        R.a = scanner.nextLong();
        while (scanner.hasNext()) {
            Node p = nodes.poll();
            String l = scanner.next();
            if (l.charAt(0) != '#') { 
                p.l = new Node();
                p.l.a = Long.valueOf(l);
                nodes.add(p.l);
            }
            if (!scanner.hasNext()) break;
            String r = scanner.next();
            if (r.charAt(0) != '#') { 
                p.r = new Node();
                p.r.a = Long.valueOf(r);
                nodes.add(p.r);
            }
        }
    }
    
    static BigInteger preOrder(Node n) {
        if (n == null) return ZERO;
        preOrder(n.l);
        preOrder(n.r);
        BigInteger nll = ZERO;
        BigInteger nlr = ZERO;
        if (n.l != null) {
            nll = n.l.l == null? ZERO: n.l.l.v;
            nlr = n.l.r == null? ZERO: n.l.r.v;
        }
        BigInteger nrl = ZERO;
        BigInteger nrr = ZERO;
        if (n.r != null) {
            nrl = n.r.l == null? ZERO: n.r.l.v;
            nrr = n.r.r == null? ZERO: n.r.r.v;
        }
        BigInteger nl = n.l == null? ZERO: n.l.v;
        BigInteger nr = n.r == null? ZERO: n.r.v;
        n.v = nll.add(nlr.add(nrl.add(nrr.add(BigInteger.valueOf(n.a)))));
        BigInteger t = nr.add(nl);
        if (n.v.compareTo(t) < 0)
            n.v = t;
        return n.v;
    }
    
    static BigInteger solve() {
        return preOrder(R);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.getProperty("local") == null? 
                System.in: Portfolio_Manager.class.getResourceAsStream("Portfolio_Manager.in"));
        buildTreeFromHeap(scanner);
        System.out.println(solve());
        scanner.close();
    }

}
