package model.algorithm;

import java.util.ArrayList;

import model.similarity.SimilarityTable;



public class HillClimbing extends Algorithm {

    public HillClimbing() {
        super(AlgorithmsNames.HC.toString(), "Algoritmo Hill Climbing para optimización.");
    }

    @Override
    public Solution execute(Solution[] solutions) {
        Solution bestSolution = solutions[0];
        double bestSolutionCost = solutions[0].getCost();
        int size = solutions[0].getSize();

        for (Solution actSolution : solutions) {
            
            double best_cost = actSolution.getCost();
            boolean improvement = true;
            int ite = 0;
            while (improvement && (ite < 5)) {
                ite++;
                improvement = false;
                for (int i = 0; i < size - 3; i++) {
                    for (int j = i + 2; j < size - 1; j++) {

                        double act_cost = best_cost 
                                - actSolution.costBetweenPathNodes(i, i + 1) //podría directamente declarar nuevo actsolution y hacer swap y el coste es esto
                                - actSolution.costBetweenPathNodes(j, j + 1)
                                + actSolution.costBetweenPathNodes(i, j)
                                + actSolution.costBetweenPathNodes(j + 1, i + 1);

                        if (act_cost < best_cost) {
                            actSolution.swapAndUpdate(i, j);
                            //double coste = actSolution.getCost();
                            best_cost = act_cost;
                            improvement = true;
                        }

                    }
                }
            }

            if (best_cost < bestSolutionCost) {
                bestSolution = actSolution;
                bestSolutionCost = best_cost;
            }
        }

        return new Solution(bestSolution.getPath(), bestSolutionCost); //construimos una nueva solución con getpath para facilitar el testing
    }
}
