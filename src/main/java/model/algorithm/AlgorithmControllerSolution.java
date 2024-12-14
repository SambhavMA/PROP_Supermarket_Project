package model.algorithm;

import java.util.Vector;

public class AlgorithmControllerSolution {
    private int[] order;
    private double cost;
    private double temps;
    private String usedAlgorithm;

    public AlgorithmControllerSolution(int[] order, double cost, double temps, String usedAlgorithm) {
        this.order = order;
        this.cost = cost;
        this.temps = temps;
        this.usedAlgorithm = usedAlgorithm;
    }

    public int[] getOrder() {
        return order;
    }

    public double getCost() {
        return cost;
    }

    public double getTemps() {
        return temps;
    }

    public String getUsedAlgorithm() {
        return usedAlgorithm;
    }
}
