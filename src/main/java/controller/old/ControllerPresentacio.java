package controller.old;

import controller.ControllerDomini;
import model.algorithm.AlgorithmController;
import model.distribution.Distribution;
import model.exceptions.DistributionNotFoundException;
import model.exceptions.NoTypeWithName;
import model.exceptions.ProductAlreadyExistsException;
import model.exceptions.ProductNotFoundException;
import model.exceptions.SimilarityTableNotFoundException;
import utils.Pair;

import java.util.*;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 * <p>Clase que se encarga de la interacción con el usuario</p>
 */
public class ControllerPresentacio {
    private ControllerDomini cDom = new ControllerDomini();
    private ControllerIO cIO = new ControllerIO(this);

    public ControllerPresentacio() throws ProductAlreadyExistsException {
        init();
    }

    /**
     * Inicializa el controlador de IO y espera la respuesta del usuario
     */
    public void init() {
        cIO.showWelcomeMessage();
        cIO.showUseCases();
        waitResponse();
    }

    /**
     * Espera la respuesta del usuario y ejecuta la acción correspondiente
     */
    public void waitResponse() {
        cIO.writeLine("\nEnter a number: ");
        String option = cIO.readLine();
        switch (option) {
            case "1":
                addProduct();
                break;
            case "2":
                modifyProduct();
                break;
            case "3":
                deleteProduct();
                break;
            case "4":
                getProduct();
                break;
            case "5":
                addSimilarityTable();
                break;
            case "6":
                modifySimilarityTable();
                break;
            case "7":
                deleteSimilarityTable();
                break;
            case "8":
                getSimilarityTable();
                break;
            case "9":
                generateDistribution();
                break;
            case "10":
                modifyDistribution();
                break;
            case "11":
                deleteDistribution();
                break;
            case "12":
                getDistribution();
                break;
            case "13":
                cIO.exit();
                break;
            default:
                System.out.println("Invalid option.");
        }
        waitResponse();
    }

    /**
     * Llama al controlador de IO y de Domini para añadir un producto
     */
    public void addProduct() {
        cIO.writeLine("Ha escollit l'opcio Add Product");
        cIO.writeLine("Indiqui en linies diferents el nom del producte i el seu tipus");
        cIO.writeLine("De moment, els tipus disponibles son: Fruita - Verdura - Peix - Carn");

        String nom = cIO.readLine();
        String tipus = cIO.readLine();

        try {
            cDom.addProduct(nom, tipus);
        } catch (ProductAlreadyExistsException | NoTypeWithName e) {
            cIO.writeLine("ERROR: " + e.getMessage());
        }
    }

    /**
     * Llama al controlador de IO y de Domini para modificar un producto
     */
    public void modifyProduct() {
        cIO.writeLine("Ha escollit l'opcio Modify Product");
        cIO.writeLine("Indiqui en linies diferents el nom del producte i el seu nou tipus");
        cIO.writeLine("De moment, els tipus disponibles son: Fruita - Verdura - Peix - Carn");

        String nom = cIO.readLine();
        String nouTipus = cIO.readLine();

        try {
            cDom.modifyProduct(nom, nouTipus);
        } catch (ProductNotFoundException | NoTypeWithName e) {
            cIO.writeLine("ERROR: " + e.getMessage());
        }
    }

    /**
     * Llama al controlador de IO y de Domini para eliminar un producto
     */
    public void deleteProduct() {
        cIO.writeLine("Ha escollit l'opcio Delete Product");
        cIO.writeLine("Indiqui el nom del producte a eliminar");

        String nom = cIO.readLine();

        try {
            cDom.deleteProduct(nom);
        } catch (ProductNotFoundException e) {
            cIO.writeLine("ERROR: " + e.getMessage());
        }
    }

    /**
     * Llama al controlador de IO y de Domini para obtener un producto
     */
    public void getProduct() {
        cIO.writeLine("Ha escollit l'opcio Get Product");
        cIO.writeLine("Indiqui el nom del producte a consultar");

        String nom = cIO.readLine();

        try {
            Pair<String, String> product = cDom.getProduct(nom);
            cIO.writeLine("Producte: " + product.first + " Tipus: " + product.second);
        } catch (ProductNotFoundException e) {
            cIO.writeLine("ERROR: " + e.getMessage());
        }
    }

