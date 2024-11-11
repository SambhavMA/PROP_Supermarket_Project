package model.algorithm;

import java.util.Arrays;

import static model.algorithm.AlgorithmController.costs;

public class Solution {
    private int[] path;
    private double cost;

    public Solution() {
        this.path = new int[costs[0].length];
        Arrays.fill(path, -1);
        this.cost = 0;
    }
    public Solution(int[] path) {
        this.path = Arrays.copyOf(path, path.length);
        calculateCost();
    }

    public Solution(int[] path, double cost) {
        this.path = Arrays.copyOf(path, path.length);
        this.cost = cost;
    }

    public void swapAndUpdate(int x, int y) {
        removeCost(x, y);
        swap(x, y);
        addCost(x, y);
    }

    //PRE: y > x+1 (cíclicamente, o sea si x = n, x+1 = 0), por lo tanto path.size > 4
    public void swap(int x, int y) { //swap de las aristas que tienen como "primer" (en el orden del path) vértice a i y a j 
        /*int temp = path[i];
        path[i] = path[j];
        path[j] = temp;*/
        int n1, n2; //defino quien va primero, si x o y
        if (y > x) { n1 = x; n2 = y; }
        else { n1 = y; n2 = x; }

        for (int i = 1; i <= n2-n1; ++i) { //invertimos todo el sub-path de n1+1 a n2 incluidos
            int temp = path[n1+i];
            path[n1+i] = path[n2-i+1];
            path[n2-i+1] = temp;
        }
    }

    public int[] getPath() {
        return path;
    }

    public double getCost() {
        return cost;
    }

    public int getSize() {
        return path.length;
    }

    private double costBetweenNodes(int x, int y) {
        return costs[x][y];
    }

    public double costBetweenPathNodes(int x, int y) {
        return costs[path[x]][path[y]];
    }

    /*path esta llena*/
    private void calculateCost() {
        this.cost = 0;
        for(int i = 0; i < path.length; i++) {
            this.cost += costBetweenNodes(path[i], path[(i+1)%path.length]);
        }
    }
    
    private void removeCost(int x, int y) {
        cost -= costBetweenNodes(path[x], path[(x+1)%path.length]); //x e y son los "primeros" vértices de sus aristas, de las aristas que haremos swap
        cost -= costBetweenNodes(path[y], path[(y+1)%path.length]);
    }

    private void addCost(int x, int y) {
        cost += costBetweenNodes(path[x], path[(x+1)%path.length]);
        cost += costBetweenNodes(path[y], path[(y+1)%path.length]);
    }

}
