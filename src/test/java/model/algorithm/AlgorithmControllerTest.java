package model.algorithm;

import java.util.Arrays;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.Before;

import model.algorithm.HillClimbing;
import model.algorithm.Solution;

//se tendria que usar PowerMockito :-(
public class AlgorithmControllerTest {
/*
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
            assertArrayEquals(expectedCosts[i], myAlgortithmController.getCosts()[i], 0.000001); //el 0.000001 es el margen de error, lo pongo ya que los double pueden tener problemas de precisión
        }
    }

    @Test
    public void testGetAlgorithmsReturns() {
        double[][] relationMatrixTest = {
            {0.0, 0.0},
            {0.0, 0.0}
        };

        AlgorithmController controller = new AlgorithmController(relationMatrixTest);

        String[] algorithmNames = controller.getAlgorithms();

        String[] expectedNames = {"NearestNeighbor", "HillClimbing","MST","Backtracking"};
        assertArrayEquals(expectedNames, algorithmNames);
    }

    @Test
    public void testExecuteAlgorithmNN() throws Exception {
        NearestNeighbor mockNN = mock(NearestNeighbor.class);
        Solution mockSolution = mock(Solution.class);


        when(mockNN.execute()).thenReturn(mockSolution);
        when(mockSolution.getCost()).thenReturn(10.0);
        when(mockSolution.getSize()).thenReturn(15);
        when(mockSolution.getPath()).thenReturn(new int[]{0, 1});


        double[][] relationMatrixTest = {
            {0.0, 0.0},
            {0.0, 0.0} //ponemos cualquier valor en relationMatrix porque da igual en la ejecución del test por los mocks
        };
        AlgorithmController controller = new AlgorithmController(relationMatrixTest);

        AlgorithmControllerSolution result = controller.executeAlgorithm("NearestNeighbor");

        System.out.println("Result order: " + Arrays.toString(result.getOrder()));

        assertArrayEquals(new int[]{0, 1}, result.getOrder());
        assertEquals(5.0, result.getCost(), 0.000001);
        assertEquals("NearestNeighbor", result.getUsedAlgorithm());
    }

   /* @Test
    public void testExecuteAlgorithmHC() throws Exception {
        no hago mock de initialSolutions ni de NN, ya que lo que devuelve la función execute
        algorithm depende úncamente de lo que devuelve la ejecución de algorithmHC, que depende
        únicamente de initialssolutions (y por lo tanto de las ejecuciones NN), pero como que en
        nuestro caso estamos haciendo un test unitario sobre algorithm controler no evaluamos cómo
        funciona la clase algorithm

        HillClimbing mockHC = mock(HillClimbing.class);
        Solution mockFinalSolution = mock(Solution.class);

        when(mockHC.execute(any(Solution[].class))).thenReturn(mockFinalSolution);
        when(mockFinalSolution.getCost()).thenReturn(10.0);
        when(mockFinalSolution.getSize()).thenReturn(15);
        when(mockFinalSolution.getPath()).thenReturn(new int[]{0, 1});

        NearestNeighbor mockNN = mock(NearestNeighbor.class);
        Solution mockInitialSolution = mock(Solution.class);
        when(mockNN.execute(anyInt(), anyInt())).thenReturn(mockInitialSolution);

        double[][] relationMatrixTest = {
            {0.0, 0.0},
            {0.0, 0.0}
        };
        AlgorithmController controller = new AlgorithmController(relationMatrixTest, mockNN, mockHC);
        //nos dan igual las soluciones iniciales porque en ultima instancia lo que importa es lo que retorne la clase HC, de la cual tenemos mock

        //no hacemos mock de algorithmNames ya que simplemente es un enum y no una clase como tal
        Object[] result = controller.executeAlgorithm("HC");

        assertArrayEquals(new int[]{0, 1}, (int[]) result[0]);
        assertEquals(5.0, result[1]);
        assertEquals("HC", result[2]);
    }*/


}
