package controller;

import java.util.Scanner;

import model.exceptions.NoTypeWithName;
import model.exceptions.ProductAlreadyExistsException;
import model.exceptions.ProductNotFoundException;

import utils.Pair;

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

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void close() {
        scanner.close();
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
    }

    public void modifySimilarityTable() {
    }

    public void deleteSimilarityTable() {
    }

    public void getSimilarityTable() {
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
