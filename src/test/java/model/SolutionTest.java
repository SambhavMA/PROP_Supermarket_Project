package test.model.algorithm;

import main.model.algorithm.Solution;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SolutionTest {

    private double[][] costsMock;

    @BeforeClass
    public void setUp() {
        costsMock = new double[][]{
            {0.0, 2.0, 3.0},
            {2.0, 0.0, 4.0},
            {3.0, 4.0, 0.0}
        };
        Solution.costs = costsMock;
    }



    @Test
    public testCostructorZeroParameter() {
        
        assertArrayEquals(0, , 0.000001);
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
        removeCost(x, (x+1)%path.length, y, (y+1)%path.length);
        addCost(x, y, (x+1)%path.length, (y+1)%path.length);
        swap(x, y);
    }

    //PRE: y > x+1 (cíclicamente, o sea si x = n, x+1 = 0), por lo tanto path.size > 4
    public void swap(int x, int y) { //swap de las aristas que tienen como "primer" (en el orden del path) vértice a i y a j 
        /*int temp = path[i];
        path[i] = path[j];
        path[j] = temp;*/
        int n1, n2; //defino quien va primero, si x o y
        if (y > x) { n1 = x; n2 = y; }
        else { n1 = y; n2 = x; }
        
        for (int i = 1; i <= (n2-n1)/2; ++i) { //invertimos todo el sub-path de n1+1 a n2 incluidos
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
    
    private void removeCost(int x1, int x2, int y1, int y2) { // x1-x2 es una arista y y1-y2 es otra
        
        cost -= costBetweenNodes(path[x1], path[x2]); 
        cost -= costBetweenNodes(path[y1], path[y2]);

    }

    private void addCost(int x1, int x2, int y1, int y2) {
        
        cost += costBetweenNodes(path[x1], path[x2]); 
        cost += costBetweenNodes(path[y1], path[y2]);

    }

}
