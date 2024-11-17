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
    private ControllerPresentacio controllerP;

    public ControllerIO(ControllerPresentacio cP) {
        controllerP = cP;
        scanner = new Scanner(System.in);
    }

    public void writeLine(String message) {
        System.out.println(message);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public Integer readIntLine() {
        Integer n = scanner.nextInt();
        scanner.nextLine();
        return n;
    }

    public void showWelcomeMessage() {
        System.out.println("Welcome to the system! Please select an option:");
    }

    public void showUseCases() {
        System.out.println("Use cases:");
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

    public void exit() {
        System.out.println("\nAdios!\n");
        System.exit(0);
    }

}
