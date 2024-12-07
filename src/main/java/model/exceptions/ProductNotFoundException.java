package model.exceptions;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 */
public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String productName) {
        super("El producto " + productName + " no existe en el sistema.");
    }
}
