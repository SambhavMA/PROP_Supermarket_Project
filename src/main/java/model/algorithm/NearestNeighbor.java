package model.algorithm;

import java.util.ArrayList;
import java.util.Random;

public class NearestNeighbor extends Algorithm {

    public NearestNeighbor() {
        super(AlgorithmsNames.NN.toString(), "Algoritmo Nearest Neighbor:\n" +
                "Consiste en escoger la arista de menor coste desde un nodo.\n" +
                "Empezando en un nodo determinado hasta construyir un camino que los visite todos y vuelva a el.");
    }

    @Override
    public Solution execute(int initial, int nProducts) {
        double cost = 0.0;

        ArrayList<Integer> toVisit = new ArrayList<>();
        for (int i = 0; i < nProducts; i++) {
            toVisit.add(i);
        }

        int[] path = new int[nProducts];
        int index = 0; path[index] = initial; toVisit.remove(initial);

        while (!toVisit.isEmpty()) {
            Integer next = toVisit.get(0);
            double min = AlgorithmController.costs[path[index]][next];
            for (int i = 1; i < toVisit.size(); i++) {
                double c = AlgorithmController.costs[path[index]][toVisit.get(i)];
                if (c < min) {
                    min = c;
                    next = toVisit.get(i);
                }
            }
            path[++index] = next; toVisit.remove(next);
            cost += min;
        }

        return new Solution(path, cost);
    }
}
