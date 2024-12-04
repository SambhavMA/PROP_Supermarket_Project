package controller;

import persistence.FileManager;
import persistence.JSON.JsonManager;

import java.util.HashMap;
import java.util.Vector;

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
    public HashMap<String, String> importProducts(String path) throws IncorrectPath{
        return fileManager.importProducts(path);
    }

    /**
     * Retorna un Pair con los productos y la tabla de similitud importados
     * @param path ruta donde se obtienen la tabla de similitud
     * @throws IncorrectPath si la ruta no es correcta
     */
    public Pair<Vector<Pair<String, Integer>>, double[][]> importSimilarityTable(String path) throws IncorrectPath{
        return fileManager.importSimilarityTable(path);
    }

    /**
     * Exporta los productos a un fichero
     * @param path ruta donde se creara el fichero
     * @param products productos a exportar
     * @throws IncorrectPath si la ruta no es correcta
     */
    public void exportProducts(String path, HashMap<String, String> products) throws IncorrectPath{
        fileManager.exportProducts(path, products);
    }

    /**
     * Exporta la tabla de similitud a un fichero
     * @param path ruta donde se creara el fichero
     * @param similarityTable tabla de similitud a exportar
     * @throws IncorrectPath si la ruta no es correcta
     */
    public void exportSimilarityTable(String path, Pair<Vector<Pair<String, Integer>>, double[][]> similarityTable) throws IncorrectPath{
        fileManager.exportSimilarityTable(path, similarityTable);
    }

}
