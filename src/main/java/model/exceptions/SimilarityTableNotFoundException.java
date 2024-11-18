package model.exceptions;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 */
public class SimilarityTableNotFoundException extends Exception {
    public SimilarityTableNotFoundException(Integer id) {
        super("The similarity table with id " + id + " was not found in the system.");
    }
}
