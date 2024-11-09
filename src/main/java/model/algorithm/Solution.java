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

    public void swap(int i, int j) {
        int temp = path[i];
        path[i] = path[j];
        path[j] = temp;
        updateCost(i, j);
    }

    public int[] getPath() {
        return path;
    }

    public double getCost() {
        return cost;
    }

    private void calculateCost() {
        this.cost = 0;
        for(int i = 0; i < path.length; i++) {
            this.cost += path[i];
        }
    }

    private void updateCost(int i, int j) {

    }

}
