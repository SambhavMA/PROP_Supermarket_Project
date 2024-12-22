package controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import persistence.FileManager;
import persistence.JSON.JsonManager;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

import utils.Pair;
import model.exceptions.IncorrectPathException;

import javax.swing.JFileChooser;


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

    /**
     * Retorna la instancia de ControllerPersistencia
     * @return instancia de ControllerPersistencia
     */
    public static ControllerPersistencia getInstance() {
        if (instance == null) {
            instance = new ControllerPersistencia();
        }
        return instance;
    }

    /**
     * Retorna una lista con los productos importados desde un archivo seleccionado.
     * @return lista de productos.
     * @throws Exception si ocurre un error al importar o el formato es incorrecto.
     */
    public List<JsonObject> importProducts() throws Exception {
        File file = selectFile("Seleccionar archivo JSON para productos", true);
        JsonObject jsonData;

        try {
            jsonData = fileManager.importFromFile(file);
        } catch (Exception e) {
            throw new Exception("Error al importar productos: " + e.getMessage());
        }

        JsonArray productsArray = jsonData.getAsJsonArray("Products");
        if (productsArray == null) {
            throw new Exception("Error: Formato de productos incorrecto.");
        }

        List<JsonObject> products = new ArrayList<>();
        for (JsonElement productElement : productsArray) {
            products.add(productElement.getAsJsonObject());
        }
        return products;
    }

    /**
     * Retorna una lista de Pairs de productos y similitudes desde un archivo seleccionado.
     * @return lista de Pairs con productos y similitudes.
     * @throws Exception si ocurre un error al importar o el formato es incorrecto.
     */
    public List<Pair<List<String>, List<Pair<Pair<String, String>, Double>>>> importSimilarityTables() throws Exception {
        File file = selectFile("Seleccionar archivo JSON para tablas de similitudes", true);
        JsonObject jsonData;

        try {
            jsonData = fileManager.importFromFile(file);
        } catch (Exception e) {
            throw new Exception("Error al importar tablas de similitudes: " + e.getMessage());
        }

        JsonArray STArray = jsonData.getAsJsonArray("SimilarityTables");
        if (STArray == null) {
            throw new Exception("Error: Formato de la Tabla de Similitudes incorrecto.");
        }

        List<Pair<List<String>, List<Pair<Pair<String, String>, Double>>>> similarityTables = new ArrayList<>();
        for (JsonElement ST : STArray) {
            JsonObject STObject = ST.getAsJsonObject();

            List<String> products = new ArrayList<>();
            for (JsonElement product : STObject.getAsJsonArray("products")) {
                products.add(product.getAsString());
            }

            List<Pair<Pair<String, String>, Double>> similarities = new ArrayList<>();
            for (JsonElement similarity : STObject.getAsJsonArray("similarities")) {
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
     * Exporta los productos a un archivo seleccionado.
     * @param products lista de productos a exportar.
     * @throws IncorrectPathException si la ruta no es correcta.
     */
    public void exportProducts(List<JsonObject> products) throws Exception {
        File file = selectFile("Guardar archivo JSON para productos", false);

        JsonObject result = new JsonObject();
        JsonArray productsArray = new JsonArray();
        for (JsonObject product : products) {
            productsArray.add(product);
        }
        result.add("Products", productsArray);

        try {
            fileManager.exportToFile(result, file);
        } catch (Exception e) {
            throw new IncorrectPathException("Error al exportar productos: " + e.getMessage());
        }
    }


    /**
     * Exporta las tablas de similitudes a un archivo seleccionado.
     * @param similarityTable lista de tablas de similitudes a exportar.
     * @throws IncorrectPathException si la ruta no es correcta.
     */
    public void exportSimilarityTables(List<JsonObject> similarityTable) throws Exception {
        File file = selectFile("Guardar archivo JSON para tablas de similitudes", false);

        JsonObject result = new JsonObject();
        JsonArray STArray = new JsonArray();
        for (JsonObject ST : similarityTable) {
            STArray.add(ST);
        }
        result.add("SimilarityTables", STArray);

        try {
            fileManager.exportToFile(result, file);
        } catch (Exception e) {
            throw new IncorrectPathException("Error al exportar tablas de similitudes: " + e.getMessage());
        }
    }

    /**
     * Abre un JFileChooser para seleccionar un archivo.
     * @param dialogTitle Título del diálogo.
     * @param forRead True si es para lectura, False si es para escritura.
     * @return Archivo seleccionado por el usuario.
     * @throws Exception si el usuario cancela la operación.
     */
    private File selectFile(String dialogTitle, boolean forRead) throws Exception {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(dialogTitle);

        if (!forRead) {
            fileChooser.setSelectedFile(new File("output.json")); // Nombre por defecto para escritura
        } else {
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos JSON", "json"));
        }

        int userSelection = forRead ? fileChooser.showOpenDialog(null) : fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            throw new Exception("Operación cancelada por el usuario.");
        }
    }

}
