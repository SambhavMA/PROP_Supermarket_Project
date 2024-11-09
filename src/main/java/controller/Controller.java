package controller;

import model.distribution.Distribution;
import model.product.ProductContainer;
import model.product.Product;
import model.product.EnumType;
import model.distribution.DistributionContainer;
import model.similarity.SimilarityTableContainer;
import utils.UserView;

public class Controller {
    private ProductContainer productContainer = new ProductContainer();
    private DistributionContainer distributionContainer =  new DistributionContainer();
    private SimilarityTableContainer similarityTableContainer = new SimilarityTableContainer();
    private UserView userView = new UserView();
    private boolean exit = false;

    public void start() {
        while (!exit) {
            userView.showWelcomeMessage();
            userView.showUseCases();

            String input = userView.getInput(">");
            switch (input) {
                /* 1 - init */
                case "1":
                    break;

                /* 2 - loadAlgorithms */
                case "2":
                    break;

                /* 3 - startMenu */
                case "3":
                    break;

                /* 4 - manageProducts */
                case "4":
                    break;

                /* 5 - addProduct */
                case "5":
                    addProduct(userView.getInput("Enter product name: "), EnumType.valueOf(userView.getInput("Enter product type: ")));
                    break;

                /* 6 - modifyProduct */
                case "6":
                    modifyProduct(userView.getInput("Enter product name: "), EnumType.valueOf(userView.getInput("Enter product type: ")));
                    break;

                /* 7 - deleteProduct */
                case "7":
                    deleteProduct(userView.getInput("Enter product name: "));
                    break;

                /* 8 - checkProduct */
                case "8":
                    checkProduct(userView.getInput("Enter product name: "));
                    break;

                /* 9 - manageSimilarityTables */
                case "9":
                    break;

                /* 10 - addSimilarityTable */
                case "10":
                    break;

                /* 11 - modifySimilarityTable */
                case "11":
                    break;

                /* 12 - deleteSimilarityTable */
                case "12":
                    break;

                /* 13 - checkSimilarityTable */
                case "13":
                    break;

                /* 14 - manageDistributions */
                case "14":
                    break;

                /* 15 - generateDistribution */
                case "15":
                    break;

                /* 16 - modifyDistribution */
                case "16":
                    break;

                /* 17 - deleteDistribution */
                case "17":
                    deleteDistribution(userView.getIntInput("Enter distribution id: "));
                    break;

                /* 18 - checkDistribution */
                case "18":
                    checkDistribution(userView.getIntInput("Enter distribution id: "));
                    break;

                /* 19 - selectDistribution */
                case "19":
                    break;

                /* 20 - close */
                case "20":
                    break;

                /* 21 - showUseCases (Mostrar los casos de uso) */
                case "21":
                    userView.showUseCases(); // Muestra nuevamente los casos de uso
                    break;

                default:
                    userView.showMessage("Invalid option.");
            }

            if (!input.equals("21")) {
                userView.showMessage("Select an option. Type '21' to show the use cases.");
            }
        }
    }


    // methods

    // algorithm

    // distribution
    public void generateDistribution(Distribution distribution) {
        distributionContainer.addDistribution(distribution);
        userView.showMessage("Distribution generated: " + distribution.getId());
    }

    /*
    public void modifyDistribution(Distribution distribution) {
        distributionContainer.addDistribution(distribution);
         userView.showMessage("Distribution modified: " + distribution.getId());
         }
     */

    public void deleteDistribution(int id) {
        if (distributionContainer.getDistributionById(id) == null) {
            userView.showMessage("Distribution not found: " + id);
            return;
        }
        distributionContainer.deleteDistributionById(id);
        userView.showMessage("Distribution deleted: " + id);
    }

    public void checkDistribution(int id) {
        Distribution distribution = distributionContainer.getDistributionById(id);
        if (distribution != null) {
            userView.showMessage("Distribution found: " + id);
            return;
        }
        userView.showMessage("Distribution not found: " + id);
    }

    // product
    public void addProduct(String name, EnumType type) {
        if(productContainer.getProductByName(name) != null) {
            userView.showMessage("Product already exists: " + name);
            return;
        }
        Product product = new Product(name, type);
        productContainer.addProduct(product);
        userView.showMessage("Product added: " + name);
    }

    public void modifyProduct(String name, EnumType type) {
        Product product = productContainer.getProductByName(name);
        if(product == null) {
            userView.showMessage("Product not found: " + name);
            return;
        }
        if(product.getType() == type) {
            userView.showMessage("Product already has type: " + type);
            return;
        }
        product.setType(type);
        userView.showMessage("Product modified: " + name);
    }

    public void deleteProduct(String name) {
        if(productContainer.getProductByName(name) == null) {
            userView.showMessage("Product not found: " + name);
            return;
        }
        productContainer.deleteProductByName(name);
        userView.showMessage("Product deleted: " + name);
    }

    public void checkProduct(String name) {
        Product product = productContainer.getProductByName(name);
        if (product != null) {
            userView.showMessage("Product found: " + name);
        } else {
            userView.showMessage("Product not found: " + name);
        }
    }

    // similarity tables
}
