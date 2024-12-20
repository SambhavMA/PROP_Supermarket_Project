package model.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.exceptions.AlgorithmException;
import model.similarity.SimilarityTable;



/**
 * @author Sambhav Mayani Harlani (sambhav.mayani@estudiantat.upc.edu)
 *
 * <p>Los algoritmos de tipo Hill Climbing, son algoritmos de optimización que a partir de una solución inicial a un problema,
 * van evaluando pequeños cambios en esta hasta encontrar una mejora y aplicar los cambios iterativamente, hasta que ya no se
 * pueda mejorar más la solución.
 * En nuestro caso a un grupo de soluciones iniciales le vamos aplicando cambios de tipo 2_opt (Intercambio de dos aristas,
 * descrito más en detalle en la función {@link Solution#swapAndUpdate(int x, int y)} en la clase solution) para intentar 
 * mejorar las soluciones iniciales, y quedarnos con la solución minima de las soluciones iniciales mejoradas.</p>
 * 
 */
public class HillClimbing extends Algorithm {

    public HillClimbing(List<Parameter> p, double[][] costs) {
        super(p, costs);
        super.description = "Algoritmo Hill Climbing para optimización.";
        super.name = AlgorithmsNames.HillClimbing.toString();
    }

    /**
     * Funcion getParameters, retorna una lista con los parametros que necesita el algoritmo.
     * @return Lista de parametros
     */
    public static ArrayList<Parameter> getParameters() {
        ArrayList<Parameter> parameters = new ArrayList<>();

        int n = costs.length/2;
        if (n > 3) n = 3;
        Solution[] initialSolutions = new Solution[n];
        //startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            ArrayList<Parameter> parametersNN = NearestNeighbor.getParameters();
            NearestNeighbor nn = new NearestNeighbor(parametersNN, costs);
            initialSolutions[i] = nn.execute();
        }

        Parameter<Solution[]> p1 = new Parameter<>("Soluciones iniciales", "Array de soluciones", initialSolutions);
        parameters.add(p1);

        return parameters;
    }

    /**
     * Funcion execute del HillClimbing a partir de un conjunto de soluciones inicial
     *
     * @return Solucion final de devolvemos
     */
    @Override
    public Solution execute() throws AlgorithmException {
        Solution[] solutions = null;

        for (Parameter p : parameters) {
            if (p.getName().equals("Soluciones iniciales")) {
                solutions = (Solution[]) p.getValue();
            }
        }


        if (solutions != null) {
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
                                    - actSolution.costBetweenPathNodes(i, i + 1) // podría directamente declarar nuevo
                                    // actsolution y hacer swap y el coste es
                                    // esto
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
        } else {
            throw new AlgorithmException("Lista de parametros incorrecta");
        }
    }
}
