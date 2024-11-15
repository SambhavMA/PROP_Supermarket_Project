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

import java.util.HashMap;
import java.util.Vector;

public class ControllerDomini {
    private ProductContainer productContainer = new ProductContainer();
    private DistributionContainer distributionContainer = new DistributionContainer();
    private SimilarityTableContainer similarityTableContainer = new SimilarityTableContainer();
    private UserView userView = new UserView();

    private static ControllerDomini singletonObject;
    private ControllerIO controllerIO = new ControllerIO(this);

    /**
     * Crea una instancia de domini
     */
    public ControllerDomini() {
        init();
        controllerIO.init();
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
    public void addSimilarityTable(Vector<String> productos, Vector<Pair<Pair<String, String>, Double>> similitudes)
            throws ProductNotFoundException {
        int newId = similarityTableContainer.newId();

        HashMap<String, Integer> fastIndexes = new HashMap<>();
        for (int i = 0; i < productos.size(); i++) {
            fastIndexes.put(productos.get(i), i);
        }

        Vector<Vector<Double>> relationMatrix = new Vector<>();
        for (int i = 0; i < productos.size(); i++) {
            Vector<Double> row = new Vector<>();
            for (int j = 0; j < productos.size(); j++) {
                row.add(0.0); // Inicializar con 0.0
            }
            relationMatrix.add(row);
        }

        for (Pair<Pair<String, String>, Double> similitud : similitudes) {
            String product1 = similitud.getFirst().getFirst();
            String product2 = similitud.getFirst().getSecond();
            Double value = similitud.getSecond();

            // TODO no tener duplicadas las similitudes
            if (fastIndexes.containsKey(product1) && fastIndexes.containsKey(product2)) {
                int index1 = fastIndexes.get(product1);
                int index2 = fastIndexes.get(product2);

                relationMatrix.get(index1).set(index2, value);
                relationMatrix.get(index2).set(index1, value);
            }
        }

        SimilarityTable similarityTable = new SimilarityTable(newId, fastIndexes, relationMatrix);
        similarityTableContainer.addSimilarityTable(newId, similarityTable);
    }

    /**
     * Modifica una taula de similitud
     * 
     * @param id                 Identificador de la taula de similitud
     * @param newSimilarityTable Taula de similitud amb els nous canvis
     * @throws SimilarityTableNotFoundException Si la taula de similitud no existeix
     */
    public void modifySimilarityTable(int id, Vector<Pair<Pair<String, String>, Double>> nuevasSimilitudes)
            throws SimilarityTableNotFoundException {
        SimilarityTable similarityTable = similarityTableContainer.getSimilarityTableById(id);
        HashMap<String, Integer> fastIndexes = similarityTable.getFastIndexes();
        Vector<Vector<Double>> relationMatrix = similarityTable.getRelationMatrix();

        for (Pair<Pair<String, String>, Double> similitud : nuevasSimilitudes) {
            String product1 = similitud.getFirst().getFirst();
            String product2 = similitud.getFirst().getSecond();
            Double value = similitud.getSecond();

            // TODO no tener duplicadas las similitudes
            if (fastIndexes.containsKey(product1) && fastIndexes.containsKey(product2)) {
                int index1 = fastIndexes.get(product1);
                int index2 = fastIndexes.get(product2);

                relationMatrix.get(index1).set(index2, value);
                relationMatrix.get(index2).set(index1, value);
            }
        }

        SimilarityTable modifiedSimilarityTable = new SimilarityTable(id, fastIndexes, relationMatrix);
        similarityTableContainer.modifySimilarityTable(id, modifiedSimilarityTable);
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

    /**
     * Retorna una taula de similitud
     * 
     * @param id Identificador de la taula de similitud a comprovar
     */
    public Pair<Vector<Pair<String, Integer>>, Vector<Vector<Double>>> getSimilarityTable(int id)
            throws SimilarityTableNotFoundException {
        SimilarityTable similarityTable = similarityTableContainer.getSimilarityTableById(id);

        Vector<Pair<String, Integer>> productos = new Vector<>();
        for (String key : similarityTable.getFastIndexes().keySet()) {
            productos.add(new Pair<>(key, similarityTable.getFastIndexes().get(key)));
        }

        Vector<Vector<Double>> relationMatrix = similarityTable.getRelationMatrix();

        return new Pair<>(productos, relationMatrix);
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
}
