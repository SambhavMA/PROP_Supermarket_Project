package utils;

import java.util.Scanner;

public class UserView {
    private Scanner scanner;

    public UserView() {
        scanner = new Scanner(System.in);
    }


    public void showMessage(String message) {
        System.out.println(message);
    }


    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }


    public int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public void close() {
        scanner.close();
    }


    public void showWelcomeMessage() {
        System.out.println("Welcome to the system! Please select an option:");
    }


    public void showUseCases() {
        System.out.println("\nUse cases:");
        System.out.println("1 - init");
        System.out.println("2 - loadAlgorithms");
        System.out.println("3 - startMenu");
        System.out.println("4 - manageProducts");
        System.out.println("5 - addProduct");
        System.out.println("6 - modifyProduct");
        System.out.println("7 - deleteProduct");
        System.out.println("8 - checkProduct");
        System.out.println("9 - manageSimilarityTables");
        System.out.println("10 - addSimilarityTable");
        System.out.println("11 - modifySimilarityTable");
        System.out.println("12 - deleteSimilarityTable");
        System.out.println("13 - checkSimilarityTable");
        System.out.println("14 - manageDistributions");
        System.out.println("15 - generateDistribution");
        System.out.println("16 - modifyDistribution");
        System.out.println("17 - deleteDistribution");
        System.out.println("18 - checkDistribution");
        System.out.println("19 - selectDistribution");
        System.out.println("20 - close");
        System.out.println("21 - showUseCases");
    }
}