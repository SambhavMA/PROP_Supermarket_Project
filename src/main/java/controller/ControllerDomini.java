package controller;

import model.algorithm.AlgorithmController;
import model.algorithm.AlgorithmControllerSolution;
import model.algorithm.AlgorithmsNames;
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

import java.text.DecimalFormat;
import java.util.*;
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
     * Retorna els tipus de productes del sistema
     *
     * @return Una array de Strings dels tipus de productes del sistema
     */
    public String[] getProductsTypes() {
        return new String[]{Arrays.toString(EnumType.values())};
    }

    /**
     * Retorna els productes del sistema
     *
     * @return Array de Pair amb identificadors de producte: {name, tipus}, retorna array buida si no hi ha productes al sistema
     */
    public Pair<String, String>[] getProducts() {
        HashMap<String, Product> products = productContainer.getProducts();
        Pair<String, String>[] pairsArray = new Pair[0];
        if (! products.isEmpty()) {
            List<Pair<String, String>> pairsList = new ArrayList<>();

            for (Product product : products.values()) {
                pairsList.add(new Pair<>(product.getName(), product.getType().toString()));
            }
            pairsArray = pairsList.toArray(new Pair[0]);
        }

        return pairsArray;
    }

    public void testingAddingProducts() throws ProductAlreadyExistsException {
        productContainer.addProduct(new Product("Bistec", EnumType.CARNE));
        productContainer.addProduct(new Product("Salmon", EnumType.PESCADO));
        productContainer.addProduct(new Product("Lomo", EnumType.CARNE));
        productContainer.addProduct(new Product("Atun", EnumType.PESCADO));
        productContainer.addProduct(new Product("Manzana", EnumType.FRUTA));
        productContainer.addProduct(new Product("Pera", EnumType.FRUTA));
//        productContainer.addProduct(new Product("P7", EnumType.CARN));
//        productContainer.addProduct(new Product("P8", EnumType.PEIX));
//        productContainer.addProduct(new Product("P9", EnumType.CARN));
//        productContainer.addProduct(new Product("P10", EnumType.PEIX));
//        productContainer.addProduct(new Product("P11", EnumType.FRUITA));
//        productContainer.addProduct(new Product("P12", EnumType.FRUITA));
    }

    /**
     * Retorna les taules de similituds del sistema
     *
     * @return Array de Strings amb els identificadors de les taules de similituds
     */
    public String[] getSimilarityTables() {
        HashMap<Integer, SimilarityTable> similarityTableHashMap = similarityTableContainer.getSimilarityTables();

        String[] ids = new String[similarityTableHashMap.size()];
        int i = 0;
        for (SimilarityTable s : similarityTableHashMap.values()) {
            ids[i] = ""+s.getId();
            i++;
        }

        return ids;
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

    public int getSimilarityTableNextId() {
        return similarityTableContainer.nextId();
    }

    /**
     * Parseja les dades en format simple i despres crea una taula de similitud
     *
     * @param productosP   Array de Strings amb els noms dels productes de la taula de similitud
     * @param similitudesP Array de Strings amb les similituds entre productes (producte1, producte2, similitud)
     * @return Identificador de la taula de similitud creada
     * @throws WrongInputException Si les dades estan en un format incorrecte
     * @throws ProductNotFoundException Si algun dels productes que formen la taula no existeix
     */
    public int addSimilarityTable(String[] productosP, String[] similitudesP) throws WrongInputException, ProductNotFoundException {
        List<Pair<Pair<String, String>, Double>> similitudes = new ArrayList<>();
        List<String> productos = new ArrayList<>();

        try {
            for (String v : similitudesP) {
                String[] linea = v.split(" ");
                Pair<String, String> p = new Pair<>(linea[0], linea[1]);
                Pair<Pair<String, String>, Double> p2 = new Pair<>(p, Double.parseDouble(linea[2]));
                similitudes.add(p2);
            }
        } catch (Exception e) {
            throw new WrongInputException();
        }

        for (String p : productosP) {
            try {
                Product product = productContainer.getProductByName(p);
                productos.add(product.getName());
            } catch (ProductNotFoundException e) {
                throw new ProductNotFoundException(p);
            }
        }

        return addSimilarityTable(productos, similitudes);
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

//    /**
//     * Retorna una taula de similitud si existeix
//     *
//     * @param id Identificador de la taula de similitud a buscar
//     * @return Pair amb els productes de la taula i la matriu de similituds
//     * @throws SimilarityTableNotFoundException Si la taula de similitud a buscar no existeix
//     */
//    public Pair<Vector<Pair<String, Integer>>, double[][]> getSimilarityTable(int id)
//            throws SimilarityTableNotFoundException {
//        SimilarityTable similarityTable = similarityTableContainer.getSimilarityTableById(id);
//
//        Vector<Pair<String, Integer>> productos = new Vector<>();
//        for (String key : similarityTable.getFastIndexes().keySet()) {
//            productos.add(new Pair<>(key, similarityTable.getFastIndexes().get(key)));
//        }
//
//        double[][] relationMatrix = similarityTable.getRelationMatrix();
//
//        return new Pair<>(productos, relationMatrix);
//    }

    /**
     * Crea i afegeix una nova distribució al container de distribucions
     *
     * @param similarityTableId Identificador de la taula de similitud a generar
     * @param cost              Cost de la distribució
     * @param order             Ordre dels productes de la distribució (Producte1, Producte2, ..., ProducteN)
     * @param usedAlgorithm     Nom de l'algoritme utilitzat per generar la distribució
     * @return Identificador de la distribució creada
     */
    public int createDistribution(int similarityTableId, double cost, double temps, Vector<String> order,
            String usedAlgorithm) {
        int id = distributionContainer.newId();
        Distribution distribution = new Distribution(id, similarityTableId, cost, order, usedAlgorithm, temps);
        distributionContainer.addDistribution(id, distribution);

        return distribution.getId();
    }

    public void generateDistribution(int stId, String algorithm) throws SimilarityTableNotFoundException, DistributionCreationErrorException, AlgorithmException, MSTTriangleInequalityException {
        Pair<Vector<Pair<String, Integer>>, double[][]> similarityTable = getSimilarityTable(stId);
        double[][] relationMatrix = similarityTable.second();

        if (relationMatrix.length < 4) throw new AlgorithmException("La cantidad de productos es demasiado pequeña para utilizar un algoritmo. Como minimo se aceptan 4.");

        AlgorithmController cAlg = new AlgorithmController(relationMatrix);
        AlgorithmControllerSolution result = cAlg.executeAlgorithm(algorithm);

        try {
            int[] path = result.getOrder();
            double cost = result.getCost();
            double temps = result.getTemps();

            Vector<Pair<String, Integer>> fastIndexes = similarityTable.first();
            Vector<String> names = new Vector<>(path.length);

            for (int n : path) {
                for (Pair<String, Integer> pair : fastIndexes) {
                    if (pair.second().equals(n)) {
                        names.add(pair.first());
                        break;
                    }
                }
            }


            createDistribution(stId, cost, temps, names, algorithm);
        } catch (Exception e) {
            throw new DistributionCreationErrorException();
        }

        if (algorithm.equals(AlgorithmsNames.MST.toString())) {
            boolean isTriangleInequality = true;

            int quadraticLength = relationMatrix.length;
            for (int i = 0; i < quadraticLength; i++) {
                for (int j = 0; j < quadraticLength; j++) {
                    for (int k = 0; k < quadraticLength; k++) {
                        if (relationMatrix[i][j] >= relationMatrix[i][k] + relationMatrix[j][k]) {
                            isTriangleInequality = false;
                        }
                    }
                }
            }

            if (isTriangleInequality) throw new MSTTriangleInequalityException();
        }

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
    public String[][] getDistribution(int id) throws DistributionNotFoundException {
        DecimalFormat df = new DecimalFormat("#.###");

        Distribution distribution = distributionContainer.getDistributionById(id);
        return new String[][]{new String[]{""+distribution.getId()},
                new String[]{""+distribution.getSimilarityTableId()},
                new String[]{df.format(distribution.getCost())},
                new String[]{""+distribution.getTemps()},
                distribution.getOrder().toArray(new String[0]),
                new String[]{distribution.getUsedAlgorithm()}
        };
    }

    /**
     * Retorna les distribucions del sistema
     *
     * @return Array de Strings amb els identificadors de les distribucions, la taula de similitud que s'ha utilitzat, l'algoritme utilitzat i el cost de la distribució
     */
    public String[][] getDistributions() {
        DecimalFormat df = new DecimalFormat("#.###");

        HashMap<Integer, Distribution> distributions = distributionContainer.getDistributions();
        String[][] parameters = new String[0][0];

        if (! distributions.isEmpty()) {
            List<String[]> parametersList = new ArrayList<>();

            for (Distribution distribution : distributions.values()) {
                parametersList.add(new String[]{""+distribution.getId(),
                        ""+distribution.getSimilarityTableId(), distribution.getUsedAlgorithm(),
                        df.format(distribution.getCost()), ""+distribution.getTemps()});
            }

            parameters = parametersList.toArray(new String[0][0]);
        }

        return parameters;
    }

    /**
     * Retorna els algoritmes disponibles
     *
     * @return Array de Strings amb els noms dels algoritmes disponibles
     */
    public String[] getAlgorithms() {
        return Arrays.stream(AlgorithmsNames.values())
                .map(Enum::name)
                .toArray(String[]::new);
    }

    /**
     * Retorna el següent identificador de distribució
     *
     * @return Següent identificador de distribució
     */
    public int getDistributionNextId() {
        return distributionContainer.nextId();
    }

    /**
     * Afegeix al programa els productes importats
     *
     * @throws IncorrectPathException Si la ruta no és correcte
     * @throws NoTypeWithName         Si el tipus no existeix
     */
    public void importProducts() throws Exception {
        List<JsonObject> products = List.of();
        products = cP.importProducts();

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
     *
     * @throws IncorrectPathException Si la ruta no és correcte
     * @throws ProductNotFoundException Si algun dels productes no existeix
     */
    public void importSimilarityTables() throws Exception {
        List< Pair< List<String>, List< Pair<Pair<String, String>, Double> > > > similarityTables = List.of();
        similarityTables = cP.importSimilarityTables();

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
     *
     * @throws IncorrectPathException Si la ruta no és correcte
     */
    public void exportProducts() throws IncorrectPathException {
        List<JsonObject> products = new ArrayList<>();
        for (Product product : productContainer.getProducts().values()) {
            JsonObject productJson = new JsonObject();
            productJson.addProperty("name", product.getName());
            productJson.addProperty("type", product.getType().toString());
            products.add(productJson);
        }
        try {
            cP.exportProducts(products);
        } catch (IncorrectPathException e) {
            //throw new IncorrectPathException();
        }
    }

    /**
     * Exporta les taules de similituds a un fitxer
     *
     * @throws IncorrectPathException Si la ruta no és correcte
     */
    public void exportSimilarityTables() throws IncorrectPathException {
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
            cP.exportSimilarityTables(similarityTables);
        } catch (IncorrectPathException e) {
        }
    }
}