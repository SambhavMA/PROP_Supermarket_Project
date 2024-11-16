package test.model.algorithm;

import main.model.algorithm.Solution;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SolutionTest {

    private double[][] costsMock;

    @BeforeClass
    public void setUp() {
        costsMock = new double[][]{
            {0.0, 2.0, 3.0},
            {2.0, 0.0, 4.0},
            {3.0, 4.0, 0.0}
        };
        Solution.costs = costsMock;
    }

    @Test
    public testCostructorZeroParameter() {
        Solution solutionTest = new Solution();
        assertArrayEquals({-1,-1,-1}, solutionTest.getPath);
        assertEquals(0, solutionTest.getCost());
    }
    
    @Test
    public testCostructorOneParameter() {
        int[] mypath = {0,2,1};
        double expectedCost = 9;
        Solution solutionTest = new Solution(mypath);
        assertArrayEquals(mypath, solutionTest.getPath);
        assertEquals(expectedCost, solutionTest.getCost());
    }

    //no hacemos test con 2 parametros porque son solamente 2 asignaciones, no hay lógica alguna

    @Test
    public testSwap() {
        int[] mypath = {0,2,1};
        double expectedCost = 9;
        Solution solutionTest = new Solution(mypath);
        assertArrayEquals(mypath, solutionTest.getPath);
        assertEquals(expectedCost, solutionTest.getCost());
    }

}
