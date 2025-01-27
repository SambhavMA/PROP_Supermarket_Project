package model.exceptions;

/**
 * @author David Calvo Espases (david.calvo.espases@estudiantat.upc.edu)
 */

public class IncorrectPathException extends Exception {
    public IncorrectPathException(String path) {
        super("The path " + path + " is incorrect.");
    }
}