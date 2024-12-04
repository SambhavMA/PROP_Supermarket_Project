package persistence.JSON;

import persistence.FileManager;
import utils.Pair;
import model.exceptions.IncorrectPath;

import java.util.HashMap;
import java.util.Vector;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author David Calvo Espases (david.calvo.espases@estudiantat.upc.edu)
 * <p>Clase para la gestion de ficheros JSON</p>
 */

public class JsonManager implements FileManager{
    public JsonManager() {
        ;
    }

    /**
     * Importa los productos de un fichero JSON
     * @param path ruta donde se obtienen los productos
     * @throws IncorrectPath si la ruta no es correcta
     */
    @Override
    public HashMap<String, String> importProducts(String path) throws IncorrectPath {
        return null;
    }

    /**
     * Importa la tabla de similitud de un fichero JSON
     * @param path ruta donde se obtiene la tabla de similitud
     * @throws IncorrectPath si la ruta no es correcta
     */
    @Override
    public Pair<Vector<Pair<String, Integer>>, double[][]> importSimilarityTable(String path) throws IncorrectPath {
        return null;
    }

    /**
     * Exporta los productos a un fichero JSON
     * @param path ruta donde se creara el fichero
     * @param products productos a exportar
     * @throws IncorrectPath si la ruta no es correcta
     */
    @Override
    public void exportProducts(String path, HashMap<String, String> products) throws IncorrectPath {
        try {
            FileWriter file = new FileWriter(path);
            file.write(products.toString());
            file.close();
        } catch (IOException e) {
            throw new IncorrectPath(path);
        }
    }

    /**
     * Exporta la tabla de similitud a un fichero JSON
     * @param path ruta donde se creara el fichero
     * @param similarityTable tabla de similitud a exportar
     * @throws IncorrectPath si la ruta no es correcta
     */
    @Override
    public void exportSimilarityTable(String path, Pair<Vector<Pair<String, Integer>>, double[][]> similarityTable) throws IncorrectPath {
        try {
            FileWriter file = new FileWriter(path);
            file.write(similarityTable.toString());
            file.close();
        } catch (IOException e) {
            throw new IncorrectPath(path);
        }
    }
}


