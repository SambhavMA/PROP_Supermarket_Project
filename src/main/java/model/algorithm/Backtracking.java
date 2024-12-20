package model.algorithm;

import model.exceptions.AlgorithmException;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase backtracking, retorna una solucion optima al problema de TSP
 *
 * <p> Nota: Solo es viable usar este algoritmo para instancias pequenas del problema,
 * no se recomienda generar una distribucion de mas de 15 productos</p>
 */

public class Backtracking extends Algorithm {

    private double minCost;
    private ArrayList<Integer> minPath;

    /**
     * Constructora de la clase, crea una instancia de backtracking con el nombre, la tabla de costes y la descripción correspondientes.
     * Tambien inicializamos los atributos privados usados en el algoritmo.
     * @param p
     * @param costs
     */
    public Backtracking(List<Parameter> p, double[][] costs) {
        super(p, costs);
        super.description = "Algoritmo Backtracking para encontrar resultado óptimo.";
        super.name = AlgorithmsNames.Backtracking.toString();

        this.minCost = Double.MAX_VALUE;
        this.minPath = new ArrayList<>();
    }

    public static ArrayList<Parameter> getParameters() {
        return null;
    }

    private void backtracking(int actVertex, ArrayList<Integer> actPath, double actCost) {
        if (actPath.size() == super.costs.length) {
            double newTotalCost = actCost + super.costs[actPath.get(actPath.size()-1)][actPath.get(0)];

            if (newTotalCost < minCost) {
                minCost = newTotalCost;
                minPath = new ArrayList<>(actPath);
            }
        }

        else {
            for (int i = 0; i < super.costs.length; i++) {
                if (!actPath.contains(i)) {
                    double newCost = actCost + super.costs[actPath.get(actPath.size()-1)][i];
                    if (newCost < minCost) {
                        actPath.add(i);
                        backtracking(i, actPath, newCost);
                        actPath.remove(actPath.size() - 1);
                    }
                }
            }
        }
    }

    public Solution execute() throws AlgorithmException {
        ArrayList<Integer> initialPath = new ArrayList<>();
        initialPath.add(0);
        backtracking(0, initialPath , 0.0);

        int[] hamiltonianPath = new int[minPath.size()];


        for (int i = 0; i < minPath.size(); i++) {
            hamiltonianPath[i] = minPath.get(i);
        }

        return new Solution(hamiltonianPath, minCost);
    }
}
