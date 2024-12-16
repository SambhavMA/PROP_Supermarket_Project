package model.algorithm;

import java.util.Vector;

/**
 *
 *
 * Clase que representa lo que retorna el Algorithm Controller despues de ejecutar un algoritmo
 * Lo que retorna es: ciclo hamiltoniano, coste del ciclo, tiempo de ejecución y algoritmo usado
 *
 * @author Sergio Polo
 *
 *  <p>Atributos internos:</p>
 *  <ul>
 *    <li><b>order</b>: El ciclo hamiltonianoo.</li>
 *    <li><b>cost</b>: Coste de la solucion dada.</li>
 *    <li><b>temps</b>: Tiempo de ejecucion del algoritmo.</li>
 *    <li><b>usedAlgorithm</b>: Algoritmo usado.</li>
 *  </ul>
 */
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
