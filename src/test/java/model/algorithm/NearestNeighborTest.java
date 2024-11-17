
package model.algorithm;
import static org.junit.Assert.*;
import org.junit.Test;



public class NearestNeighborTest {

    @Test
    public void testExecuteNormalCaseInitialFirst() {
        double[][] mycosts = {
            {0.0, 10.0, 15.0, 20.0},
            {10.0, 0.0, 35.0, 25.0},
            {15.0, 35.0, 0.0, 30.0},
            {20.0, 25.0, 30.0, 0.0}
        };
        AlgorithmController.costs = mycosts;

        NearestNeighbor NN = new NearestNeighbor();

        int[] expectedPath = new int[]{0, 1, 3, 2};
        double expectedCost = 80.0;

        Solution result = NN.execute(0, 4);

        assertArrayEquals(expectedPath, result.getPath());
        assertEquals(expectedCost, result.getCost(), 0.000001);
    }

    @Test
    public void testExecuteNormalCase() {
        double[][] mycosts = {
            {0.0, 10.0, 15.0, 20.0},
            {10.0, 0.0, 35.0, 25.0},
            {15.0, 35.0, 0.0, 30.0},
            {20.0, 25.0, 30.0, 0.0}
        };
        AlgorithmController.costs = mycosts;

        NearestNeighbor NN = new NearestNeighbor();

        int[] expectedPath = new int[]{3, 0, 1, 2};
        double expectedCost = 95.0;

        Solution result = NN.execute(3, 4);

        assertArrayEquals(expectedPath, result.getPath());
        assertEquals(expectedCost, result.getCost(), 0.000001);
    }

    @Test
    public void testExecuteEdgeCase1() {
        double[][] mycosts = {
            {0.0}
        };
        AlgorithmController.costs = mycosts;

        NearestNeighbor NN = new NearestNeighbor();

        int[] expectedPath = new int[]{0};
        double expectedCost = 0.0;

        Solution result = NN.execute(0, 1);

        assertArrayEquals(expectedPath, result.getPath());
        assertEquals(expectedCost, result.getCost(), 0.000001);
    }

    @Test
    public void testExecuteEdgeCase2() {
        double[][] mycosts = {
            {0.0, 10.0},
            {10.0, 0.0}
        };
        AlgorithmController.costs = mycosts;

        NearestNeighbor NN = new NearestNeighbor();

        int[] expectedPath = new int[]{0,1};
        double expectedCost = 20.0;

        Solution result = NN.execute(0, 2);

        assertArrayEquals(expectedPath, result.getPath());
        assertEquals(expectedCost, result.getCost(), 0.000001);
    }
}
