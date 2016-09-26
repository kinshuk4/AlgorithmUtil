package com.vaani.algo.compete.cc150.chap3;

import java.util.ArrayList;
import java.util.List;

/**
 * In the classic problem of the Towers of Hanoi, you have 3 towers and N disks
 * of different sizes which can slide onto any tower. The puzzle starts with
 * disks sorted in ascending order of size from top to bottom (i.e., each disk
 * sits on top of an even larger one). You have the following constraints: (1)
 * Only one disk can be moved at a time. (2) A disk is slid off the top of one
 * tower onto the next tower. (3) A disk can only be placed on top of a larger
 * disk. Write a program to move the disks from the first tower to the last
 * using stacks.
 * <p>
 * Note: the disks are numbered as 1, 2, and 3.
 */
//  O(2^n) space, O(2^n) time
public class Question4 {

    // put the action sequence into this array list.
    private List<Action> actions = new ArrayList<Action>();

    public List<Action> hanoi(int numDisks) {
        // Write the implementation here.
        move(1, 2, 3, numDisks);
        return actions;
    }

    private void move(int from, int via, int to, int numDisks) {
        if (numDisks == 1) {
            actions.add(new Action(from, to));
        } else {
            move(from, to, via, numDisks - 1);
            actions.add(new Action(from, to));
            move(via, from, to, numDisks - 1);
        }
    }

    /**
     * The auxiliary data structure to record the actions.
     */
    public static class Action {
        private int from;
        private int to;

        public Action(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Action other = (Action) obj;
            if (from != other.from)
                return false;
            if (to != other.to)
                return false;
            return true;
        }

        @Override
        public String toString() {
            return new StringBuilder("Move from ").append(from).append(" to ")
                    .append(to).toString();
        }
    }

}


