package model.algorithm;

import java.util.Arrays;

public class Solution {
    private int[] path;
    private double cost;

    public Solution(int[] path) {
        this.path = Arrays.copyOf(path, path.length);
        calculateCost();
    }

    public Solution(int[] path, double cost) {
        this.path = Arrays.copyOf(path, path.length);
        this.cost = cost;
    }

    public void swapAndUpdateCost(int i, int j) {
        removeCost(i, j);
        swap(i, j);
        addCost(i, j);
    }

    public void swap(int i, int j) {
        int temp = path[i];
        path[i] = path[j];
        path[j] = temp;
    }

    public int[] getPath() {
        return path;
    }

    public double getCost() {
        return cost;
    }

    private double costBetweenNodes(int x, int y) {
        return AlgorithmController.costs[x][y];
    }

    private void calculateCost() {
        this.cost = 0;
        for(int i = 0; i < path.length; i++) {
            this.cost += costBetweenNodes(path[i], path[(i+1)%path.length]);
        }
    }

    private void removeCost(int i, int j) {
        cost -= costBetweenNodes(path[i], path[(i-1)%path.length]);
        cost -= costBetweenNodes(path[j], path[(j+1)%path.length]);
    }

    private void addCost(int i, int j) {
        cost += costBetweenNodes(path[i], path[(i-1)%path.length]);
        cost += costBetweenNodes(path[j], path[(j+1)%path.length]);
    }

}
