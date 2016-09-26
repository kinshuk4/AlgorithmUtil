package com.vaani.algo.compete.codility.stackmachine;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackMachineTest {

    @Test
    public void testCalc() throws Exception {

        assertEquals(182, StackMachine.calc2("13+62*7+*"));
//        assertEquals(-1, calc("12++"));
//        assertEquals(-1, calc(""));
//        assertEquals(-1, calc("21+3g+"));
    }
}