package controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.distribution.Distribution;
import model.exceptions.*;
import model.product.ProductContainer;
import model.product.Product;
import model.product.EnumType;
import model.distribution.DistributionContainer;
import model.similarity.SimilarityTableContainer;
import model.similarity.SimilarityTable;
import utils.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 * <p>S'encarrega de gestionar les operacions de domini.
 * Aquestes operacions poden ser crear, modificar o eliminar productes, taules de similitud i distribucions.
 * A més, també pot retornar informació sobre productes, taules de similitud i distribucions.
 * Té el controller peristencia i els containers de productes, taules de similitud i distribucions com a atributs.</p>
 */
public class ControllerDomini {
    private static ControllerDomini instance;

    private ProductContainer productContainer = ProductContainer.getInstance();
    private DistributionContainer distributionContainer = DistributionContainer.getInstance();
    private SimilarityTableContainer similarityTableContainer = SimilarityTableContainer.getInstance();
    private ControllerPersistencia cP = new ControllerPersistencia();
    /**
     * Retorna la intancia de ControllerDomini. Si no existeix, es crea
     */
    public static ControllerDomini getInstance() {
        if (instance == null) {
            instance = new ControllerDomini();
        }
        return instance;
    }

    /**
     * Constructora de la classe
     */
    public ControllerDomini() {
        init();
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
     * @throws NoTypeWithName                Si el tipus no existeix
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
    }

    /**
     * Modifica un producte existent
     * 
     * @param name    Nom del producte
     * @param newType Nou tipus del producte
     * @throws ProductNotFoundException Si el producte a modificar no existeix
     * @throws NoTypeWithName          Si el nou tipus no existeix
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
    }

    /**
     * Elimina un producte existent
     * 
     * @param name Nom del producte a eliminar
     * @throws ProductNotFoundException Si el producte a eliminar no existeix
     */
    public void deleteProduct(String name) throws ProductNotFoundException {
        productContainer.deleteProductByName(name);
    }

    /**
     * Retorna un producte si existeix
     * 
     * @param name Nom del producte a buscar
     * @return Pair amb el nom i el tipus del producte
     * @throws ProductNotFoundException Si el producte buscat no existeix
     */
    public Pair<String, String> getProduct(String name) throws ProductNotFoundException {
        Product product = productContainer.getProductByName(name);
        return new Pair<>(product.getName(), product.getType().toString());
    }

    /**
     * Crea una nova taula de similitud i l'afegeix al container de taules de similitud
     * 
     * @param productos   Llista de productes de la taula de similitud
     * @param similitudes Llista de similituds entre productes (producte1, producte2, similitud)
     * @return Identificador de la taula de similitud creada
     * @throws ProductNotFoundException Si algun dels productes que formen la taula no existeix
     */
    public int addSimilarityTable(List<String> productos, List<Pair<Pair<String, String>, Double>> similitudes)
            throws ProductNotFoundException {
        int newId = similarityTableContainer.newId();

        HashMap<String, Integer> fastIndexes = new HashMap<>();
        for (int i = 0; i < productos.size(); i++) {
            fastIndexes.put(productos.get(i), i);
        }

        double[][] relationMatrix = new double[productos.size()][productos.size()];

        for (Pair<Pair<String, String>, Double> similitud : similitudes) {
            String product1 = similitud.first().first();
            String product2 = similitud.first().second();
            Double value = similitud.second();

            Integer index1 = fastIndexes.get(product1);
            if (index1 == null) throw new ProductNotFoundException(product1);
            Integer index2 = fastIndexes.get(product2);
            if (index2 == null) throw new ProductNotFoundException(product2);
            relationMatrix[index1][index2] = value;
            relationMatrix[index2][index1] = value;
        }

        SimilarityTable similarityTable = new SimilarityTable(newId, fastIndexes, relationMatrix);
        similarityTableContainer.addSimilarityTable(newId, similarityTable);

        return newId;
    }

