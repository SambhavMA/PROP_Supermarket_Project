package model.algorithm;


import model.exceptions.AlgorithmException;

import java.util.*;

/**
 * @author Sergio Polo (sergio.polo@estudiantat.upc.edu)\n
 *
 * Clase del Algoritmo Nearest Neighbor
 * 
 * <p>El algoritmo Nearest Neighbor, a partir de un nodo inicial, busca el nodo conexo a este con menor coste, 
 * o sea, "su vecino mas cercano", así iterativamente hasta formar un ciclo hamiltoniano</p>
 * 
 */
public class NearestNeighbor extends Algorithm {

    public NearestNeighbor(List<Parameter> p, double[][] costs) {
        super(p, costs);
        super.description = "Algoritmo Nearest Neighbor:\nConsiste en escoger la arista de menor coste desde un nodo.\nEmpezando en un nodo determinado hasta construyir un camino que los visite todos y vuelva a el.";
        super.name = AlgorithmsNames.NearestNeighbor.toString();
    }

    public static ArrayList<Parameter> getParameters() {
        ArrayList<Parameter> parameters = new ArrayList<>();

        Random random = new Random();
        Parameter<Integer> p1 = new Parameter<>("Vertice inicial", "Vertice Inicial", random.nextInt(costs.length));
        parameters.add(p1);

        return parameters;
    }

    /**
     * Ejecuta el algoritmo sobre un nodo inicial y el numero de productos que tendra el resultado
     *
     * @return Solucion retornada, representa al ciclo hamiltoniano
     * 
     * @see Solution
     */
    @Override
    public Solution execute() throws AlgorithmException {
        Integer initial = null;
        int nProducts = costs.length;

        for (Parameter p : super.parameters) {
            if (p.getName().equals("Vertice inicial")) {
                initial = (Integer) p.getValue();
            }
        }

        if (initial != null) {
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

                double min = super.costs[path[indexPath]][actVal];
                while (it.hasNext()) {
                    actVal = it.next(); indexList++;
                    double c = super.costs[path[indexPath]][actVal];
                    if (c < min) {
                        min = c;
                        nn = toVisit.listIterator(indexList);
                        nnVal = actVal;
                    }
                }
                path[++indexPath] = nnVal; nn.next(); nn.remove();
                cost += min;
            }

            cost += super.costs[path[indexPath]][initial];
            return new Solution(path, cost);
        } else {
            throw new AlgorithmException("Lista de parametros incorrecta");
        }
    }
}
