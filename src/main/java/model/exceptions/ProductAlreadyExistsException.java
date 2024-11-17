package model.exceptions;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 */
public class ProductAlreadyExistsException extends Exception{
    public ProductAlreadyExistsException(String productName) {
        super("The product " + productName + " already exists in the system.");
    }
}
