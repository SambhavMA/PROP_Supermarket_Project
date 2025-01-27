package model.algorithm;

import model.exceptions.AlgorithmException;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class BacktrackingTest {

    //No testeamos los casos en los que la matriz es de 1 o 2 elementos
    //ya que, primero, no tiene sentido calcular esas matrices, segundo, el programa no las permite

    @Test
    public void testExecuteSimpleCase() {
        double[][] costs = //del dataset del raco
                {
                        { 0, 10,  15,  20 },
                        { 10, 0,  35,  25 },
                        { 15, 35,  0,  30 },
                        { 20, 25, 30,   0 }
                };

        List<Parameter> parameters = new ArrayList<>();
        Backtracking backtracking = new Backtracking(parameters, costs);

        try {
            Solution solution = backtracking.execute();

            assertNotNull(solution);
            assertEquals(80.0, solution.getCost(), 0.01);
        } catch (AlgorithmException e) {
            fail(); //no esperabamos una excepcion
        }
    }

    @Test
    public void testExecuteComplexCase() {
        double[][] costs = //del dataset del raco
                {
                        {  0,29,82,46,68,52,72,42,51},
                        { 29, 0,55,46,42,43,43,23,23},
                        { 82,55, 0,68,46,55,23,43,41},
                        { 46,46,68, 0,82,15,72,31,62},
                        { 68,42,46,82, 0,74,23,52,21},
                        { 52,43,55,15,74, 0,61,23,55},
                        { 72,43,23,72,23,61, 0,42,23},
                        { 42,23,43,31,52,23,42, 0,33},
                        { 51,23,41,62,21,55,23,33, 0}
                };


        List<Parameter> parameters = new ArrayList<>();
        Backtracking backtracking = new Backtracking(parameters, costs);

        try {
            Solution solution = backtracking.execute();

            assertNotNull(solution);
            assertEquals(246.0, solution.getCost(), 0.01);
        } catch (AlgorithmException e) {
            fail(); //no esperabamos una excepcion
        }
    }
}
