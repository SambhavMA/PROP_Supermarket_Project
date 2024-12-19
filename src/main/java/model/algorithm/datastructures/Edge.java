package model.algorithm.datastructures;

/**
 *
 *
 * Clase Edge, contiene los 2 extremos de una arista y el coste de esta,
 * la usamos al principio de Kruskal para ordenar las aristas crecientemente,
 * esto gracias a que implementamos la función compareTo para luego poder llamar a la función Arrays.sort()
 *
 * @author Sambhav Mayani Harlani (sambhav.mayani@estudiantat.upc.edu)
 *
 *  <p>Atributos internos:</p>
 *  <ul>
 *    <li><b>i, j</b>: Los nodos a los extremos de la arista.</li>
 *    <li><b>cost</b>: El coste de la arista</li>
 *  </ul>
 */
public class Edge implements Comparable<Edge> {
    private int i, j;
    private double cost;

    /**
     * Constructora de Edge
     * @param i
     * @param j
     * @param cost
     */
    public Edge(int i, int j, double cost) {
        this.i = i;
        this.j = j;
        this.cost = cost;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public double getCost() {
        return cost;
    }

    /**
     * Función compareTo, establece como comparar 2 instancias de Edge
     * @param a the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Edge a) {
        return Double.compare(this.cost, a.cost);
    }

}
