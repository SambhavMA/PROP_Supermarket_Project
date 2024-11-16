import static org.junit.Assert.*;
import org.junit.Test;

import static org.mockito.Mockito.*;
import org.mockito.Mock;

public class NearestNeighborTest {

    @Test
    public void testConstructorCostsMatrix() {
        double[][] relationMatrixTest = {
            {1.0, 0.9952},
            {0.65, 0.0}
        };

        AlgorithmController myAlgortithmController = new AlgorithmController(relationMatrixTest);

        double[][] expectedCosts = {
            {0.0, 0.0048}, 
            {0.35, 1.0}
        };

        for (int i = 0; i < expectedCosts.length; i++) {
            assertArrayEquals(expectedCosts[i], myAlgortithmController.costs[i], 0.000001); //el 0.000001 es el margen de error, lo pongo ya que los double pueden tener problemas de precisión
        } 
    }

    @Test
    public void testGetAlgorithmsReturns() {
        double[][] relationMatrixTest = {
            {0.0, 0.0},
            {0.0, 0.0}
        };

        AlgorithmController controller = new AlgorithmController(relationMatrix);

        String[] algorithmNames = controller.getAlgorithms();

        String[] expectedNames = {"NN", "HC"};
        assertArrayEquals(expectedNames, algorithmNames);
    }

    @Test
    public void testExecuteAlgorithmNN() throws Exception {
        NearestNeighbor mockNN = mock(NearestNeighbor.class);
        Solution mockSolution = mock(Solution.class);

        when(mockNN.execute(anyInt(), anyInt())).thenReturn(mockSolution);
        when(mockSolution.getCost()).thenReturn(10.0);
        when(mockSolution.getSize()).thenReturn(15);
        when(mockSolution.getPath()).thenReturn(new int[]{0, 1, 2});

        double[][] relationMatrixTest = {
            {0.0, 0.0},
            {0.0, 0.0}
        };
        AlgorithmController controller = new AlgorithmController(relationMatrix);

        Object[] result = controller.executeAlgorithm(AlgorithmsNames.NN);

        assertArrayEquals(new int[]{0, 1, 2}, (int[]) result[0]);
        assertEquals(10, result[1]); 
        assertEquals("Nearest Neighbor", result[2]);
    }

    @Test
    public void testExecuteAlgorithmHC() throws Exception {
        NearestNeighbor mockNN = mock(NearestNeighbor.class);
        Solution mockSolution = mock(Solution.class);

        when(mockNN.execute(anyInt(), anyInt())).thenReturn(mockSolution);
        when(mockSolution.getCost()).thenReturn(10.0);
        when(mockSolution.getSize()).thenReturn(15);
        when(mockSolution.getPath()).thenReturn(new int[]{0, 1, 2});

        double[][] relationMatrixTest = {
            {0.0, 0.0},
            {0.0, 0.0}
        };
        AlgorithmController controller = new AlgorithmController(relationMatrix);

        Object[] result = controller.executeAlgorithm(AlgorithmsNames.NN);

        assertArrayEquals(new int[]{0, 1, 2}, (int[]) result[0]);
        assertEquals(10, result[1]); 
        assertEquals("Hill Climbing", result[2]);
    }

    @Test(expected = Exception.class)
    public void testExecuteAlgorithmUnsupportedAlgorithmThrowsException() throws Exception {
        double[][] relationMatrix = {{0.0, 0.0}, {0.0, 0.0}};
        AlgorithmController controller = new AlgorithmController(relationMatrix);

        controller.executeAlgorithm(null);
    }
}
