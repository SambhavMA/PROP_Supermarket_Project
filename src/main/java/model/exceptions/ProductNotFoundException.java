package model.exceptions;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 */
public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String productName) {
        super("The product " + productName + " was not found in the system.");
    }
}
