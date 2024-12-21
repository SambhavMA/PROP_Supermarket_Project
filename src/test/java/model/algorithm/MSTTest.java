package model.algorithm;

import model.algorithm.datastructures.Graph;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class MSTTest {

    private static boolean isArrayEqualToAny(int[] result, int[]... arrays) {
        for (int[] array : arrays) {
            if (Arrays.equals(result, array)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testExecuteConsecutiveDuplicates() {
        double[][] mycosts = {
                {0.0, 10.0, 15.0, 20.0},
                {10.0, 0.0, 35.0, 25.0},
                {15.0, 35.0, 0.0, 30.0},
                {20.0, 25.0, 30.0, 0.0}
        }; //en verdad la matriz de costes dara igual, todo deberia depender de lo que devuelve la funcion
        //eulerianCircuitDoubleTree() de Graph

        Graph mockGraph = mock(Graph.class);

        //ponemos que genere un circuito arbitrario
        when(mockGraph.eulerianCircuitDoubleTree()).thenReturn(new ArrayList<>(Arrays.asList(0,1,2,2,3)));

        doNothing().when(mockGraph).addDoubleEdge(anyInt(),anyInt(),anyInt());


        MST mst = new MST(null, mycosts, mockGraph);

        int[] expectedPath = new int[]{0, 1, 2, 3};

        Solution result = mst.execute();

        assertArrayEquals(expectedPath, result.getPath());
    }

    @Test
    public void testExecuteSeparatedDuplicates() {
        double[][] mycosts = {
                {0.0, 10.0, 15.0, 20.0},
                {10.0, 0.0, 35.0, 25.0},
                {15.0, 35.0, 0.0, 30.0},
                {20.0, 25.0, 30.0, 0.0}
        }; //en verdad la matriz de costes dara igual, todo deberia depender de lo que devuelve la funcion
        //eulerianCircuitDoubleTree() de Graph

        Graph mockGraph = mock(Graph.class);

        //ponemos que genere un circuito arbitrario
        when(mockGraph.eulerianCircuitDoubleTree()).thenReturn(new ArrayList<>(Arrays.asList(0,1,2,3,1,2)));

        doNothing().when(mockGraph).addDoubleEdge(anyInt(),anyInt(),anyInt());


        MST mst = new MST(null, mycosts, mockGraph);

        //todos los posibles resultados (ya que pasar de euleriano a hamiltoniano es aleatorio)
        int[] expectedPath1 = new int[]{0, 1, 2, 3};
        int[] expectedPath11 = new int[]{1, 2, 3, 0};
        int[] expectedPath12 = new int[]{2, 3, 0, 1};
        int[] expectedPath13 = new int[]{3, 0, 1, 2};
        int[] expectedPath2 = new int[]{0, 1, 3, 2};
        int[] expectedPath21 = new int[]{1, 3, 2, 0};
        int[] expectedPath22 = new int[]{3, 2, 0, 1};
        int[] expectedPath23 = new int[]{2, 0, 1, 3};
        int[] expectedPath3 = new int[]{0, 2, 3, 1};
        int[] expectedPath31 = new int[]{2, 3, 1, 0};
        int[] expectedPath32 = new int[]{3, 1, 0, 2};
        int[] expectedPath33 = new int[]{1, 0, 2, 3};
        int[] expectedPath4 = new int[]{0, 3, 1, 2};
        int[] expectedPath41 = new int[]{3, 1, 2, 0};
        int[] expectedPath42 = new int[]{1, 2, 0, 3};
        int[] expectedPath43 = new int[]{2, 0, 3, 1};



        Solution result = mst.execute();

        assertTrue(isArrayEqualToAny(result.getPath(), expectedPath1, expectedPath11, expectedPath12, expectedPath13,
                                                       expectedPath2, expectedPath21, expectedPath22, expectedPath23,
                                                       expectedPath3, expectedPath31, expectedPath32, expectedPath33,
                                                       expectedPath4, expectedPath41, expectedPath42, expectedPath43));
    }
}