    /**
     * Llama al controlador de IO y de Domini para añadir una tabla de similitud
     */
    public void addSimilarityTable() {
        cIO.writeLine("Ha escollit l'opcio Add Similarity Table");
        cIO.writeLine(
                "Indiqui els noms dels productes que vol afegir a la taula de similitud, un per línia. Quan hagi acabat, escrigui 'fi'");

        List<String> products = new ArrayList<>();
        while (true) {
            String line = cIO.readLine().trim();
            if (line.equalsIgnoreCase("fi")) {
                break;
            }
            if (line.isEmpty()) {
                cIO.writeLine("El nom del producte no pot estar buit. Torni a intentar-ho.");
                continue;
            }
            // mirar que line sea un producto valido
            try {
                cDom.getProduct(line);
                products.add(line);
            } catch (ProductNotFoundException e) {
                cIO.writeLine("ERROR: " + e.getMessage());
            }
        }

        cIO.writeLine("Indiqui les similituds entre els productes, una per línia. Quan hagi acabat, escrigui 'fi'");
        cIO.writeLine("El format és: producte1 producte2 similitud.");

        List<Pair<Pair<String, String>, Double>> similarities = new ArrayList<>();
        while (true) {
            String line = cIO.readLine().trim();
            if (line.equalsIgnoreCase("fi")) {
                break;
            }

            try {
                String[] parts = line.split("\\s+");
                if (parts.length != 3) {
                    cIO.writeLine("Format incorrecte. Torni a introduir la similitud.");
                    continue;
                }

                String product1 = parts[0].trim();
                String product2 = parts[1].trim();
                Double similarity = Double.parseDouble(parts[2]);

                if (!products.contains(product1) || !products.contains(product2)) {
                    cIO.writeLine(
                            "Un o més productes no es troben a la llista de productes introduïts. Torni a intentar-ho.");
                    continue;
                }

                similarities.add(new Pair<>(new Pair<>(product1, product2), similarity));
            } catch (NumberFormatException e) {
                cIO.writeLine("El valor de similitud no és un número vàlid. Torni a intentar-ho.");
            } catch (Exception e) {
                cIO.writeLine("Error al processar la entrada. Comprovi el format i torni-ho a intentar.");
            }
        }

        try {
            int similarityTableId = cDom.addSimilarityTable(products, similarities);
            cIO.writeLine("Taula de similitud afegida amb identificador: " + similarityTableId);
        } catch (ProductNotFoundException e) {
            cIO.writeLine("ERROR: " + e.getMessage());
        }
    }

    /**
     * Llama al controlador de IO y de Domini para modificar una tabla de similitud
     */
    public void modifySimilarityTable() {
        cIO.writeLine("Ha escollit l'opció Modify Similarity Table");
        cIO.writeLine("Indiqui l'identificador de la taula de similitud a modificar");

        int id = cIO.readIntLine();
        cIO.writeLine(
                "Indiqui les noves similituds dels productes de la taula, una per línia. Quan hagi acabat, escrigui 'fi'");
        cIO.writeLine("El format esperat es: producte1 producte2 novaSimilitud.");

        List<Pair<Pair<String, String>, Double>> newSimilarities = new ArrayList<>();

        while (true) {
            String line = cIO.readLine().trim();
            if (line.equalsIgnoreCase("fi")) {
                break;
            }
            try {
                String[] parts = line.split("\\s+");
                if (parts.length != 3) {
                    cIO.writeLine("Format incorrecte. Torni a introduir la similitud.");
                    continue;
                }

                String product1 = parts[0];
                String product2 = parts[1];
                Double similarity = Double.parseDouble(parts[2]);

                newSimilarities.add(new Pair<>(new Pair<>(product1, product2), similarity));
            } catch (Exception e) {
                cIO.writeLine("Error al processar la entrada. Comprovi el format i torni-ho a intentar.");
            }
        }

        try {
            cDom.modifySimilarityTable(id, newSimilarities);
        } catch (SimilarityTableNotFoundException | ProductNotFoundException e) {
            cIO.writeLine("ERROR: " + e.getMessage());
        }
    }

