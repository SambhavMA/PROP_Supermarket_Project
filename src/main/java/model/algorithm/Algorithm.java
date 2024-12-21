package model.algorithm;

import model.exceptions.AlgorithmException;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Clase Algoritmo, es una clase abstracta que representa a un algoritmo
 *
 * @author Sergio Polo (sergio.polo@estudiantat.upc.edu)\n
 * 
 * <p>Las implementaciones de los distintos tipos de algoritmos estan en sus respectivas clases</p>
 *
 *  <p>Atributos internos:</p>
 *  <ul>
 *    <li><b>name</b>: Nombre del algoritmo.</li>
 *    <li><b>description</b>: Descripcion del algoritmo.</li>
 *    <li><b>parameters</b>: Parametros de entrada del algoritmo.</li>
 *    <li><b>costs</b>: La matriz de costes entre los productos.</li>
 *  </ul>
 *
 * @see NearestNeighbor
 * @see HillClimbing
 * @see MST
 * @see Backtracking
 */
public abstract class Algorithm {
    /**
     * Nombre del algoritmo usado
     */
    protected String name;
    /**
     * Descripción. del algoritmo usado
     */
    protected String description;

    protected List<Parameter> parameters;
    protected static double[][] costs;

    protected Algorithm(List<Parameter> p, double[][] costs) {
        this.parameters = p;
        this.costs = costs;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Funcion execute, ejecuta el algoritmo.
     * @return Una solucion
     * @throws AlgorithmException
     */
    public abstract Solution execute() throws AlgorithmException;

    /**
     * Funcion estatica abstracta par obtener los parametros que necesita un algoritmo para ejecutarse.
     * Por defecto no retorna nada
     * @return Parametros del algoritmo
     */
    public static List<Parameter> getParameters() {
        return null;
    }

    /*
    /**
     * Ejecuta un algoritmo, esta función es usada en el algoritmo Nearest Neighbor
     * @see NearestNeighbor
     *
    public Solution execute(int initial, int nProductes) { return null; };

    /**
     * Ejecuta un algoritmo, esta función es usada en el algoritmo Hill CLimbing
     * @see HillClimbing
     *
    public Solution execute(Solution solutions[]) { return null; };
     */
}
