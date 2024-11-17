package model.algorithm;


import java.util.Arrays;
import java.util.Random;

public class AlgorithmController {
    protected static double[][] costs;

    private Algorithm nearestNeighbor;
    private Algorithm hillClimbing;

    // Constructor principal
    public AlgorithmController(double[][] relationMatrix) {
        this(relationMatrix, new NearestNeighbor(), new HillClimbing());
    }

    // Constructor donde "inyectamos" la clase
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

    // Aqui lanza excepcion de Algoritmo (reemplazar a throws Exception)
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
