package model.exceptions;

public class WrongInputException extends RuntimeException {
    public WrongInputException() {
        super("El input esta en formato incorrecto");
    }
}
