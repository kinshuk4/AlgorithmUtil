package com.vaani.algo.compete.cc150.chap7;

/**
 * There are three ants on different vertices of a triangle. What is the
 * probability of collision (between any two or all of them) if they start
 * walking on the sides of the triangle? Assume that each ant randomly picks a
 * direction, with either direction being equally likely to be chosen, and that
 * they walk at the same speed. Assume they will stop once they come to another
 * vertex.
 */
public class Question2 {

    public double probability(int nVertices) {
        // write implementation here
        return 1 - 2 / Math.pow(2, nVertices);
    }

}


