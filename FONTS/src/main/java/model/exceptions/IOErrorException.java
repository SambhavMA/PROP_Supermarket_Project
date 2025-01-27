package model.exceptions;

/**
 * @author David Calvo Espases (david.calvo.espases@estudiantat.upc.edu)
 */

public class IOErrorException extends RuntimeException {
    public IOErrorException(String path, String type) {
        super("Error " + type + " the file: " + path);
    }
}
