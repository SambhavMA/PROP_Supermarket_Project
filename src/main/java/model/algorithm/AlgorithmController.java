package model.algorithm;


import java.util.Arrays;
import java.util.Random;


/**
 * @author Sergio Polo 
 
 * Controlador de las clases relacionadas con el algoritmo
 * 
 * <p> Sirve para aislar la lógica del package de algoritmo del resto del programa.
 * Recibe la tabla de similitudes, invierte las relaciones de esta (que pasan a ser costes en una matriz de costes).
 * para que así ejecutar los algoritmos pertinentes que devuelven un ciclo hamiltoniano con los costes
 * entre nodos más pequeño posible a partir del grafo que representa la matriz de costes. Este ciclo representa
 * la distribución de productos que queremos generar en el programa principal.</p>
*/
public class AlgorithmController {
    protected static double[][] costs;

    private Algorithm nearestNeighbor;
    private Algorithm hillClimbing;

    /**
     * Primera constructora de AlgorithmController 
     * 
     * @param relationMatrix con las relaciones entre productos
    */
    public AlgorithmController(double[][] relationMatrix) {
        this(relationMatrix, new NearestNeighbor(), new HillClimbing());
    }

    /**
     * Segunda constructora de AlgorithmController
     * 
     * <p>Esta constructora, con dos parámetros extra con las instancias de los distintos tipos de algoritmo,
     * usada para hacer "mocks" de algoritmos en el testing </p>
     * 
     * @param relationMatrix Matriz con las relaciones entre productos
     * @param nearestNeighbor Instancia del algoritmo Nearest Neighbor
     * @param hillClimbing Instancia del algoritmo Hill Climbing
    */
    // Constructor donde "inyectamos" la clase Algorithm
    public AlgorithmController(double[][] relationMatrix, Algorithm nearestNeighbor, Algorithm hillClimbing) {
        costs = new double[relationMatrix.length][relationMatrix[0].length];
        for (int i = 0; i < relationMatrix.length; i++) {
            for (int j = 0; j < relationMatrix[0].length; j++) {
                costs[i][j] = 1 - relationMatrix[i][j];
            }
        }
        this.nearestNeighbor = nearestNeighbor;
        this.hillClimbing = hillClimbing;
    }

    public static double[][] getCosts() { //usada en el testing
        return costs;
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
     * @param a String con el tipo algoritmo seleccionado, {@link NearestNeighbor} o {@link HillClimbing}
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
    public Object[] executeAlgorithm(String a) throws Exception {
        AlgorithmsNames algorithm = AlgorithmsNames.valueOf(a);

        Solution solution;
        switch(algorithm) {
            case NN:
                //Algorithm nearestNeighbor = new NearestNeighbor();
                Random rand = new Random();
                solution = nearestNeighbor.execute(rand.nextInt(costs.length), costs.length);
                break;
            case HC:
                //Algorithm startingAlgorithmNN = new NearestNeighbor();
                Solution[] initialSolutions = new Solution[3];
                for (int i = 0; i < 3; i++) {
                    Random rand2 = new Random();
                    initialSolutions[i] = nearestNeighbor.execute(rand2.nextInt(costs.length), costs.length);
                }
                //Algorithm hillClimbing = new HillClimbing();
                solution = hillClimbing.execute(initialSolutions);
                break;
            default:
                solution = null;
                //THROW ERROR
                break;
        }
        double finalCost = ((-1)*solution.getCost()) + solution.getSize();
        return new Object[]{solution.getPath(), finalCost, algorithm.toString()};
    }



}
