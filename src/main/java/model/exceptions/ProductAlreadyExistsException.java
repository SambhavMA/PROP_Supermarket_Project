package model.exceptions;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 */
public class ProductAlreadyExistsException extends Exception{
    public ProductAlreadyExistsException(String productName) {
        super("El producto: " + productName + " ya existe en el sistema.");
    }
}
