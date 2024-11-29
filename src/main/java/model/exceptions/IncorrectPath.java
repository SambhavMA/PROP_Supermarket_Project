package model.exceptions;

/**
 * @author David Calvo Espases (david.calvo.espases@estudiantat.upc.edu)
 */

public class IncorrectPath extends Exception {
    public IncorrectPath(String path) {
        super("The path " + path + " is incorrect.");
    }
}