    /**
     * Llama al controlador de IO y de Domini para eliminar una tabla de similitud
     */
    public void deleteSimilarityTable() {
        cIO.writeLine("Ha escollit l'opcio Delete Similarity Table\n");
        cIO.writeLine("Indiqui l'identificador de la taula de similitud a eliminar");

        int id = cIO.readIntLine();

        try {
            cDom.deleteSimilarityTable(id);
        } catch (SimilarityTableNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Llama al controlador de IO y de Domini para obtener una tabla de similitud
     */
    public void getSimilarityTable() {
        cIO.writeLine("Ha escollit l'opcio Get Similarity Table\n");
        cIO.writeLine("Indiqui l'identificador de la taula de similitud a consultar");

        int id = cIO.readIntLine();
        try {
            Pair<Vector<Pair<String, Integer>>, double[][]> table = cDom.getSimilarityTable(id);

            Vector<Pair<String, Integer>> products = table.first();
            double[][] similarities = table.second();
            for (int i = 0; i < products.size(); i++) {
                cIO.writeLine("Product: " + products.get(i).first() + " - Index: " + products.get(i).second());
            }
            for (int i = 0; i < similarities.length; i++) {
                for (int j = 0; j < similarities[i].length; j++) {
                    cIO.writeLine("Similarity between " + products.get(i).first() + " and "
                            + products.get(j).first() + ": " + similarities[i][j]);
                }
            }
        } catch (SimilarityTableNotFoundException e) {
            cIO.writeLine("ERROR: " + e.getMessage());
        }
    }

    /**
     * Llama al controlador de IO y de Domini para generar una distribución
     */
    public void generateDistribution() {
        cIO.writeLine("Ha escollit l'opcio Generate Distribution");
        cIO.writeLine("Indiqui l'id de la taula de similitud que vol utilitzar");

        int id = cIO.readIntLine();

        double[][] costs = null;
        try {
            Pair<Vector<Pair<String, Integer>>, double[][]> similarityTable = cDom.getSimilarityTable(id);
            double[][] relationMatrix = similarityTable.second();

            AlgorithmController cAlg = new AlgorithmController(relationMatrix);

            String[] algorithms = cAlg.getAlgorithms();
            cIO.writeLine("Algorithms available:");
            for (String algorithm : algorithms) {
                cIO.writeLine(algorithm);
            }
            cIO.writeLine("Choose an algorithm to generate the distribution");
            String chosenAlgorithm = cIO.readLine();

            Object[] result = cAlg.executeAlgorithm(chosenAlgorithm);

            int[] path = (int[]) result[0];
            double cost = (double) result[1];

            Vector<Pair<String, Integer>> fastIndexes = similarityTable.first();
            Vector<String> names = new Vector<>(path.length);

            for (int n : path) {
                for (Pair<String, Integer> pair : fastIndexes) {
                    if (pair.second().equals(n)) {
                        names.add(pair.first());
                        break;
                    }
                }
            }

            cIO.writeLine("Path:");
            for (String p : names) {
                cIO.writeLine(String.valueOf(p));
            }

            int distributionId = cDom.generateDistribution(id, cost, names, chosenAlgorithm);
            cIO.writeLine("Distribution generated with id: " + distributionId);
        } catch (SimilarityTableNotFoundException e) {
            cIO.writeLine("ERROR: " + e.getMessage());
        } catch (Exception e) {
            // para todas las otras excepciones de algorithmController
            cIO.writeLine("ERROR: " + e.getMessage());
        }

    }

    /**
     * Llama al controlador de IO y de Domini para modificar una distribución
     */
    public void modifyDistribution() {
        cIO.writeLine("Ha escollit l'opcio Modify Distribution");
        cIO.writeLine("Indiqui l'id de distribucio que vol modificar:");

        int id = cIO.readIntLine();

        cIO.writeLine(
                "Indiqui els productes que vol intercanviar de posicio, un per linia. Quan hagi acabat, escrigui 'fi'");
        cIO.writeLine("El format esperat es: producte1 producte2");

        List<Pair<String, String>> changes = new ArrayList<>();

        while (true) {
            String line = cIO.readLine().trim();
            if (line.equalsIgnoreCase("fi")) {
                break;
            }
            try {
                String[] parts = line.split("\\s+");
                if (parts.length != 2) {
                    cIO.writeLine("Format incorrecte. Torni a introduir la similitud.");
                    continue;
                }

                String product1 = parts[0];
                String product2 = parts[1];

                cDom.getProduct(product1);
                cDom.getProduct(product2);

                changes.add(new Pair<>(product1, product2));
            } catch (ProductNotFoundException e) {
                cIO.writeLine("ERROR: " + e.getMessage());
            } catch (Exception e) {
                cIO.writeLine("Error al processar la entrada. Comprovi el format i torni-ho a intentar.");
            }
        }

        try {
            cDom.modifyDistribution(id, changes);
        } catch (DistributionNotFoundException | SimilarityTableNotFoundException e) {
            cIO.writeLine("ERROR: " + e.getMessage());
        }
    }

    /**
     * Llama al controlador de IO y de Domini para eliminar una distribución
     */
    public void deleteDistribution() {
        cIO.writeLine("Ha escollit l'opcio Delete Distribution");
        cIO.writeLine("Indiqui l'id de distribucio que vol eliminar:");

        int id = cIO.readIntLine();

        try {
            cDom.deleteDistribution(id);
        } catch (DistributionNotFoundException e) {
            cIO.writeLine("ERROR: " + e.getMessage());
        }
    }

    /**
     * Llama al controlador de IO y de Domini para obtener una distribución
     */
    public void getDistribution() {
        cIO.writeLine("Ha escollit l'opcio Get Distribution");
        cIO.writeLine("Indiqui l'id de distribucio que vol veure:");

        int id;

        try {
            id = cIO.readIntLine();
        } catch (InputMismatchException e) {
            cIO.writeLine("ERROR: L'identificador ha de ser un numero enter valid.");
            return;
        }

        try {
            Distribution distribution = cDom.getDistribution(id);
            cIO.writeLine("Id: " + distribution.getId());
            cIO.writeLine("Similarity Table Id: " + distribution.getSimilarityTableId());
            cIO.writeLine("Cost: " + distribution.getCost());
            cIO.writeLine("Order:");
            for (String product : distribution.getOrder()) {
                cIO.writeLine(product);
            }
            cIO.writeLine("Algorithm: " + distribution.getUsedAlgorithm());
        } catch (DistributionNotFoundException e) {
            cIO.writeLine("ERROR: " + e.getMessage());
        }
    }
}
