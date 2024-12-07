package controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import persistence.FileManager;
import persistence.JSON.JsonManager;

import java.util.List;
import java.util.ArrayList;

import utils.Pair;
import model.exceptions.IncorrectPath;


/**
 * @author David Calvo Espases (david.calvo.espases@estudiantat.upc.edu)
 * <p>Se encarga de gestionar las operaciones de persistencia.
 * Estas operaciones son importar o exportar productos y tablas de similitud.
 * Tiene la interficie de FileManager como atributo.</p>
 */

public class ControllerPersistencia {

    private FileManager fileManager;
    public ControllerPersistencia() {
        fileManager = new JsonManager();
    }

    /**
     * Retorna un HashMap con los productos importados
     * @param path ruta donde se obtienen los productos
     * @throws IncorrectPath si la ruta no es correcta
     */
    public List<JsonObject> importProducts(String path) throws IncorrectPath{
        JsonObject jsonData;
        try{
            jsonData = fileManager.importFromFile(path);
        } catch (IncorrectPath e) {
            throw new IncorrectPath(path);
        }
        JsonArray productsArray = jsonData.getAsJsonArray("Products");
        List<JsonObject> products = new ArrayList<>();
        for (JsonElement productElement : productsArray) {
            products.add(productElement.getAsJsonObject());
        }
        return products;
    }

    /**
     * Retorna un Pair de la lista de productos de cada tabla de similitud importada y las similitudes de estos
     * @param path ruta donde se obtienen la tabla de similitud
     * @throws IncorrectPath si la ruta no es correcta
     */
    public List< Pair< List<String>, List< Pair<Pair<String, String>, Double> > > > importSimilarityTable(String path) throws IncorrectPath {
        JsonObject jsonData;
        try{
            jsonData = fileManager.importFromFile(path);
        } catch (IncorrectPath e) {
            throw new IncorrectPath(path);
        }
        List< Pair< List<String>, List< Pair<Pair<String, String>, Double> > > > similarityTables = new ArrayList<>();
        JsonArray STArray = jsonData.getAsJsonArray("SimilarityTables");
        for(JsonElement ST : STArray){
            //Lista de los productos de la tabla de similitud
            List<String> products = new ArrayList<>();
            for(JsonElement product : ST.getAsJsonObject().getAsJsonArray("products")){
                products.add(product.getAsString());
            }

            //Lista de las similitudes de los productos
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
     * @param path ruta donde se creara el fichero
     * @param products productos a exportar
     * @throws IncorrectPath si la ruta no es correcta
     */
    public void exportProducts(String path, List<JsonObject> products) throws IncorrectPath{
        JsonObject result = new JsonObject();
        JsonArray productsArray = new JsonArray();
        for (JsonObject product : products) {
            productsArray.add(product);
        }
        result.add("Products", productsArray);
        try {
            fileManager.exportToFile(path, result);
        } catch (IncorrectPath e) {
            throw new IncorrectPath(path);
        }
    }

    /**
     * Exporta la tabla de similitud a un fichero
     * @param path ruta donde se creara el fichero
     * @param similarityTable tabla de similitud a exportar
     * @throws IncorrectPath si la ruta no es correcta
     */
    public void exportSimilarityTable(String path, List<JsonObject> similarityTable) throws IncorrectPath{
        JsonObject result = new JsonObject();
        JsonArray STArray = new JsonArray();
        for (JsonObject ST : similarityTable) {
            STArray.add(ST);
        }
        result.add("SimilarityTables", STArray);
        try {
            fileManager.exportToFile(path, result);
        } catch (IncorrectPath e) {
            throw new IncorrectPath(path);
        }
    }
}
