package controller;

import model.distribution.Distribution;
import model.exceptions.DistributionNotFoundException;
import model.exceptions.NoTypeWithName;
import model.exceptions.ProductAlreadyExistsException;
import model.exceptions.ProductNotFoundException;
import model.exceptions.SimilarityTableNotFoundException;
import model.product.ProductContainer;
import model.product.Product;
import model.product.EnumType;
import model.distribution.DistributionContainer;
import model.similarity.SimilarityTableContainer;
import model.similarity.SimilarityTable;
import utils.Pair;
import utils.UserView;
import java.util.Vector;

public class ControllerDomini {
    private ProductContainer productContainer = new ProductContainer();
    private DistributionContainer distributionContainer = new DistributionContainer();
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

    /**
     * Afegeix una nova distribució al container de distribucions
     * 
     * @param distribution Distribució a afegir
     */
    public void generateDistribution(Distribution distribution) {
        distributionContainer.addDistribution(distribution);
        userView.showMessage("Distribution generated: " + distribution.getId());
    }

    /*
     * public void modifyDistribution(Distribution distribution) {
     * distributionContainer.addDistribution(distribution);
     * userView.showMessage("Distribution modified: " + distribution.getId());
     * }
     */

    /**
     * Elimina una distribució del container de distribucions
     * 
     * @param id Identificador de la distribució a eliminar
     * @throws DistributionNotFoundException Si la distribució no existeix
     */
    public void deleteDistribution(int id) throws DistributionNotFoundException {
        distributionContainer.deleteDistributionById(id);
        userView.showMessage("Distribution deleted: " + id);
    }

    /**
     * Comprova si una distribució existeix
     * 
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

    /**
     * Crea un nou producte i l'afegeix al product container
     * 
     * @param name Nom del producte
     * @param type Tipus del producte
     * @throws ProductAlreadyExistsException Si el producte ja existeix
     */
    public void addProduct(String name, String type) throws ProductAlreadyExistsException, NoTypeWithName {
        EnumType eType;
        try {
            eType = EnumType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NoTypeWithName(type);
        }

        Product product = new Product(name, eType);
        productContainer.addProduct(product);
        userView.showMessage("Product added: " + name);
    }

    /**
     * Modifica un producte existent
     * 
     * @param name    Nom del producte
     * @param newType Nou tipus del producte
     */
    public void modifyProduct(String name, String newType) throws ProductNotFoundException, NoTypeWithName {
        EnumType eNewType;
        try {
            eNewType = EnumType.valueOf(newType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NoTypeWithName(newType);
        }

        Product product = productContainer.getProductByName(name);
        if (product == null) {
            throw new ProductNotFoundException(name);
        }

        product.setType(eNewType);
        userView.showMessage("Product modified: " + name);
    }

    /**
     * Elimina un producte existent
     * 
     * @param name Nom del producte a eliminar
     * @throws ProductNotFoundException Si el producte no existeix
     */
    public void deleteProduct(String name) throws ProductNotFoundException {
        productContainer.deleteProductByName(name);
        userView.showMessage("Product deleted: " + name);
    }

    // TODO s'ha de fer amb excepcions???
    /**
     * Torna un producte si existeix
     * 
     * @param name Nom del producte a comprovar
     */
    public Pair<String, String> getProduct(String name) throws ProductNotFoundException {
        Product product = productContainer.getProductByName(name);
        return new Pair<>(product.getName(), product.getType().toString());
    }

    /**
     * Afegeix una nova taula de similitud
     * 
     * @param similarityTable Taula de similitud a afegir
     */
    public void addSimilarityTable(SimilarityTable similarityTable) {
        int newId = similarityTableContainer.newId();
        similarityTableContainer.addSimilarityTable(newId, similarityTable);
    }

    /**
     * Modifica una taula de similitud
     * 
     * @param id                 Identificador de la taula de similitud
     * @param newSimilarityTable Taula de similitud amb els nous canvis
     * @throws SimilarityTableNotFoundException Si la taula de similitud no existeix
     */
    public void modifySimilarityTable(int id, SimilarityTable newSimilarityTable)
            throws SimilarityTableNotFoundException {
        similarityTableContainer.modifySimilarityTable(id, newSimilarityTable);
    }

    /**
     * Elimina una taula de similitud
     * 
     * @param id Identificador de la taula de similitud a eliminar
     * @throws SimilarityTableNotFoundException Si la taula de similitud a borrar no
     *                                          existeix
     */
    public void deleteSimilarityTable(int id) throws SimilarityTableNotFoundException {
        similarityTableContainer.deleteSimilarityTableById(id);
    }

    // TODO s'ha de fer amb excepcions???
    /**
     * Comprova una taula de similitud
     * 
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
