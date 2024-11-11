package model.exceptions;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String productName) {
        super("The product " + productName + " was not found in the system.");
    }
}
