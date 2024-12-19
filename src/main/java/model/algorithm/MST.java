package model.algorithm;

import model.algorithm.datastructures.Edge;
import model.algorithm.datastructures.Graph;
import model.algorithm.datastructures.UnionFind;

import utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


/**

 *
 * Clase del algoritmo de MST, calcula un Minimum Spanning Tree con Kruskal y a partir del MST crea un ciclo hamiltoniano
 *
 * @author Sambhav Mayani Harlani (sambhav.mayani@estudiantat.upc.edu)
 *
 */
public class MST {
    //private double[][] costs;

    MST(double[][] costs) {
        this.costs = costs;
    }

    public MST() {
        super(AlgorithmsNames.MST.toString(), "Algoritmo Minimum Spanning ");
    }

    /**
     * Función execute: Ejecuta el algoritmo
     *
     * nota: la implementacion no es correcta, falta aplicar una correccion,
     * (lo digo para que no se evalue el codigo del JavaDoc de la segunda entrega, ya que en la segunda entrega no se entrega codigo)
     * @return Retorna una instancia de Solution, que representa un ciclo hamiltoniano
     */
    @Override
    public Solution execute() {

        //KRUSKAL
        int n = costs.length;
        Edge[] edgesArray = new Edge[n*(n-1)/2];

        int edgeIndex = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double cost = costs[i][j];
                edgesArray[edgeIndex++] = new Edge(i, j, cost);
            }
        }
        Arrays.sort(edgesArray);

        UnionFind UF = new UnionFind(n);
        Graph G = new Graph(n);
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

        int[] lastInstancePos = new int[n]; //para cada vertice (producto) me guardo la "posicion en el circuito" de
        //la ultima instancia que hemos visto del vertice en el circuito

        //inicializamos el array de enteros a -1
        Arrays.fill(lastInstancePos, -1);




        circuit.remove(circuit.size()-1); //quitamos el ultimo elemento, que por construccion del circuito es igual al primero

        //recorremos el circuito eliminando los duplicados de la manera en que tengamos menor coste total posible en el ciclo hamiltoniano resultante
        for (int pos = 0; pos < circuit.size(); pos++) {
            int act = circuit.get(pos);
            int lastInstPos = lastInstancePos[act]; //Posición de la última instancia recorrida de pos

            if (lastInstPos == -1) { //si es la primera vez que visitamos la instancia (si es el penúltimo o último elemnto no hace falta hacer nada, porque sabemos que act que ya no se va a repetir en lo que queda de circuito)
                if (pos < circuit.size()-2) {

                    lastInstancePos[act] = pos;
                }
            }

            else {
                int prevActPos, nextActPos;
                boolean prevNextActEq = false;
                //evidentemente supongo que el circuito es de tamaño mayor a 1
                int prevLastInstPos, nextLastInstPos;
                boolean prevNextLastEq = false;

                if (lastInstPos == 0) { //evaluamos los casos base, se podria hacer que el prev fuese (n - i + n)%n y que el next fuese (i+1)%n, pero veo esto mas legible y es un poco mas eficiente
                    prevLastInstPos = circuit.size() - 1;
                    nextLastInstPos = lastInstPos + 1;
                } else {
                    prevLastInstPos = lastInstPos - 1;
                    nextLastInstPos = lastInstPos + 1;
                }
                if (prevLastInstPos == nextLastInstPos) { // A-X-A, si elimino X me queda A-A (lo cual no queremos contar)
                    --prevLastInstPos; ++nextLastInstPos;
                    prevNextLastEq = true;
                }

                if (pos == circuit.size()-1) {
                    prevActPos = pos-1;
                    nextActPos = 0;
                }
                else {
                    prevActPos = pos-1;
                    nextActPos = pos+1;
                }
                if (prevActPos == nextActPos) { // A-X-A, si elimino X me queda A-A (lo cual no queremos contar)
                    --prevActPos; ++nextActPos;
                    prevNextActEq = true;
                }

                double costIfDeleteLastInstance = costIfDeleteInCircuit(circuit.get(prevLastInstPos), circuit.get(lastInstPos), circuit.get(nextLastInstPos));;
                double costIfDeleteAct = costIfDeleteInCircuit(circuit.get(prevActPos), act, circuit.get(nextActPos));

                //si merece mas la pena eliminar la ultima instancia recorrida en vez de la instancia actual, eliminamos la ultima intancia recorrida
                if (costIfDeleteAct < costIfDeleteLastInstance) {
                    lastInstancePos[act] = pos; //actualizamos la ultima instancia
                    circuit.remove(lastInstPos);

                    if (prevNextLastEq) circuit.remove(prevLastInstPos);
                } // si no eliminamos el actual
                else {
                    circuit.remove(pos);

                    if (prevNextActEq) circuit.remove(prevActPos);
                }

                if (!prevNextLastEq && !prevNextActEq) --pos;

            }


        }

        int[] hamiltonianPath = new int[circuit.size()];


        for (int i = 0; i < circuit.size(); i++) {
            hamiltonianPath[i] = circuit.get(i);
        }


        return new Solution(hamiltonianPath);

    }

}
