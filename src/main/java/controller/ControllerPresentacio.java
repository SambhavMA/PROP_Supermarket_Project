package controller;

import controller.ControllerIO;
import model.distribution.Distribution;
import model.exceptions.DistributionNotFoundException;
import model.exceptions.NoTypeWithName;
import model.exceptions.ProductAlreadyExistsException;
import model.exceptions.ProductNotFoundException;
import model.exceptions.SimilarityTableNotFoundException;
import model.product.EnumType;
import model.product.Product;
import model.similarity.SimilarityTable;
import utils.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import controller.ControllerDomini;

public class ControllerPresentacio {
    private ControllerDomini cDom = new ControllerDomini();
    private ControllerIO cIO = new ControllerIO(this);

    public ControllerPresentacio() {
        init();
    }

    public void init() {
        cIO.showWelcomeMessage();
        cIO.showUseCases();
        waitResponse();
    }

    public void waitResponse() {
        cIO.writeLine("Enter a number: ");
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
                break;
            default:
                System.out.println("Invalid option.");
        }
        waitResponse();
    }

    public void addProduct() {
        cIO.writeLine("Ha escollit l'opcio Add Product");
        cIO.writeLine("Indiqui en linies diferents el nom del producte i el seu tipus");
        cIO.writeLine("De moment, els tipus disponibles son: Fruita - Verdura - Peix - Carn");

        String nom = cIO.readLine();
        String tipus = cIO.readLine();

        try {
            cDom.addProduct(nom, tipus);
        } catch (ProductAlreadyExistsException e) {
            cIO.writeLine("ERROR: " + e.toString());
        } catch (NoTypeWithName e) {
            cIO.writeLine("ERROR: " + e.toString());
        }
    }

    public void modifyProduct() {
        cIO.writeLine("Ha escollit l'opcio Modify Product");
        cIO.writeLine("Indiqui en linies diferents el nom del producte i el seu nou tipus");
        cIO.writeLine("De moment, els tipus disponibles son: Fruita - Verdura - Peix - Carn");

        String nom = cIO.readLine();
        String nouTipus = cIO.readLine();

        try {
            cDom.modifyProduct(nom, nouTipus);
        } catch (ProductNotFoundException e) {
            cIO.writeLine("ERROR: " + e.toString());
        } catch (NoTypeWithName e) {
            cIO.writeLine("ERROR: " + e.toString());
        }
    }

    public void deleteProduct() {
        cIO.writeLine("Ha escollit l'opcio Delete Product");
        cIO.writeLine("Indiqui el nom del producte a eliminar");

        String nom = cIO.readLine();

        try {
            cDom.deleteProduct(nom);
        } catch (ProductNotFoundException e) {
            cIO.writeLine("ERROR: " + e.toString());
        }
    }

    public void getProduct() {
        cIO.writeLine("Ha escollit l'opcio Get Product");
        cIO.writeLine("Indiqui el nom del producte a consultar");

        String nom = cIO.readLine();

        try {
            Pair<String, String> product = cDom.getProduct(nom);
            cIO.writeLine("Producte: " + product.first + " Tipus: " + product.second);
        } catch (ProductNotFoundException e) {
            cIO.writeLine("ERROR: " + e.toString());
        }
    }

    public void addSimilarityTable() {
        cIO.writeLine("Ha escollit l'opció Add Similarity Table");
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
            products.add(line);
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
                cIO.writeLine("Error al processar la entrada. Comprovi el format i torni-ho a intentar.aaaaa");
            }
        }

        try {
            cDom.addSimilarityTable(products, similarities);
            cIO.writeLine("La taula de similitud s'ha afegit correctament.");
        } catch (ProductNotFoundException e) {
            cIO.writeLine("ERROR: " + e.getMessage());
        }
    }

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
        } catch (SimilarityTableNotFoundException e) {
            cIO.writeLine("ERROR: " + e.toString());
        }
    }

    public void deleteSimilarityTable() {
        cIO.writeLine("Ha escollit l'opcio Delete Similarity Table\n");
        cIO.writeLine("Indiqui l'identificador de la taula de similitud a eliminar");

        int id = cIO.readIntLine();

        try {
            cDom.deleteSimilarityTable(id);
        } catch (SimilarityTableNotFoundException e) {
            System.out.println("ERROR: " + e.toString());
        }
    }

    public void getSimilarityTable() {
        cIO.writeLine("Ha escollit l'opcio Get Similarity Table\n");
        cIO.writeLine("Indiqui l'identificador de la taula de similitud a consultar");

        int id = cIO.readIntLine();
        try {
            Pair<Vector<Pair<String, Integer>>, Vector<Vector<Double>>> table = cDom.getSimilarityTable(id);

            Vector<Pair<String, Integer>> products = table.getFirst();
            Vector<Vector<Double>> similarities = table.getSecond();
            for (int i = 0; i < products.size(); i++) {
                cIO.writeLine("Product: " + products.get(i).getFirst() + " - Index: " + products.get(i).getSecond());
            }
            for (int i = 0; i < similarities.size(); i++) {
                for (int j = 0; j < similarities.get(i).size(); j++) {
                    cIO.writeLine("Similarity between " + products.get(i).getFirst() + " and "
                            + products.get(j).getFirst() + ": " + similarities.get(i).get(j));
                }
            }
        } catch (Exception e) {
            cIO.writeLine("ERROR: " + e.toString());
        }
    }

    public void generateDistribution() {
    }

    public void modifyDistribution() {
    }

    public void deleteDistribution() {
    }

    public void getDistribution() {
    }

    /*
     * public void testingAlgorithm() throws Exception {
     * double[][] costes = {
     * {0.0, 0.2, 0.4, 0.6},
     * {0.2, 0.0, 0.8, 0.3},
     * {0.4, 0.8, 0.0, 0.7},
     * {0.6, 0.3, 0.7, 0.0}
     * };
     * AlgorithmController alg = new AlgorithmController(costes);
     * String[] s = alg.getAlgorithms();
     * Object[] data = alg.executeAlgorithm(AlgorithmsNames.NN);
     * }
     */
}
