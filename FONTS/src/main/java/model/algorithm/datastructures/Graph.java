package model.algorithm.datastructures;

import utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 *
 * Clase Graph, sirve para representar un grafo con listas de adyacencia.
 *
 * @author Sambhav Mayani Harlani (sambhav.mayani@estudiantat.upc.edu)
 *
 *  <p>Atributos internos:</p>
 *  <ul>
 *    <li><b>adjList</b>: Las listas de adyacencia.</li>
 *  </ul>
 */
public class Graph {
    private List<List<Pair<Integer, Double>>> adjList; //si no usas el cost quitalo!!!

    /**
     * Constructora de Graph
     * @param n
     */
    public Graph(int n) {
        adjList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public List<List<Pair<Integer, Double>>> getAdjList() {
        return adjList;
    }

    /**
     * Añade una arista dirigida desde el vértice src hacia el vértice dst
     *
     * @param src
     * @param dst
     * @param cost
     */
    public void addEdge(int src, int dst, double cost) {
        adjList.get(src).add(new Pair<>(dst, cost));
    }

    /**
     * Añade una arista doble (o no dirigida) entre los vertices x e y
     * @param x
     * @param y
     * @param cost
     */
    public void addDoubleEdge(int x, int y, double cost) {
        addEdge(x, y, cost);
        addEdge(y, x, cost);
    }

    /**
     * Sirve para construir un circuito euleriano en un Grafo que es un arbol con las aristas duplicadas
     * PRE: Que el grafo sea un arbol con las aristas duplicadas
     * @return Retorna el circuito euleriano
     */
    public ArrayList<Integer> eulerianCircuitDoubleTree() {
        int n = adjList.size();

        boolean[][] visitedEdges = new boolean[n][n]; //iniicalizada toda a false automaticamente por java

        ArrayList<Integer> circuit = new ArrayList<>();

        Stack<Integer> stack = new Stack<>(); //inicializamos la pila con la que haremos el DFS
        stack.push(0);

        while (!stack.isEmpty()) {
            int act = stack.pop();
            circuit.add(act); //para el test verifica que el último == primero

            boolean found = false;
            int returnVertex = -1; //si no hay un camino que pueda seguir recorriendo vuelvo por donde he venido
            for (Pair<Integer,Double> neighbor : adjList.get(act)) {
                if (!visitedEdges[act][neighbor.first]) { //tengo un camino de ida no visitado
                    if (!visitedEdges[neighbor.first][act]) { //tengo un camino de ida y vuelta no visitados
                        stack.push(neighbor.first);
                        visitedEdges[act][neighbor.first] = true;
                        if (!found) found = true;
                        break;
                    }
                    else { //tengo un camino de ida no visitado pero una arista que "viene hacia mi vertice actual" esta visitado (es por donde he venido)
                        returnVertex = neighbor.first;
                    }
                }
                //si la arista "de ida" esta visitada, ya he visitado ese camino
            }

            if (!found && returnVertex != -1) { //si no me quedan vertices nuevos que explorar, vuelvo por donde he venido
                stack.push(returnVertex);
            }
        }

        return circuit;

    }

}
