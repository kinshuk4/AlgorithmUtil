package com.vaani.algo.compete.cc150.chap7;

/**
 * You have a basketball hoop and someone says that you can play one of two
 * games. Game 1: You get one shot to make the hoop. Game 2: You get three shots
 * and you have to make two of three shots. If p is the probability of making a
 * particular shot, for which values of p should you pick one game or the other?
 */
public class Question1 {

    /**
     * @param p The probability.
     * @return Which game is preferred 1 or 2.
     */
    public int chooseGame(double p) {
        // write the implementation here
        double g1 = p;
        double g2 = p * p + p * (1 - p) * p + (1 - p) * p * p;
        return g1 >= g2 ? 1 : 2;
    }
}
