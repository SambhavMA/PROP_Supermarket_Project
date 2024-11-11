package model.algorithm;

import java.util.ArrayList;

import model.similarity.SimilarityTable;



public class HillClimbing extends Algorithm {

    public HillClimbing() {
        super(AlgorithmsNames.HC.toString(), "Algoritmo Hill Climbing para optimización.");
    }

    @Override
    public Solution execute(Solution solutions[]) {
        int nSolutions = solutions.length; 
        Solution bestSolution = solutions[0];
        double bestSolutionCost = solutions[0].getCost();

        for (int it = 0; it < nSolutions; ++it) {
            Solution actSolution = solutions[it];
            int size = actSolution.getSize();
            int[] res;
            double best_cost = actSolution.getCost();
            boolean improvement = true;

            while (improvement){
                improvement = false;
                for (int i = 0; i < size - 3; i++) { //nProducts - 3 es el número de aristas compatibles para hacer 2optswap entre i y j
                    for (int j = i+2; j < size - 1; j++) {
                        
                        double act_cost = best_cost - actSolution.costBetweenPathNodes(i,i+1) 
                                                    - actSolution.costBetweenPathNodes(j,j+1)
                                                    + actSolution.costBetweenPathNodes(i,j+1) 
                                                    + actSolution.costBetweenPathNodes(j,i+1); //calculamos como varia el coste si cruzamos las aristas
                        
                        if (act_cost < best_cost) {
                            actSolution.swapAndUpdate(i, j); //si el coste mejora hacemos el 2optswap
                            //para el test verifica que act_cost = solution.cost si hemos pasado por el if
                            best_cost = act_cost;
                            improvement = true;
                            //una vez encontrado el two swap voy al inicio o no? pseudocodigo que he encontrado lo hace pero no sé si es mejor o peor
                        }
                    }
                }
            }

            if (best_cost < bestSolutionCost) {
                bestSolution = actSolution;
                bestSolutionCost = best_cost;
            }
        }


        return bestSolution;
        
    }
}
