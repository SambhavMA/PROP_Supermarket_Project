package persistence;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import utils.Pair;

import model.exceptions.IncorrectPath;

/**
 * @author David Calvo Espases (david.calvo.espases@estudiantat.upc.edu)
 * <p>Interficie para la gestion de ficheros</p>
 */

public interface FileManager {

    HashMap<String, String> importProducts(String path) throws IncorrectPath;
    Pair<Vector<Pair<String, Integer>>, double[][]> importSimilarityTable(String path) throws IncorrectPath;
    void exportProducts(String path, HashMap<String, String> products) throws IncorrectPath;
    void exportSimilarityTable(String path, Pair<Vector<Pair<String, Integer>>, double[][]> similarityTable) throws IncorrectPath;


}
