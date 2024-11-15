package model.algorithm;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NearestNeighbor extends Algorithm {

    public NearestNeighbor() {
        super(AlgorithmsNames.NN.toString(), "Algoritmo Nearest Neighbor:\n" +
                "Consiste en escoger la arista de menor coste desde un nodo.\n" +
                "Empezando en un nodo determinado hasta construyir un camino que los visite todos y vuelva a el.");
    }

    @Override
    public Solution execute(int initial, int nProducts) {
        double cost = 0.0;

        LinkedList<Integer> toVisit = new LinkedList<Integer>();
        ListIterator<Integer> itCreation = toVisit.listIterator();
        for (int i = 0; i < nProducts; i++) {
            if (i != initial) itCreation.add(i);
        }

        int[] path = new int[nProducts];
        int indexPath = 0; path[indexPath] = initial;

        while (!toVisit.isEmpty()) {
            int indexList = 0;
            ListIterator<Integer> it = toVisit.listIterator(); ListIterator<Integer> nn = toVisit.listIterator();
            Integer actVal = it.next(); Integer nnVal = actVal;

            double min = AlgorithmController.costs[path[indexPath]][actVal];
            while (it.hasNext()) {
                actVal = it.next(); indexList++;
                double c = AlgorithmController.costs[path[indexPath]][actVal];
                if (c < min) {
                    min = c;
                    nn = toVisit.listIterator(indexList);
                    nnVal = actVal;
                }
            }
            path[++indexPath] = nnVal; nn.next(); nn.remove();
            cost += min;
        }

        cost += AlgorithmController.costs[path[indexPath]][initial];
        return new Solution(path, cost);
    }
}
