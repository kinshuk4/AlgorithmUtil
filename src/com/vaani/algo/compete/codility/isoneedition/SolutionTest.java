package com.vaani.algo.compete.codility.isoneedition;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SolutionTest {

    private Solution solution;

    @Before
    public void setUp(){
        solution = new Solution();
    }

    @Test
    public void testSolutionInset(){
        Assert.assertEquals(Solution.INSERT + " e", solution.solution("nice", "niece"));
    }

    @Test
    public void testSolutionInsetEnd(){
        Assert.assertEquals(Solution.INSERT + " e", solution.solution("nice", "nicee"));
    }

    @Test
    public void testSolutionInsetFalse(){
        Assert.assertEquals(Solution.IMPOSSIBLE, solution.solution("nice", "niecy"));
    }

    @Test
    public void testSolutionDelete(){
        Assert.assertEquals(Solution.DELETE + " f", solution.solution("cardf", "card"));
    }

    @Test
    public void testSolutionSwap(){
        Assert.assertEquals(Solution.SWAP + " o r", solution.solution("form", "from"));
    }

    @Test
    public void testSolutionSwapFalse(){
        Assert.assertEquals(Solution.IMPOSSIBLE, solution.solution("form", "fora"));
    }

    @Test
    public void testSolutionNothing(){
        Assert.assertEquals(Solution.NOTHING, solution.solution("equal", "equal"));
    }

    @Test
    public void testSolutionImpossible(){
        Assert.assertEquals(Solution.IMPOSSIBLE , solution.solution("equal", "not equal"));
    }

}
