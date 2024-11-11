package model.algorithm;

import java.util.ArrayList;

import model.similarity.SimilarityTable;



public class HillClimbing extends Algorithm {

    public HillClimbing() {
        super(AlgorithmsNames.HC.toString(), "Algoritmo Hill Climbing para optimización.");
    }

    @Override
    public Solution execute(Solution solution) {
        int size = solution.getSize();
        int[] res;
        double best_cost = solution.getCost();
        boolean improvement = true;

        while (improvement){
            improvement = false;
            for (int i = 0; i < size - 3; i++) { //nProducts - 3 es el número de aristas compatibles para hacer 2optswap entre i y j
                for (int j = i+2; j < size - 1; j++) {
                    
                    double act_cost = best_cost - solution.costBetweenPathNodes(i,i+1) 
                                                - solution.costBetweenPathNodes(j,j+1)
                                                + solution.costBetweenPathNodes(i,j+1) 
                                                + solution.costBetweenPathNodes(j,i+1); //calculamos como varia el coste si cruzamos las aristas
                    
                    if (act_cost < best_cost) {
                        solution.twoOptSwap(i, j); //si el coste mejora hacemos el 2optswap
                        //para el test verifica que act_cost = solution.cost si hemos pasado por el if
                        best_cost = act_cost;
                        improvement = true;
                        //una vez encontrado el two swap voy al inicio o no? pseudocodigo que he encontrado lo hace pero no sé si es mejor o peor
                    }
                }
            }
        }


        return solution;
        
    }
}
