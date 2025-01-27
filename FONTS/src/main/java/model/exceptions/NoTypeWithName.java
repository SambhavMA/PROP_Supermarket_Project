package model.exceptions;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 */
public class NoTypeWithName extends Exception {
    public NoTypeWithName(String type) {
        super("El tipo: " + type + " no es un tipo valido.");
    }
}
