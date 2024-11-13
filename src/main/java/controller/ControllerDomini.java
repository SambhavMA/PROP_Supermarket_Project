package controller;

import model.distribution.Distribution;
import model.exceptions.DistributionNotFoundException;
import model.exceptions.ProductAlreadyExistsException;
import model.exceptions.ProductNotFoundException;
import model.exceptions.SimilarityTableNotFoundException;
import model.product.ProductContainer;
import model.product.Product;
import model.product.EnumType;
import model.distribution.DistributionContainer;
import model.similarity.SimilarityTableContainer;
import model.similarity.SimilarityTable;
import utils.UserView;

public class ControllerDomini {
    private ProductContainer productContainer = new ProductContainer();
    private DistributionContainer distributionContainer =  new DistributionContainer();
    private SimilarityTableContainer similarityTableContainer = new SimilarityTableContainer();
    private UserView userView = new UserView();
    private boolean exit = false;

    private static ControllerDomini singletonObject;

    /**
     * Crea una instancia de domini
     */
    public ControllerDomini() {
        init();
    }

    /**
     * Retorna la intancia de ControllerDomini. Si no existeix, es crea
     */
    public static ControllerDomini getInstance() {
        if (singletonObject == null) {
            singletonObject = new ControllerDomini();
        }
        return singletonObject;
    }

    /**
     * S'inicialitzen les variables necessàries
     */
    public void init() {

    }

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
                    userView.showUseCasesProduct();
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
                    getProduct(userView.getInput("Enter product name: "));
                    break;

                /* 9 - manageSimilarityTables */
                case "9":
                    userView.showUseCasesSimilarityTable();
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
                    userView.showUseCasesDistribution();
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
                    getDistribution(userView.getIntInput("Enter distribution id: "));
                    break;

                /* 19 - selectDistribution */
                case "19":
                    break;

                /* 20 - close */
                case "20":
                    userView.close();
                    exit = true;
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

    /**
     * Afegeix una nova distribució al container de distribucions
     * @param distribution Distribució a afegir
     */
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

    /**
     * Elimina una distribució del container de distribucions
     * @param id Identificador de la distribució a eliminar
     * @throws DistributionNotFoundException Si la distribució no existeix
     */
    public void deleteDistribution(int id) throws DistributionNotFoundException {
        distributionContainer.deleteDistributionById(id);
        userView.showMessage("Distribution deleted: " + id);
    }

    /**
     * Comprova si una distribució existeix
     * @param id Identificador de la distribució a comprovar
     */
    public void getDistribution(int id) {
        Distribution distribution = distributionContainer.getDistributionById(id);
        if (distribution != null) {
            userView.showMessage("Distribution found: " + id);
            return;
        }
        userView.showMessage("Distribution not found: " + id);
    }

    public void manageProducts() {
        // TODO
    }

    /**
     * Crea un nou producte i l'afegeix al product container
     * @param name Nom del producte
     * @param type Tipus del producte
     * @throws ProductAlreadyExistsException Si el producte ja existeix
     */
    public void addProduct(String name, EnumType type) throws ProductAlreadyExistsException {
        Product product = new Product(name, type);
        productContainer.addProduct(product);
        userView.showMessage("Product added: " + name);
    }

    // TODO creo que esta mal hecho
    /**
     * Modifica un producte existent
     * @param name Nom del producte
     * @param newType Nou tipus del producte
     */
    public void modifyProduct(String name, EnumType newType) {
        Product product = productContainer.getProductByName(name);
        if(product == null) {
            userView.showMessage("Product not found: " + name);
            return;
        }
        product.setType(newType);
        userView.showMessage("Product modified: " + name);
    }

    /**
     * Elimina un producte existent
     * @param name Nom del producte a eliminar
     * @throws ProductNotFoundException Si el producte no existeix
     */
    public void deleteProduct(String name)  throws ProductNotFoundException {
        productContainer.deleteProductByName(name);
        userView.showMessage("Product deleted: " + name);
    }

    // TODO s'ha de fer amb excepcions???
    /**
     * Comprova si un producte existeix
     * @param name Nom del producte a comprovar
     */
    public void getProduct(String name) {
        Product product = productContainer.getProductByName(name);
        if (product == null) {
            userView.showMessage("Product not found: " + name);
            return;
        }
        userView.showMessage("Product found: " + name);
    }

    public void manageSimilarityTables() {
        // TODO
    }

    /**
     * Afegeix una nova taula de similitud
     * @param similarityTable Taula de similitud a afegir
     */
    public void addSimilarityTable(SimilarityTable similarityTable) {
        int newId = similarityTableContainer.newId();
        similarityTableContainer.addSimilarityTable(newId, similarityTable);
    }

    /**
     * Modifica una taula de similitud
     * @param id Identificador de la taula de similitud
     * @param newSimilarityTable Taula de similitud amb els nous canvis
     * @throws SimilarityTableNotFoundException Si la taula de similitud no existeix
     */
    public void modifySimilarityTable(int id, SimilarityTable newSimilarityTable) throws SimilarityTableNotFoundException {
        similarityTableContainer.modifySimilarityTable(id, newSimilarityTable);
    }

    /**
     * Elimina una taula de similitud
     * @param id Identificador de la taula de similitud a eliminar
     * @throws SimilarityTableNotFoundException Si la taula de similitud a borrar no existeix
     */
    public void deleteSimilarityTable(int id) throws SimilarityTableNotFoundException {
        similarityTableContainer.deleteSimilarityTableById(id);
    }

    // TODO s'ha de fer amb excepcions???
    /**
     * Comprova una taula de similitud
     * @param id Identificador de la taula de similitud a comprovar
     */
    public void getSimilarityTable(int id) {
        SimilarityTable similarityTable = similarityTableContainer.getSimilarityTableById(id);
        if (similarityTable == null) {
            userView.showMessage("SimilarityTable not found with id: " + id);
            return;
        }
        userView.showMessage("SimilarityTable found with id: " + id);
    }
}
