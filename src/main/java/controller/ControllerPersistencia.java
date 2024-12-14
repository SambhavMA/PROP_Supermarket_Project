package controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import persistence.FileManager;
import persistence.JSON.JsonManager;

import java.util.List;
import java.util.ArrayList;

import utils.Pair;
import model.exceptions.IncorrectPathException;


/**
 * @author David Calvo Espases (david.calvo.espases@estudiantat.upc.edu)
 * <p>Se encarga de gestionar las operaciones de persistencia.
 * Estas operaciones son importar o exportar productos y tablas de similitudes.
 * Tiene la interficie de FileManager como atributo.</p>
 */

public class ControllerPersistencia {
    private static ControllerPersistencia instance;
    private FileManager fileManager;
    public ControllerPersistencia() {
        fileManager = new JsonManager();
    }

    public static ControllerPersistencia getInstance() {
        if (instance == null) {
            instance = new ControllerPersistencia();
        }
        return instance;
    }

    /**
     * Retorna una lista con los productos importados
     * @return lista de productos
     * @throws IncorrectPathException si la ruta no es correcta
     */
    public List<JsonObject> importProducts() throws IncorrectPathException {
        JsonObject jsonData = null;
        try{
            jsonData = fileManager.importFromFile();
        } catch (IncorrectPathException e) {
            // throw new IncorrectPathException();
        }
        JsonArray productsArray = jsonData.getAsJsonArray("Products");
        List<JsonObject> products = new ArrayList<>();
        for (JsonElement productElement : productsArray) {
            products.add(productElement.getAsJsonObject());
        }
        return products;
    }

    /**
     * Retorna una lista de Pairs de la lista de productos de cada tabla de similitud importada y las similitudes de estos
     * @param path ruta donde se obtienen las tabla de similitudes
     * @return lista de Pairs de la lista de productos de cada tabla de similitud importada y las similitudes de estos
     * @throws IncorrectPathException si la ruta no es correcta
     */
    public List< Pair< List<String>, List< Pair<Pair<String, String>, Double> > > > importSimilarityTables(String path) throws IncorrectPathException {
        JsonObject jsonData;
        try{
            jsonData = fileManager.importFromFile();
        } catch (IncorrectPathException e) {
            throw new IncorrectPathException(path);
        }
        List< Pair< List<String>, List< Pair<Pair<String, String>, Double> > > > similarityTables = new ArrayList<>();
        JsonArray STArray = jsonData.getAsJsonArray("SimilarityTables");
        for(JsonElement ST : STArray){
            List<String> products = new ArrayList<>();
            for(JsonElement product : ST.getAsJsonObject().getAsJsonArray("products")){
                products.add(product.getAsString());
            }

            List< Pair<Pair<String, String>, Double> > similarities = new ArrayList<>();
            for(JsonElement similarity : ST.getAsJsonObject().getAsJsonArray("similarities")){
                JsonObject similarityObject = similarity.getAsJsonObject();
                String product1 = similarityObject.get("product1").getAsString();
                String product2 = similarityObject.get("product2").getAsString();
                Double similarityValue = similarityObject.get("similarity").getAsDouble();

                similarities.add(new Pair<>(new Pair<>(product1, product2), similarityValue));
            }

            similarityTables.add(new Pair<>(products, similarities));
        }
        return similarityTables;
    }

    /**
     * Exporta los productos a un fichero
     * @param products lista de productos a exportar
     *
     * @throws IncorrectPathException si la ruta no es correcta
     */
    public void exportProducts(List<JsonObject> products) throws IncorrectPathException {
        JsonObject result = new JsonObject();
        JsonArray productsArray = new JsonArray();
        for (JsonObject product : products) {
            productsArray.add(product);
        }
        result.add("Products", productsArray);
        try {
            fileManager.exportToFile(result);
        } catch (IncorrectPathException e) {
        }
    }

    /**
     * Exporta las tablas de similitudes a un fichero
     * @param path ruta donde se creara el fichero
     * @param similarityTable lista de tablas de similitudes a exportar
     * @throws IncorrectPathException si la ruta no es correcta
     */
    public void exportSimilarityTables(String path, List<JsonObject> similarityTable) throws IncorrectPathException {
        JsonObject result = new JsonObject();
        JsonArray STArray = new JsonArray();
        for (JsonObject ST : similarityTable) {
            STArray.add(ST);
        }
        result.add("SimilarityTables", STArray);
        try {
            fileManager.exportToFile(result);
        } catch (IncorrectPathException e) {
            throw new IncorrectPathException(path);
        }
    }
}
