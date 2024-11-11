package model.exceptions;

public class ProductAlreadyExistsException extends Exception{
    public ProductAlreadyExistsException(String productName) {
        super("The product " + productName + " already exists in the system.");
    }
}
