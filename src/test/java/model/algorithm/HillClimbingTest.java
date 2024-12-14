package model.algorithm;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.Before;

public class HillClimbingTest {
    /*
    @Test
    public void testExecuteOneInput() {
        /*double[][] mycosts = {
            {0.0, 10.0, 15.0, 20.0},
            {10.0, 0.0, 35.0, 25.0},
            {15.0, 35.0, 0.0, 30.0},
            {20.0, 25.0, 30.0, 0.0}
        };
        AlgorithmController.costs = mycosts; //suponemos que esta es la matriz de costes

        Solution mockSolutionInput = mock(Solution.class); //suponemos que la solución inicial es 1,2,3,0
        Solution[] mockSolutions = {mockSolutionInput};

        when(mockSolutions[0].getCost()).thenReturn(95.0);
        when(mockSolutions[0].getSize()).thenReturn(4);
        when(mockSolutions[0].costBetweenPathNodes(0,1)).thenReturn(35.0,25.0); //la primera vez el mock retornará 35 y la segunda 25
        when(mockSolutions[0].costBetweenPathNodes(2,3)).thenReturn(20.0,15.0);
        when(mockSolutions[0].costBetweenPathNodes(0,2)).thenReturn(10.0,10.0);
        when(mockSolutions[0].costBetweenPathNodes(3,1)).thenReturn(30.0,30.0);
        doNothing().when(mockSolutions[0]).swapAndUpdate(anyInt(), anyInt());
       /* when(mockSolutions[0].costBetweenPathNodes(anyInt(), anyInt())).thenAnswer(invocation -> {
                int i = invocation.getArgument(0);
                int j = invocation.getArgument(1);
                return mycosts[i][j];
            });
        //definimos que debería devolver swapandupdate en cada caso
        when(mockSolutions[0].getPath()).thenReturn(new int[]{1, 3, 2, 0});

        /*doAnswer(invocation -> {
            when(mockSolutions[0].getPath()).thenReturn(new int[] {1,3,2,0}); // Actualizar el mock para cambiar el path de la solucion mock, ya que la "solucion mock" devuelva el path con el "swap" hecho
            return null;
        }).when(mockSolutions[0]).swapAndUpdate(0, 2);

        HillClimbing HC = new HillClimbing();

        int[] expectedPath = new int[]{1, 3, 2, 0};
        double expectedCost = 80.0;

        Solution result = HC.execute(mockSolutions);

        assertArrayEquals(expectedPath, result.getPath());
        assertEquals(expectedCost, result.getCost(), 0.000001);
    }

    @Test
    public void testExecuteThreeInput() {
        double[][] mycosts = {
            {0.0, 10.0, 15.0, 20.0},
            {10.0, 0.0, 35.0, 25.0},
            {15.0, 35.0, 0.0, 30.0},
            {20.0, 25.0, 30.0, 0.0}
        };
        AlgorithmController.costs = mycosts;

        Solution mockSolutionInput1 = mock(Solution.class); //suponemos que la solución inicial es 1,2,3,0
        Solution mockSolutionInput2 = mock(Solution.class); //suponemos que la solución inicial es 2,1,0,3
        Solution mockSolutionInput3 = mock(Solution.class); //suponemos que la solución inicial es 3,1,0,2
        Solution[] mockSolutions = {mockSolutionInput1, mockSolutionInput2, mockSolutionInput3};

        when(mockSolutions[0].getCost()).thenReturn(95.0);
        when(mockSolutions[1].getCost()).thenReturn(95.0);
        when(mockSolutions[2].getCost()).thenReturn(80.0);

        when(mockSolutions[0].getSize()).thenReturn(4);


        when(mockSolutions[0].costBetweenPathNodes(0,1)).thenReturn(35.0,25.0);
        when(mockSolutions[0].costBetweenPathNodes(2,3)).thenReturn(20.0,15.0);
        when(mockSolutions[0].costBetweenPathNodes(0,2)).thenReturn(10.0,10.0);
        when(mockSolutions[0].costBetweenPathNodes(3,1)).thenReturn(30.0,30.0);
        doNothing().when(mockSolutions[0]).swapAndUpdate(anyInt(), anyInt());

        when(mockSolutions[1].costBetweenPathNodes(0,1)).thenReturn(35.0,15.0);
        when(mockSolutions[1].costBetweenPathNodes(2,3)).thenReturn(20.0,25.0);
        when(mockSolutions[1].costBetweenPathNodes(0,2)).thenReturn(30.0,30.0);
        when(mockSolutions[1].costBetweenPathNodes(3,1)).thenReturn(10.0,10.0);
        doNothing().when(mockSolutions[1]).swapAndUpdate(anyInt(), anyInt());

        when(mockSolutions[2].costBetweenPathNodes(0,1)).thenReturn(25.0);
        when(mockSolutions[2].costBetweenPathNodes(2,3)).thenReturn(10.0);
        when(mockSolutions[2].costBetweenPathNodes(0,2)).thenReturn(30.0);
        when(mockSolutions[2].costBetweenPathNodes(3,1)).thenReturn(10.0);
        doNothing().when(mockSolutions[2]).swapAndUpdate(anyInt(), anyInt());
        //definimos que debería devolver swapandupdate en cada caso
        when(mockSolutions[0].getPath()).thenReturn(new int[]{1, 3, 2, 0}); //en la ejecución debería devolver este path
        /*doAnswer(invocation -> {
            when(mockSolutions[0].getPath()).thenReturn(new int[] {1,3,2,0}); // Actualizar el mock para cambiar el path de la solucion mock, ya que la "solucion mock" devuelva el path con el "swap" hecho
            return null;
        }).when(mockSolutions[0]).swapAndUpdate(0, 2);

        when(mockSolutions[1].getPath()).thenReturn(new int[]{2, 0, 1, 3});
        /*doAnswer(invocation -> {
            when(mockSolutions[1].getPath()).thenReturn(new int[] {2,0,1,3});
            return null;
        }).when(mockSolutions[1]).swapAndUpdate(0, 2);

        when(mockSolutions[2].getPath()).thenReturn(new int[]{3, 1, 0, 2});
        /*doAnswer(invocation -> {
            when(mockSolutions[2].getPath()).thenReturn(new int[] {0,0,0,0});//así verificamos que no hace swap, ya que la solución que le hemos pasado ya es mínima
            return null;
        }).when(mockSolutions[2]).swapAndUpdate(anyInt(), anyInt());

        HillClimbing HC = new HillClimbing();

        int[] expectedPath = new int[]{1, 3, 2, 0}; //el algoritmo se quedará con la PRIMERA solución mínima encontrada
        double expectedCost = 80.0;

        Solution result = HC.execute(mockSolutions);

        assertArrayEquals(expectedPath, result.getPath());
        assertEquals(expectedCost, result.getCost(), 0.000001);
    }
    */
   
}