    /**
     * Modifica els valors de les similituds d'una taula de similitud
     * 
     * @param id                 Identificador de la taula de similitud a modificar
     * @param nuevasSimilitudes  Llista de noves similituds entre dos productes ja existents a la taula
     * @throws SimilarityTableNotFoundException Si la taula de similitud a modificar no existeix
     * @throws ProductNotFoundException          Si algun dels productes no existeix
     */
    public void modifySimilarityTable(int id, List<Pair<Pair<String, String>, Double>> nuevasSimilitudes)
            throws SimilarityTableNotFoundException, ProductNotFoundException {
        SimilarityTable similarityTable = similarityTableContainer.getSimilarityTableById(id);
        HashMap<String, Integer> fastIndexes = similarityTable.getFastIndexes();
        double[][] relationMatrix = similarityTable.getRelationMatrix();

        for (Pair<Pair<String, String>, Double> similitud : nuevasSimilitudes) {
            String product1 = similitud.first().first();
            String product2 = similitud.first().second();
            Double value = similitud.second();


            Integer index1 = fastIndexes.get(product1);
            if (index1 == null) throw new ProductNotFoundException(product1);
            Integer index2 = fastIndexes.get(product2);
            if (index2 == null) throw new ProductNotFoundException(product2);
            relationMatrix[index1][index2] = value;
            relationMatrix[index2][index1] = value;

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
     * Retorna una taula de similitud si existeix
     * 
     * @param id Identificador de la taula de similitud a buscar
     * @return Pair amb els productes de la taula i la matriu de similituds
     * @throws SimilarityTableNotFoundException Si la taula de similitud a buscar no existeix
     */
    public Pair<Vector<Pair<String, Integer>>, double[][]> getSimilarityTable(int id)
            throws SimilarityTableNotFoundException {
        SimilarityTable similarityTable = similarityTableContainer.getSimilarityTableById(id);

        Vector<Pair<String, Integer>> productos = new Vector<>();
        for (String key : similarityTable.getFastIndexes().keySet()) {
            productos.add(new Pair<>(key, similarityTable.getFastIndexes().get(key)));
        }

        double[][] relationMatrix = similarityTable.getRelationMatrix();

        return new Pair<>(productos, relationMatrix);
    }

    /**
     * Crea i afegeix una nova distribució al container de distribucions
     *
     * @param similarityTableId Identificador de la taula de similitud a generar
     * @param cost              Cost de la distribució
     * @param order             Ordre dels productes de la distribució (Producte1, Producte2, ..., ProducteN)
     * @param usedAlgorithm     Nom de l'algoritme utilitzat per generar la distribució
     * @return Identificador de la distribució creada
     */
    public int generateDistribution(int similarityTableId, double cost, Vector<String> order,
            String usedAlgorithm) {
        int id = distributionContainer.newId();
        Distribution distribution = new Distribution(id, similarityTableId, cost, order, usedAlgorithm, 0);
        distributionContainer.addDistribution(id, distribution);

        return distribution.getId();
    }

    /**
     * Modifica una distribució existent
     *
     * @param id      Identificador de la distribució a modificar
     * @param changes Llista de productes a intercanviar d'ordre
     * @throws DistributionNotFoundException Si la distribució a modificar no existeix
     * @throws SimilarityTableNotFoundException Si la taula de similitud de la distribució no existeix
     */
    public void modifyDistribution(int id, List<Pair<String, String>> changes) throws DistributionNotFoundException, SimilarityTableNotFoundException {
        Distribution distribution = distributionContainer.getDistributionById(id);

        for (Pair<String, String> change : changes) {
            String p1 = change.first();
            String p2 = change.second();

            if (distribution.getOrder().contains(p1) || distribution.getOrder().contains(p2)) {
                distribution.changeOrder(p1, p2);
            }
        }

        SimilarityTable similarityTable = similarityTableContainer.getSimilarityTableById(distribution.getSimilarityTableId());
        double[][] relationMatrix = similarityTable.getRelationMatrix();
        HashMap<String, Integer> fastIndexes = similarityTable.getFastIndexes();
        Vector<String> order = distribution.getOrder();

        double cost = 0.0;
        for (int i = 0; i < order.size(); i++) {
            Integer index1 = fastIndexes.get(order.get(i));
            Integer index2 = fastIndexes.get(order.get((i + 1) % order.size()));
            cost += relationMatrix[index1][index2];
        }

        distribution.setCost(cost);
    }

    /**
     * Elimina una distribució del container de distribucions
     *
     * @param id Identificador de la distribució a eliminar
     * @throws DistributionNotFoundException Si la distribució a eliminar no existeix
     */
    public void deleteDistribution(int id) throws DistributionNotFoundException {
        distributionContainer.deleteDistributionById(id);
    }

    // TODO cambiar el return per un objecte, no es compleix la estructura de capes
    /**
     * Retorna una distribucio si existeix
     *
     * @param id Identificador de la distribució a buscar
     * @return Distribució amb l'identificador id
     * @throws DistributionNotFoundException Si la distribució a retornar no existeix
     */
    public Distribution getDistribution(int id) throws DistributionNotFoundException {
        Distribution distribution = distributionContainer.getDistributionById(id);
        return distribution;
    }

    /**
     * Afegeix al programa els productes importats
     * @param path Ruta on es troba el fitxer amb els productes
     *
     * @throws IncorrectPathException Si la ruta no és correcte
     * @throws NoTypeWithName         Si el tipus no existeix
     */
    public void importProducts(String path) throws IncorrectPathException, NoTypeWithName {
        List<JsonObject> products;
        try {
            products = cP.importProducts(path);
        } catch (IncorrectPathException e) {
            throw new IncorrectPathException(path);
        }

        for (JsonObject product : products) {
            String name = product.get("name").getAsString();
            String type = product.get("type").getAsString();
            try {
                addProduct(name, type);
            } catch (ProductAlreadyExistsException e) {
                continue;
            } catch (NoTypeWithName e) {
                throw new NoTypeWithName(type);
            }
        }
    }

    /**
     * Afegeix al programa les taules de similituds importades
     * @param path Ruta on es troba el fitxer amb les taules de similituds
     *
     * @throws IncorrectPathException Si la ruta no és correcte
     * @throws ProductNotFoundException Si algun dels productes no existeix
     */
    public void importSimilarityTables(String path) throws IncorrectPathException, ProductNotFoundException {
        List< Pair< List<String>, List< Pair<Pair<String, String>, Double> > > > similarityTables;
        try {
            similarityTables = cP.importSimilarityTables(path);
        } catch (IncorrectPathException e) {
            throw new IncorrectPathException(path);
        }

        for (Pair< List<String>, List< Pair<Pair<String, String>, Double> > > similarityTable : similarityTables) {
            List<String> products = similarityTable.first();
            for(String product : products) {
                try {
                    getProduct(product);
                } catch (ProductNotFoundException e) {
                    throw new ProductNotFoundException(product);
                }
            }
            List<Pair<Pair<String, String>, Double>> similarities = similarityTable.second();
            try {
                addSimilarityTable(products, similarities);
            } catch (ProductNotFoundException e) {
                throw new ProductNotFoundException(e.getMessage());
            }
        }
    }

    /**
     * Exporta els productes a un fitxer
     * @param path Ruta on es guardarà el fitxer amb els productes
     *
     * @throws IncorrectPathException Si la ruta no és correcte
     */
    public void exportProducts(String path) throws IncorrectPathException {
        List<JsonObject> products = new ArrayList<>();
        for (Product product : productContainer.getProducts().values()) {
            JsonObject productJson = new JsonObject();
            productJson.addProperty("name", product.getName());
            productJson.addProperty("type", product.getType().toString());
            products.add(productJson);
        }
        try {
            cP.exportProducts(path, products);
        } catch (IncorrectPathException e) {
            throw new IncorrectPathException(path);
        }
    }

    /**
     * Exporta les taules de similituds a un fitxer
     * @param path Ruta on es guardarà el fitxer amb les taules de similituds
     *
     * @throws IncorrectPathException Si la ruta no és correcte
     */
    public void exportSimilarityTables(String path) throws IncorrectPathException {
        List<JsonObject> similarityTables = new ArrayList<>();
        for(SimilarityTable similarityTable : similarityTableContainer.getSimilarityTables().values()) {
            JsonObject STObject = new JsonObject();
            STObject.addProperty("id", similarityTable.getId());

            JsonArray productsArray = new JsonArray();
            for(String product : similarityTable.getFastIndexes().keySet()) {
                productsArray.add(product);
            }
            STObject.add("products", productsArray);

            JsonArray similaritiesArray = new JsonArray();
            for(int i = 0; i < similarityTable.getRelationMatrix().length; i++) {
                for(int j = i + 1; j < similarityTable.getRelationMatrix().length; j++) {
                    JsonObject similarityObject = new JsonObject();
                    similarityObject.addProperty("product1", similarityTable.getFastIndexes().keySet().toArray()[i].toString());
                    similarityObject.addProperty("product2", similarityTable.getFastIndexes().keySet().toArray()[j].toString());
                    similarityObject.addProperty("similarity", similarityTable.getRelationMatrix()[i][j]);
                    similaritiesArray.add(similarityObject);
                }
            }

            STObject.add("similarities", similaritiesArray);
            similarityTables.add(STObject);
        }
        try {
            cP.exportSimilarityTables(path, similarityTables);
        } catch (IncorrectPathException e) {
            throw new IncorrectPathException(path);
        }
    }
}