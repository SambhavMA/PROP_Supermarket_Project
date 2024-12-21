package model.exceptions;

public class MSTTriangleInequalityException extends RuntimeException {
    public MSTTriangleInequalityException() {
        super("El MST no garantiza optimalidad porque la tabla de similitud no cumple con la desigualdad triangular");
    }
}
