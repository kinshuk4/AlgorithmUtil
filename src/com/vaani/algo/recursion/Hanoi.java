package com.vaani.algo.recursion;

/**
 * The classical hanoi problem. 
 *
 * O(2^n) time
 */
public class Hanoi {
  
  public void hanoi(int numOfPlates) {
    hanoiHelper(numOfPlates, 'A', 'B', 'C');
  }
  
  private void hanoiHelper(int numOfPlates, char from, char via, char to) {
    if(numOfPlates == 1) { // no more move needed
      System.out.println("Move from " + from + " to " + to);
    }
    else {
      hanoiHelper(numOfPlates - 1, from, to, via);
      System.out.println("Move from " + from + " to " + to);
      hanoiHelper(numOfPlates - 1, via, from, to);
    }
  }
  
}