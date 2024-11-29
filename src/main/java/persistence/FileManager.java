package persistence;

import java.util.HashMap;

import model.exceptions.IncorrectPath;

/**
 * @author David Calvo Espases (david.calvo.espases@estudiantat.upc.edu)
 * <p>Interficie para la gestion de ficheros</p>
 */

public interface FileManager {

    /*
    public void exportProducts(HashMap<String, String> products);
    public void exportSimilarityTable(Pair<Vector<Pair<String, Integer>>, double[][]> similarityTable);
    public HashMap<String, String> importProducts();
    public Pair<Vector<Pair<String, Integer>>, double[][]> importSimilarityTable();
     */

    public void generateTestFile(String path) throws IncorrectPath;
}
