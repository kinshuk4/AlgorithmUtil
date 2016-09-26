package com.vaani.algo.compete.cc150.chapter11;

import java.util.List;

/**
 * A circus is designing a tower routine consisting of people standing atop one
 * another's shoulders. For practical and aesthetic reasons, each person must be
 * both shorter and lighter than the person below him or her. Given the heights
 * and weights of each person in the circus, write a method to compute the
 * largest possible number of people in such a tower.
 * <p>
 * EXAMPLE: Input (ht, wt): (65, 200) (70, 150) (56, 90) (75, 190) (60, 95) (68,
 * 110) Output: The longest tower is length 6 and includes from top to bottom:
 * (56, 90) (60, 95) (65, 100) (68, 110) (70, 150) (75, 190)
 */
public class Question7 {

    public List<Person> longestTower(Person[] persons) {
        // write implementation here

        return null;
    }

    public static class Person {
        public int wt;
        public int ht;

        public Person(int wt, int ht) {
            this.wt = wt;
            this.ht = ht;
        }
    }
}
