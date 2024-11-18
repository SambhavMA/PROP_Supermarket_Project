package model.algorithm;

import java.util.Arrays;

import static model.algorithm.AlgorithmController.costs;


/**
 * @author Sergio Polo y Sambhav Mayani Harlani (sergio.polo@estudiantat.upc.edu i sambhav.mayani@estudiantat.upc.edu)\n
 *
 * Clase solución
 * 
 * <p>Representa una solución al problema de ciclo hamiltoniano respecto al grafo representado
 * por la matriz de costes, la clase tiene acceso a está matriz (que está en <code>AlgorithmController</code>)</p>
 * 
*/
public class Solution {
    /**
     * Array de enteros con el camino resultante .
     * 
     * <p><i>NOTA: Hay una arista entre los nodos representados por el primer y último elemento del array, o sea,
     * en {0,2,1,3}, 3 y 0 son conexos</i></p>
     */
    private int[] path;
    /**
     * Coste total de la solución (suma de los costes de las aristas del "ciclo hamiltoniano")
     */
    private double cost;

    /**
     * Constructor base de Solution
     * 
     * <p>Inicializa los atributos de solution, <code>path</code> lo llena de '-1' y <code>cost</code> lo inicializa a 0</p>
     */
    public Solution() {
        this.path = new int[costs[0].length];
        Arrays.fill(path, -1);
        this.cost = 0;
    }

    /**
     * Constructor de Solution que lo crea una instancia de Solution con un path concreto
     * 
     * <p>Crea una instancia de Solution con un <code>path</code> específico, el <code>cost</code> lo calcula en base al path.</p>
     * 
     * @param path El path con el que queremos crear Solution
     */
    public Solution(int[] path) {
        this.path = Arrays.copyOf(path, path.length);
        calculateCost();
    }

    /**
     * Constructor de Solution que lo crea una instancia de Solution con un path concreto y su coste
     * 
     * <p>Crea una instancia de Solution con un <code>path</code> específico y su coste</p>
     * 
     * @param path El path con el que inicializamos Solution
     * @param cost El coste con el que inicializamos Solution
     * 
     * @see NearestNeighbor
     * @see HillClimbing
    */
    public Solution(int[] path, double cost) {
        this.path = Arrays.copyOf(path, path.length);
        this.cost = cost;
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
    
    /**
     * Retorna el coste entre 2 nodos de la matriz coste, a apartir de sus índices en <code>path</code>
     * 
     * @param x El primer indice de path
     * @param y El segundo indice de path
     * @return Retorna el coste entre x e y
    */
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
    
    //metodo necesario para swapAndUpdate
    //PRE: y > x+1 (cíclicamente, o sea si x = n, x+1 = 0), por lo tanto path.size > 4
    private void swap(int x, int y) { //swap de las aristas que tienen como "primer" (en el orden del path) vértice a i y a j 
        int n1, n2; //defino quien va primero, si x o y
        if (y > x) { n1 = x; n2 = y; }
        else { n1 = y; n2 = x; }
        
            for (int i = 1; i <= (n2-n1)/2; ++i) { //invertimos todo el sub-path de n1+1 a n2 incluidos
                int temp = path[n1+i];
                path[n1+i] = path[n2-i+1];
                path[n2-i+1] = temp;
            }
        
    }
    
    //metodo necesario para swapAndUpdate
    private void removeCost(int x1, int x2, int y1, int y2) { // x1-x2 es una arista y y1-y2 es otra
        
        cost -= costBetweenNodes(path[x1], path[x2]); 
        cost -= costBetweenNodes(path[y1], path[y2]);
        
    }
    
    //metodo necesario para swapAndUpdate
    private void addCost(int x1, int x2, int y1, int y2) {
        
        cost += costBetweenNodes(path[x1], path[x2]); 
        cost += costBetweenNodes(path[y1], path[y2]);
        
    }
    
    /**
     * Función que hace swap entre 2 aristas de <code>path</code> y actualiza el coste total (<code>cost</code>) de Solution
     * Usada como "2_opt" en el algoritmo de {@link HillClimbing}
     * 
     * <p>Intercambia las aristas de dos pares de nodos del ciclo hamiltoniano que representa la clase Solution, ejemplo:
     * 
     * REPRESENTACIÓN GRÁFICA DE {0,2,1,4,3}  ---swapAndUpdate de 2 y 4 (posiciones 1 y 4 del ciclo)--->  {0,2,3,4,1}
     *      2 --- 1 - 4
     *      |        /
     *      |       /
     *      |      /
     *      0 --- 3
     *
     *      2     1 - 4
     *      |\   /   /
     *      |  X    /
     *      |/   \ /
     *      0     3
     *   </p>
     * 
     * @param x Representa el primer nodo de la primera arista con la que haremos el intercambio
     * @param y Representa el primer nodo de la segunda arista con la que haremos el intercambio
     */

    //PRE: y > x+1 (cíclicamente, o sea si x = n, x+1 = 0), por lo tanto path.size > 4
    public void swapAndUpdate(int x, int y) {
        removeCost(x, (x+1)%path.length, y, (y+1)%path.length);
        addCost(x, y, (x+1)%path.length, (y+1)%path.length);
        swap(x, y);
    }
}
