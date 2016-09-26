package com.vaani.algo.compete.codility.decimalzip;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexey
 */
public class DecimalZipTest {

    @Test
    public void decimalZipTest() {
        System.out.println("decimalZipTest");
        assertEquals(1526, DecimalZip.generate(12, 56));
        assertEquals(5162, DecimalZip.generate(56, 12));
        assertEquals(16273845, DecimalZip.generate(12345, 678));
        assertEquals(16273890, DecimalZip.generate(123, 67890));
        assertEquals(-1, DecimalZip.generate(100_000_000, 1));
    }
}
