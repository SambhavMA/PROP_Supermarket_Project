package model.exceptions;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 */
public class SimilarityTableNotFoundException extends Exception {
    public SimilarityTableNotFoundException(Integer id) {
        super("La tabla de similitud con id: " + id + " no se ha encontrado en el sistema.");
    }
}
