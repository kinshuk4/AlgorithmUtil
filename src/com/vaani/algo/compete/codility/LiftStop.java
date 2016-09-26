package com.vaani.algo.compete.codility;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class LiftStop {
	public static void main(String[] args){
		System.out.println(LiftStop.solution(new int[]{40,40,230, 80,20}, new int[]{3,3, 2, 2, 3},3, 5, 200));
	}
	
	public static int solution2(int[] A, int[] B, int M, int X, int Y)
	   {
	       // write your code in C# 6.0 with .NET 4.5 (Mono)
	       int totalStops = 0;
	       long totalWeightPerRound = 0;
	       int maxPersonsCount = 0;
	       List<Integer> lstFloors = new LinkedList<Integer>();
	       int currPerson = 0;
	       boolean startLift = false;
	       while (currPerson < A.length)
	       {
	           if ((totalWeightPerRound + A[currPerson]) <= Y && (maxPersonsCount+1) <= X)
	           {
	               totalWeightPerRound += A[currPerson];
	               maxPersonsCount++;
	               lstFloors.add(B[currPerson]);
	               if (currPerson == A.length - 1)
	                   startLift = true;

	               currPerson++;
	           }
	           else
	           {
	               startLift = true;
	           }

	           if (startLift)
	           {
	               int uniqueCounts = new HashSet<Integer>(lstFloors).size();
	        	   totalStops += uniqueCounts + 1;
	               lstFloors.clear();
	               maxPersonsCount = 0;
	               totalWeightPerRound = 0;
	               startLift = false;
	           }
	       }
	       return totalStops;
	   }
    //M could be used to allocate int array to store requested floors, but I decided to go with hash set since it
    //still follow space and time complexity
    public static int solution(int[] A, int[] B, int M, int X, int Y) {
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

    public static int solution1(int[] A, int[] B, int M, int X, int Y) {
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
