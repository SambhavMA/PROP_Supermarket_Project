import static org.junit.Assert.*;
import org.junit.Test;

import static org.mockito.Mockito.*;
import org.mockito.Mock;

package test.model.algorithm;

public class AlgorithmControllerTest {

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
            assertArrayEquals(expectedCosts[i], AlgorithmController..getCosts()[i], 0.000001); //el 0.000001 es el margen de error, lo pongo ya que los double pueden tener problemas de precisión
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
        AlgorithmController controller = new AlgorithmController(relationMatrixTest);

        Object[] result = controller.executeAlgorithm(AlgorithmsNames.NN);

        assertArrayEquals(new int[]{0, 1, 2}, (int[]) result[0]);
        assertEquals(10, result[1]); 
        assertEquals("Nearest Neighbor", result[2]);
    }

    @Test
    public void testExecuteAlgorithmHC() throws Exception {
        /*no hago mock de initialSolutions ni de NN, ya que lo que devuelve la función execute
        algorithm depende úncamente de lo que devuelve la ejecución de algorithmHC, que depende
        únicamente de initialssolutions (y por lo tanto de las ejecuciones NN), pero como que en
        nuestro caso estamos haciendo un test unitario sobre algorithm controler no evaluamos cómo
        funciona la clase algorithm*/

        NearestNeighbor mockHC = mock(HillClimbing.class);
        Solution mockFinalSolution = mock(Solution.class);

        when(mockHC.execute(any(Solution[].class)).thenReturn(mockFinalSolution);
        when(mockFinalSolution.getCost()).thenReturn(10.0);
        when(mockFinalSolution.getSize()).thenReturn(15);
        when(mockFinalSolution.getPath()).thenReturn(new int[]{0, 1, 2});

        double[][] relationMatrixTest = {
            {0.0, 0.0},
            {0.0, 0.0}
        };
        AlgorithmController controller = new AlgorithmController(relationMatrixTest);

        //no hacemos mock de algorithmNames ya que simplemente es un enum y no una clase como tal
        Object[] result = controller.executeAlgorithm(AlgorithmsNames.HC);

        assertArrayEquals(new int[]{0, 1, 2}, (int[]) result[0]);
        assertEquals(10, result[1]); 
        assertEquals("Hill Climbing", result[2]);
    }

    @Test(expected = Exception.class)
    public void testExecuteAlgorithmThrowsException() throws Exception {
        double[][] relationMatrix = {{0.0, 0.0}, {0.0, 0.0}};
        AlgorithmController controller = new AlgorithmController(relationMatrix);

        controller.executeAlgorithm(null);
    }
}
