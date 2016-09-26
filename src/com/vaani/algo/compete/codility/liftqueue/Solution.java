package com.vaani.algo.compete.codility.liftqueue;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    //M could be used to allocate int array to store requested floors, but I decided to go with hash set since it
    //still follow space and time complexity
    public int solution(int[] A, int[] B, int M, int X, int Y) {
        // write your code in Java SE 8
        int paxInLiftWeight = 0;
        int paxInLiftCount = 0;
        Set<Integer> requestedFloors = new HashSet<>();
        int stops = 0;
        for (int i = 0; i < A.length; i++) {
            int paxWeight = A[i];
            if (paxInLiftCount >= X || paxInLiftWeight + paxWeight > Y) {
                stops += requestedFloors.size() + 1;
                requestedFloors.clear();
                paxInLiftCount = 0;
                paxInLiftWeight = 0;
            }
            paxInLiftCount++;
            paxInLiftWeight += paxWeight;
            requestedFloors.add(B[i]);
        }
        if (!requestedFloors.isEmpty()) {
            stops += requestedFloors.size() + 1;
        }
        return stops;
    }

    public int solution1(int[] A, int[] B, int M, int X, int Y) {
        Lift lift = new Lift(X, Y);
        int totalStops = 0;
        for (int i = 0; i < A.length; i++) {
            int paxWeight = A[i];
            int paxRequestedStop = B[i];
            if (!lift.canTakePassenger(paxWeight)) {
                totalStops += lift.transportPassengers();
            }
            lift.takePassenger(paxWeight, paxRequestedStop);
        }
        if (lift.hasPassengers()) {
            totalStops += lift.transportPassengers();
        }
        return totalStops;
    }

    private static class Lift {

        private int maxWeight;
        private int maxAmount;
        private int pax;
        private int paxWeight;

        private Set<Integer> requestedFloors;

        private Lift(int maxAmount, int maxWeight) {
            requestedFloors = new HashSet<>();
            this.maxWeight = maxWeight;
            this.maxAmount = maxAmount;
        }

        public boolean canTakePassenger(int weight) {
            return pax < maxAmount && paxWeight + weight <= maxWeight;
        }

        public boolean takePassenger(int weight, int requestedFloor) {
            if (canTakePassenger(weight)) {
                pax++;
                paxWeight += weight;
                requestedFloors.add(requestedFloor);
                return true;
            }
            return false;
        }

        public boolean hasPassengers() {
            return pax > 0;
        }

        public int transportPassengers() {
            pax = 0;
            paxWeight = 0;
            int stops = requestedFloors.size() + 1;
            requestedFloors.clear();
            return stops;
        }

    }
}
