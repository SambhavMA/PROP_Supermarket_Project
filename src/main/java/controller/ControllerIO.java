package controller;

import java.util.Scanner;

import model.exceptions.NoTypeWithName;
import model.exceptions.ProductAlreadyExistsException;
import model.exceptions.ProductNotFoundException;
import model.exceptions.SimilarityTableNotFoundException;
import utils.Pair;
import java.util.Vector;

public class ControllerIO {
    private Scanner scanner;
    private ControllerDomini controllerD;

    public ControllerIO(ControllerDomini cD) {
        controllerD = cD;
        scanner = new Scanner(System.in);
    }

    public void init() {
        showWelcomeMessage();
        showUseCases();
        waitResponse();
    }

    public void waitResponse() {
        System.out.println("\nEnter a number: ");
        String option = scanner.next();
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
                exit();
                break;
            default:
                System.out.println("Invalid option.\n");
        }
        // dir si vol fer exit o mes coses
        waitResponse();
    }

    public void showWelcomeMessage() {
        System.out.println("Welcome to the system! Please select an option:");
    }

    public void showUseCases() {
        System.out.println("\nUse cases:");
        System.out.println(" 1 - Add Product");
        System.out.println(" 2 - Modify Product");
        System.out.println(" 3 - Delete Product");
        System.out.println(" 4 - Get Product ");
        System.out.println(" 5 - Add Similarity Table");
        System.out.println(" 6 - Modify Similarity Table");
        System.out.println(" 7 - Delete Similarity Table");
        System.out.println(" 8 - Get Similarity Table");
        System.out.println(" 9 - Generate Distribution");
        System.out.println("10 - Modify Distribution");
        System.out.println("11 - Delete Distribution");
        System.out.println("12 - Get Distribution");
        System.out.println("13 - Exit");
    }

    public void addProduct() {
        System.out.println("Ha escollit l'opcio 1\n");
        System.out.println("Indiqui en linies diferents el nom del producte i el seu tipus");
        System.out.println("De moment, els tipus disponibles son: Fruita - Verdura - Peix - Carn \n");

        String nom = scanner.next();
        String tipus = scanner.next();

        try {
            controllerD.addProduct(nom, tipus);
        } catch (ProductAlreadyExistsException e) {
            System.out.println("ERROR: " + e.toString());
        } catch (NoTypeWithName e) {
            System.out.println("ERROR: " + e.toString());
        }
    }

    public void modifyProduct() {
        System.out.println("Ha escollit l'opcio 2\n");
        System.out.println("Indiqui en linies diferents el nom del producte i el seu nou tipus");
        System.out.println("De moment, els tipus disponibles son: Fruita - Verdura - Peix - Carn \n");

        String nom = scanner.next();
        String tipus = scanner.next();

        try {
            controllerD.modifyProduct(nom, tipus);
        } catch (ProductNotFoundException e) {
            System.out.println("ERROR: " + e.toString());
        } catch (NoTypeWithName e) {
            System.out.println("ERROR: " + e.toString());
        }
    }

    public void deleteProduct() {
        System.out.println("Ha escollit l'opcio 3\n");
        System.out.println("Indiqui el nom del producte a eliminar");

        String nom = scanner.next();
        try {
            controllerD.deleteProduct(nom);
        } catch (ProductNotFoundException e) {
            System.out.println("ERROR: " + e.toString());
        }
    }

    public void getProduct() {
        System.out.println("Ha escollit l'opcio 4\n");
        System.out.println("Indiqui el nom del producte a consultar");

        String nom = scanner.next();
        try {
            Pair<String, String> product = controllerD.getProduct(nom);
            System.out.println(
                    "\nProduct found: \n    Name: " + product.getFirst() + "\n    Type: " + product.getSecond() + "\n");
        } catch (ProductNotFoundException e) {
            System.out.println("ERROR: " + e.toString());
        }
    }

    public void addSimilarityTable() {
        System.out.println("Ha escollit l'opcio 5\n");
        System.out.println(
                "Indiqui els noms dels productes que vol afegir a la taula de similitud, un per linia. Quan hagi acabat, escrigui 'fi'");

        // creamos el vector de productos
        Vector<String> products = new Vector<String>();
        String product = scanner.next();
        while (!product.equals("fi")) {
            products.add(product);
            product = scanner.next();
        }

        System.out
                .println("Indiqui les similituds entre els productes, una per linia. Quan hagi acabat, escrigui 'fi'");
        System.out.println("El format es: producte1 producte2 similitud.");

        // creamos el vector de similitudes
        Vector<Pair<Pair<String, String>, Double>> similarities = new Vector<Pair<Pair<String, String>, Double>>();
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("fi")) {
                break;
            }
            try {
                String[] parts = line.split("\\s+");
                if (parts.length != 3) {
                    System.out.println("Format incorrecte. Torni a introduir la similitud.");
                    continue;
                }

                String product1 = parts[0];
                String product2 = parts[1];
                Double similarity = Double.parseDouble(parts[2]);

                similarities.add(new Pair<>(new Pair<>(product1, product2), similarity));
            } catch (Exception e) {
                System.out.println("Error al processar la entrada. Comprovi el format i torni-ho a intentar.");
            }
        }

        try {
            controllerD.addSimilarityTable(products, similarities);
        } catch (ProductNotFoundException e) {
            System.out.println("ERROR: " + e.toString());
        }
    }

    public void modifySimilarityTable() {
        System.out.println("Ha escollit l'opcio 6\n");
        System.out.println("Indiqui l'identificador de la taula de similitud a modificar");

        int id = scanner.nextInt();
        System.out.println(
                "Indiqui les noves similituds dels productes de la taula, una per linia. Quan hagi acabat, escrigui 'fi'");
        System.out.println("El format es: producte1 producte2 novaSimilitud.");

        // creamos el vector de similitudes
        Vector<Pair<Pair<String, String>, Double>> newSimilarities = new Vector<Pair<Pair<String, String>, Double>>();
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("fi")) {
                break;
            }
            try {
                String[] parts = line.split("\\s+");
                if (parts.length != 3) {
                    System.out.println("Format incorrecte. Torni a introduir la similitud.");
                    continue;
                }

                String product1 = parts[0];
                String product2 = parts[1];
                Double similarity = Double.parseDouble(parts[2]);

                newSimilarities.add(new Pair<>(new Pair<>(product1, product2), similarity));
            } catch (Exception e) {
                System.out.println("Error al processar la entrada. Comprovi el format i torni-ho a intentar.");
            }
        }

        try {
            controllerD.modifySimilarityTable(id, newSimilarities);
        } catch (SimilarityTableNotFoundException e) {
            System.out.println("ERROR: " + e.toString());
        }
    }

    public void deleteSimilarityTable() {
        System.out.println("Ha escollit l'opcio 7\n");
        System.out.println("Indiqui l'identificador de la taula de similitud a eliminar");

        int id = scanner.nextInt();
        try {
            controllerD.deleteSimilarityTable(id);
        } catch (SimilarityTableNotFoundException e) {
            System.out.println("ERROR: " + e.toString());
        }
    }

    public void getSimilarityTable() {
        System.out.println("Ha escollit l'opcio 8\n");
        System.out.println("Indiqui l'identificador de la taula de similitud a consultar");

        int id = scanner.nextInt();
        Pair<Vector<Pair<String, Integer>>, Vector<Vector<Double>>> table;
        try {
            table = controllerD.getSimilarityTable(id);

            Vector<Pair<String, Integer>> products = table.getFirst();
            Vector<Vector<Double>> similarities = table.getSecond();
            for (int i = 0; i < products.size(); i++) {
                System.out
                        .println("Product: " + products.get(i).getFirst() + " - Index: " + products.get(i).getSecond());
            }
            for (int i = 0; i < similarities.size(); i++) {
                for (int j = 0; j < similarities.get(i).size(); j++) {
                    System.out.println("Similarity between " + products.get(i).getFirst() + " and "
                            + products.get(j).getFirst() + ": " + similarities.get(i).get(j));
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.toString());
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

    public void exit() {
    }

}
