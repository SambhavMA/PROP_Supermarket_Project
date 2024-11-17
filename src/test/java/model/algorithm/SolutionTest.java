package model.algorithm;

import model.algorithm.Solution;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import model.algorithm.AlgorithmController;


public class SolutionTest {

    private double[][] costsMock;

    @Before
    public void setUp() {
        costsMock = new double[][]{
            {0.0, 2.0, 3.0, 5.0, 5.0},
            {2.0, 0.0, 4.0, 4.0, 3.0},
            {3.0, 4.0, 0.0, 8.0, 6.0},
            {5.0, 4.0, 8.0, 0.0, 0.0},
            {5.0, 3.0, 6.0, 0.0, 0.0}
        };
        AlgorithmController.costs = costsMock;
    }

    @Test
    public void testCostructorZeroParameter() {
        Solution solutionTest = new Solution();
        assertArrayEquals(new int[]{-1,-1,-1,-1,-1}, solutionTest.getPath());
        assertEquals(0, solutionTest.getCost(), 0.000001);
    }
    
    @Test
    public void testCostructorOneParameter() {
        int[] mypath = {0,2,1,4,3};
        double expectedCost = 15;
        Solution solutionTest = new Solution(mypath);
        assertArrayEquals(mypath, solutionTest.getPath());
        assertEquals(expectedCost, solutionTest.getCost(), 0.000001);
    }

    @Test
    public void testCostBetweenPathNodes() {
        int[] mypath = {0,2,1,4,3};
        int x = 1, y = 4;


        double expectedTestResult = 8;
        
        Solution solutionTest = new Solution(mypath);
        double testResult = solutionTest.costBetweenPathNodes(1,4); // pos 1 (in path) = node 2 (in costs matrix) and pos 4 = node 3

        assertEquals(expectedTestResult, testResult, 0.00001);
    }

    //no hacemos test con 2 parametros porque son solamente 2 asignaciones, no hay lógica alguna

    

    /*
            REPRESENTACIÓN GRÁFICA DEL SWAP EN EL TEST SWAP
            2 --- 1 - 4       
            |        /  
            |       /
            |      /
            0 --- 3

            2     1 - 4       
            |\   /   /  
            |  X    /
            |/   \ /
            0     3
     */
/*
    @Test
    public void testSwap() {
        int[] myPath = {0,2,1,4,3};
        int[] expectedPath = {0,2,3,4,1};
        
        Solution solutionTestIfCase = new Solution(myPath); //una hará el swap pasando por el if del códifo y otra por el else
        Solution solutionTestElseCase = new Solution(myPath);

        solutionTestIfCase.swap(1,4); // pos 1: 2 y pos 4: 3
        solutionTestElseCase.swap(4,1);
        assertArrayEquals(expectedPath, solutionTestIfCase.getPath());
        assertArrayEquals(expectedPath, solutionTestElseCase.getPath());
    }

    @Test
    public void testRemoveCost() {
        int[] myPath = {0,2,1,4,3}; 
        double expectedCost = 15 - 4 - 5; // cost between 2 and 1 is 4 and between 3 and 0 is 5
        
        Solution solutionTest = new Solution(myPath);
        solutionTest.removeCost(1,2,3,0);
        double testResult = solutionTest.getCost();

        assertEquals(expectedCost, testResult, 0.000001);
    }

    @Test
    public void testAddCost() {
        int[] myPath = {0,2,1,4,3}; 
        double expectedCost = 15 + 4 + 5; // cost between 2 and 1 is 4 and between 3 and 0 is 5
        
        Solution solutionTest = new Solution(myPath);
        solutionTest.addCost(1,2,3,0);
        double testResult = solutionTest.getCost();

        assertEquals(expectedCost, testResult, 0.000001);
    }*/


    @Test 
    public void testSwapAndUpdateNormalCase() {
        int[] myPath = {0,2,1,4,3};
        int[] expectedPath = {0,4,1,2,3}; //mas abajo hay una representacion grafica del swap (para el edge case)

        double expectedCost = 15 - 3 - 0; // cost between 0 and 2 is 3 and between 4 and 3 is 0
        expectedCost += 5 + 8;// cost between 0 and 4 is 5 and between 2 and 3 is 8
        
        Solution solutionTest = new Solution(myPath); //no hacemos if case y else case porque eso es lógica de la función swap, no de swap and update

        solutionTest.swapAndUpdate(0,3); // pos 0: 0 y pos 3: 4
        assertArrayEquals(expectedPath, solutionTest.getPath());
        assertEquals(expectedCost, solutionTest.getCost(), 0.000001);
    }
    
    //EDGE CASE: uno de los nodos de los que hacemos swap es de los extremos
    @Test 
    public void testSwapAndUpdateEdgeCase() {
        int[] myPath = {0,2,1,4,3};
        int[] expectedPath = {0,2,3,4,1}; //mas abajo hay una representacion grafica del swap

        double expectedCost = 15 - 4 - 5; // cost between 2 and 1 is 4 and between 3 and 0 is 5
        expectedCost += 8 + 2;// cost between 2 and 3 is 8 and between 1 and 0 is 5
        
        Solution solutionTest = new Solution(myPath); //no hacemos if case y else case porque eso es lógica de la función swap, no de swap and update

        solutionTest.swapAndUpdate(1,4); // pos 1: 2 y pos 4: 3
        assertArrayEquals(expectedPath, solutionTest.getPath());
        assertEquals(expectedCost, solutionTest.getCost(), 0.000001);
    }

    /*
            REPRESENTACIÓN GRÁFICA DEL SWAP EN EL TEST SWAP
            2 --- 1 - 4
            |        /
            |       /
            |      /
            0 --- 3

            2     1 - 4
            |\   /   /
            |  X    /
            |/   \ /
            0     3
     */


}
