package model.exceptions;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 */
public class NoTypeWithName extends Exception {
    public NoTypeWithName(String type) {
        super("The type " + type + " is not a valid type.");
    }
}
