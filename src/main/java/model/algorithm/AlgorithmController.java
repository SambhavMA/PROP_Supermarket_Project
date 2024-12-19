package model.algorithm;

import model.exceptions.AlgorithmException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * @author Sergio Polo (sergio.polo@estudiantat.upc.edu)\n
 *
 * Controlador de las clases relacionadas con el algoritmo
 * 
 * <p> Sirve para aislar la lógica del package de algoritmo del resto del programa.
 * Recibe la tabla de similitudes, invierte las relaciones de esta (que pasan a ser costes en una matriz de costes).
 * para que así ejecutar los algoritmos pertinentes que devuelven un ciclo hamiltoniano con los costes
 * entre nodos más pequeño posible a partir del grafo que representa la matriz de costes. Este ciclo representa
 * la distribución de productos que queremos generar en el programa principal.</p>
*/
public class AlgorithmController {

    private double[][] costs;

    /**
     * Constructora de AlgorithmController
     * 
     * <p>Esta constructora, con dos parámetros extra con las instancias de los distintos tipos de algoritmo,
     * usada para hacer "mocks" de algoritmos en el testing </p>
     * 
     * @param relationMatrix Matriz con las relaciones entre productos
    */
    // Constructor donde "inyectamos" la clase Algorithm
    public AlgorithmController(double[][] relationMatrix) {
        costs = new double[relationMatrix.length][relationMatrix[0].length];
        for (int i = 0; i < relationMatrix.length; i++) {
            for (int j = 0; j < relationMatrix[0].length; j++) {
                costs[i][j] = 1 - relationMatrix[i][j];
            }
        }
    }

    public String[] getAlgorithms() {
        String[] algorithms = new String[AlgorithmsNames.values().length];
        for (int i = 0; i < algorithms.length; i++) {
            algorithms[i] = AlgorithmsNames.values()[i].toString();
        }
        return algorithms;
    }

    /**
     * Método para ejecutar un algoritmo
     * 
     * <p>El algoritmo Nearest Neighbor se ejecuta a partir de un nodo inicial alaeatorio.
     * El algoritmo de Hill Climbing se ejecuta a partir de 3 soluciones iniciales generadas por Nearest Neighbor
     * con nodo inicial aleatorio</p>
     * 
     * @param alg String con el tipo algoritmo seleccionado, {@link NearestNeighbor} o {@link HillClimbing}
     * @return Un Objeto con tres elementos:
     *    <ol>
     *         <li>El camino de la solución, es un array de enteros, donde cada entero representa a un producto.</li>
     *         <li>El costo total del camino.</li>
     *         <li>El nombre del algoritmo ejecutado.</li>
     *     </ol>
     * @throws Exception si ocurre un error al procesar el algoritmo seleccionado.
     * 
     * @see NearestNeighbor
     * @see HillClimbing
     * @see Solution
    */
    public AlgorithmControllerSolution executeAlgorithm(String alg) throws Exception {
        try {
            Algorithm.costs = costs;
            AlgorithmsNames algorithm = AlgorithmsNames.valueOf(alg);
            Solution solution = null;

            double durationInSeconds = 0;
            long startTime = 0, endTime = 0;
            switch(algorithm) {
                case NearestNeighbor:
                    ArrayList<Parameter> parametersNN = NearestNeighbor.getParameters();
                    NearestNeighbor nn = new NearestNeighbor(parametersNN, costs);
                    startTime = System.nanoTime();
                    solution = nn.execute();
                    endTime = System.nanoTime();
                    break;
                case HillClimbing:

                    ArrayList<Parameter> parametersHC = HillClimbing.getParameters();
                    HillClimbing hc = new HillClimbing(parametersHC, costs);
                    startTime = System.nanoTime();
                    solution = hc.execute();
                    endTime = System.nanoTime();
                    break;
                case Kruskal:
                    break;

                case Backtracking:
                    break;

                default:
                    solution = null;
                    break;
            }

            double finalCost = ((-1)*solution.getCost()) + solution.getSize();

            durationInSeconds = (endTime - startTime) / 1_000_000.0; // in ms.
            return new AlgorithmControllerSolution(solution.getPath(), finalCost, durationInSeconds, algorithm.toString());
        } catch (Exception e) {
            throw new AlgorithmException(e.getMessage());
        }
    }

}
