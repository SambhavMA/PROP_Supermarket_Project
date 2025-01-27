package model.algorithm.datastructures;

import org.junit.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.Pair;

import static org.junit.Assert.*;


public class GraphTest {

    private Graph graph;


    @Test
    public void testAddEdge() {
        graph = new Graph(5);
        graph.addEdge(0, 1, 10.0);

        //lista de adj del nodo 0
        List<Pair<Integer, Double>> adjList = graph.getAdjList().get(0);

        assertEquals(1, adjList.size());
        assertEquals(1, (int) adjList.get(0).first);
        assertEquals(10.0, adjList.get(0).second, 0.0001);
    }

    @Test
    public void testAddDoubleEdge() {
        graph = new Graph(5);
        graph.addDoubleEdge(0, 1, 5.0);

        List<Pair<Integer, Double>> adjList = graph.getAdjList().get(0);
        assertEquals(1, adjList.size());
        assertEquals(1, (int) adjList.get(0).first);
        assertEquals(5.0, adjList.get(0).second, 0.0001);

        assertEquals(1, adjList.size());
        assertEquals(1, (int) adjList.get(0).first);
        assertEquals(5.0, adjList.get(0).second, 0.0001);
    }

    @Test
    public void testEulerianCircuitDoubleTree() {
        graph = new Graph(5);
        //creamos un árbol con aristas duplicadas para probar
        graph.addDoubleEdge(0, 1, 1.0);
        graph.addDoubleEdge(1, 2, 1.0);
        graph.addDoubleEdge(1, 3, 1.0);
        graph.addDoubleEdge(3, 4, 1.0);

        ArrayList<Integer> circuit = graph.eulerianCircuitDoubleTree();

        assertEquals(circuit, new ArrayList<>(Arrays.asList(0,1,2,1,3,4,3,1,0)));
    }
}
