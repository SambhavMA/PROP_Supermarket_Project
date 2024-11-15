package controller;

import java.lang.ModuleLayer.Controller;
import java.util.Scanner;

import model.exceptions.NoTypeWithName;
import model.exceptions.ProductAlreadyExistsException;
import model.exceptions.ProductNotFoundException;

import utils.Pair;

public class ControllerIO {
    private Scanner scanner;
    ControllerPresentacio controllerP;

    public ControllerIO(ControllerPresentacio cP) {
        controllerP = cP;
        scanner = new Scanner(System.in);
    }

    public void init() {
        showWelcomeMessage();
        showUseCases();
        waitResponse();
    }

    public void waitResponse() {
        System.out.println("\nEnter a number: ");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                opcio1();
                break;
            case 2:
                opcio2();
                break;
            case 3:
                opcio3();
                break;
            case 4:
                opcio4();
                break;
            case 5:
                opcio5();
                break;
            case 6:
                opcio6();
                break;
            case 7:
                opcio7();
                break;
            case 8:
                opcio8();
                break;
            case 9:
                opcio9();
                break;
            case 10:
                opcio10();
                break;
            case 11:
                opcio11();
                break;
            case 12:
                opcio12();
                break;
            case 13:
                opcio13();
                break;
            default:
                showMessage("Invalid option.");
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
        System.out.println("1 - Add Product");
        System.out.println("2 - Modify Product");
        System.out.println("3 - Delete Product");
        System.out.println("4 - Get Product ");
        System.out.println("5 - Add Similarity Table");
        System.out.println("6 - Modify Similarity Table");
        System.out.println("7 - Delete Similarity Table");
        System.out.println("8 - Get Similarity Table");
        System.out.println("9 - Generate Distribution");
        System.out.println("10 - Modify Distribution");
        System.out.println("11 - Delete Distribution");
        System.out.println("12 - Get Distribution");
        System.out.println("13 - Exit");
    }

    public void opcio1() {
        System.out.println("Ha escollit l'opcio 1\n");
        System.out.println("Indiqui en linies diferents el nom del producte i el seu tipus");
        System.out.println("Pot trobar un exemple d'entrada en el fitxer 'README' \n");

        String nom = scanner.next();
        String tipus = scanner.next();

        try {
            controllerP.addProduct(nom, tipus);
        } catch (ProductAlreadyExistsException e) {
            System.out.println("ERROR: " + e.toString());
        } catch (NoTypeWithName e) {
            System.out.println("ERROR: " + e.toString());
        }
    }

    public void opcio2() {
        System.out.println("Ha escollit l'opcio 2\n");
        System.out.println("Indiqui en linies diferents el nom del producte i el seu nou tipus");
        System.out.println("Pot trobar un exemple d'entrada en el fitxer 'README' \n");

        String nom = scanner.next();
        String tipus = scanner.next();

        try {
            controllerP.modifyProduct(nom, tipus);
        } catch (ProductNotFoundException e) {
            System.out.println("ERROR: " + e.toString());
        } catch (NoTypeWithName e) {
            System.out.println("ERROR: " + e.toString());
        }
    }

    public void opcio3() {
        System.out.println("Ha escollit l'opcio 3\n");
        System.out.println("Indiqui el nom del producte a eliminar");
        System.out.println("Pot trobar un exemple d'entrada en el fitxer 'README' \n");

        String nom = scanner.next();
        try {
            controllerP.deleteProduct(nom);
        } catch (ProductNotFoundException e) {
            System.out.println("ERROR: " + e.toString());
        }
    }

    public void opcio4() {
        System.out.println("Ha escollit l'opcio 4\n");
        System.out.println("Indiqui el nom del producte a consultar");
        System.out.println("Pot trobar un exemple d'entrada en el fitxer 'README' \n");

        String nom = scanner.next();
        try {
            Pair<String, String> product = controllerP.getProduct(nom);
            System.out.println(
                    "\nProduct found: \n    Name: " + product.getFirst() + "\n    Type: " + product.getSecond() + "\n");
        } catch (ProductNotFoundException e) {
            System.out.println("ERROR: " + e.toString());
        }
    }

    public void opcio5() {
    }

    public void opcio6() {
    }

    public void opcio7() {
    }

    public void opcio8() {
    }

    public void opcio9() {
    }

    public void opcio10() {
    }

    public void opcio11() {
    }

    public void opcio12() {
    }

    public void opcio13() {
    }

}
