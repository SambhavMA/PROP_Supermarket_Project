package model.algorithm;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

/*
 * @author Sergio Polo (sergio.polo@estudiantat.upc.edu)
 *
 * Clase del Algoritmo Nearest Neighbor
 * 
 * <p>El algoritmo Nearest Neighbor, a partir de un nodo inicial, busca el nodo conexo a este con menor coste, 
 * o sea, "su vecino mas cercano", así iterativamente hasta formar un ciclo hamiltoniano</p>
 * 
 */
public class NearestNeighbor extends Algorithm {

    public NearestNeighbor() {
        super(AlgorithmsNames.NN.toString(), "Algoritmo Nearest Neighbor:\n" +
                "Consiste en escoger la arista de menor coste desde un nodo.\n" +
                "Empezando en un nodo determinado hasta construyir un camino que los visite todos y vuelva a el.");
    }

    /*
     * Ejecuta el algoritmo sobre un nodo inicial y el numero de productos que tendra el resultado
     * 
     * @param initial El nodo inicial sobre el que empezaremos a iterar
     * @param nProducts El numero de nodos que tendra la solucion que retornaremos
     * @return Solucion retornada, representa al ciclo hamiltoniano
     * 
     * @see Solution
     */
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
