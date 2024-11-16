package model.algorithm;


import java.util.Arrays;
import java.util.Random;

public class AlgorithmController {
    protected static double[][] costs;

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

    // Aqui lanza excepcion de Algoritmo (reemplazar a throws Exception)
    public Object[] executeAlgorithm(AlgorithmsNames a) throws Exception {
        Solution solution;
        switch(a) {
            case NN:
                Algorithm algorithmNN = new NearestNeighbor();
                Random rand = new Random();
                solution = algorithmNN.execute(rand.nextInt(costs.length), costs.length);
                break;
            case HC:
                solution = null;
                break;
            default:
                solution = null;
                //THROW ERROR
                break;
        }
        double finalCost = ((-1)*solution.getCost()) + solution.getSize();
        return new Object[]{solution.getPath(), finalCost, a.toString()};
    }



}
