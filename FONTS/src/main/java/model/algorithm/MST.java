package model.algorithm;

import model.algorithm.datastructures.Edge;
import model.algorithm.datastructures.Graph;
import model.algorithm.datastructures.UnionFind;

import model.exceptions.AlgorithmException;

import java.util.*;


/**

 *
 * Clase del algoritmo de MST, calcula un Minimum Spanning Tree con Kruskal y a partir del MST crea un ciclo hamiltoniano
 *
 * @author Sambhav Mayani Harlani (sambhav.mayani@estudiantat.upc.edu)
 *
 */
public class MST extends Algorithm {
    private Graph G; //en un principio no estaba, no es necesario (se podria hacer new Graph dentro
    //de la funcion execute(), que es lo que habia antes, pero para el testing (poder mockear la clase Graph con nuestra version de Mockito)
    //es necesario

    public MST(List<Parameter> p, double[][] costs) {
        super(p, costs);
        super.description = "Algoritmo de 2-opt para encontrar una solución aproximada a partir del MST.";
        super.name = AlgorithmsNames.MST.toString();
        G = new Graph(costs.length);
    }

    /**
     * Constructora para poder inyectar un "Graph" en MST, usada en el testing
     * @param p
     * @param costs
     */
    public MST(List<Parameter> p, double[][] costs, Graph myGraph) {
        super(p, costs);
        super.description = "Algoritmo de 2-opt para encontrar una solución aproximada a partir del MST.";
        super.name = AlgorithmsNames.MST.toString();
        G = myGraph;
    }

        public static ArrayList<Parameter> getParameters() {
        ArrayList<Parameter> parameters = new ArrayList<>();
        return parameters;
    }

    /**
     * Funcion execute, ejecuta el algoritmo y retorna una aproximacion al problema.
     *
     * Consiste en generar el MST del grafo que representa la matriz de costes,
     * a partir de ese MST se encuentra un ciclo euleriano,
     * y finalmente transformar aleatoriamente el ciclo euleriano en hamiltoniano aleatoriamente.
     *
     * nota: la implementacion no es correcta, falta aplicar una correccion,
     * (lo digo para que no se evalue el codigo del JavaDoc de la segunda entrega, ya que en la segunda entrega no se entrega codigo)
     * @return Retorna una instancia de Solution, que representa un ciclo hamiltoniano
     */
    @Override
    public Solution execute() throws AlgorithmException {
        try {
            //KRUSKAL
            int n = super.costs.length;
            Edge[] edgesArray = new Edge[n * (n - 1) / 2];

            int edgeIndex = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    double cost = super.costs[i][j];
                    edgesArray[edgeIndex++] = new Edge(i, j, cost);
                }
            }
            Arrays.sort(edgesArray);

            UnionFind UF = new UnionFind(n);
            int m = edgesArray.length;

            for (int i = 0; i < m; i++) {
                Edge e = edgesArray[i];
                if (UF.find(e.getI()) != UF.find(e.getJ())) {
                    UF.union(e.getI(), e.getJ());
                    G.addDoubleEdge(e.getI(), e.getJ(), e.getCost());
                }
            }

            //BUSCAMOS CIRCUITO EULERIANO
            ArrayList<Integer> circuit = G.eulerianCircuitDoubleTree();


            //HACEMOS CICLO HAMILTONIANO, eliminando los nodos repetidos del circuito euleriano

            List<Integer> minHamiltonianCycle = null; // ciclo hamiltoniano con coste minimo
            double minCost = Double.MAX_VALUE; // inicializamos el costo minimo con el valor maximo
            for (int k = 0; k < 3; ++k) {
                Random rand = new Random();

                boolean[] visited = new boolean[n];
                List<Integer> hamiltonianCycle = new ArrayList<>();
                int currentPos = rand.nextInt(circuit.size()); // Posición inicial aleatoria

                for (int i = 0; i < circuit.size(); i++) {
                    int currentNode = circuit.get(currentPos);

                    if (!visited[currentNode]) {
                        hamiltonianCycle.add(currentNode);
                        visited[currentNode] = true;
                    }

                    currentPos = (currentPos + 1) % circuit.size(); //avanzamos de forma  cíclica
                }

                //costo total del ciclo hamiltoniano
                double actCost = 0.0;
                for (int i = 0; i < hamiltonianCycle.size() - 1; i++) {
                    actCost += super.costs[hamiltonianCycle.get(i)][hamiltonianCycle.get(i + 1)];
                }
                actCost += super.costs[hamiltonianCycle.get(hamiltonianCycle.size() - 1)][hamiltonianCycle.get(0)];

                //nos quedamos con el mínimo de los 3 aleatorios
                if (actCost < minCost) {
                    minCost = actCost;
                    minHamiltonianCycle = new ArrayList<>(hamiltonianCycle);
                }
            }

            int[] hamiltonianPath = new int[minHamiltonianCycle.size()];


            for (int i = 0; i < minHamiltonianCycle.size(); i++) {
                hamiltonianPath[i] = minHamiltonianCycle.get(i);
            }

            return new Solution(hamiltonianPath);

        } catch (Exception e) {
            throw new AlgorithmException("Error en la ejecucion del algoritmo");
        }
    }



}

