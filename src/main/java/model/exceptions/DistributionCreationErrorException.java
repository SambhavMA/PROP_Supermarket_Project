package model.exceptions;

public class DistributionCreationErrorException extends RuntimeException {
    public DistributionCreationErrorException() {
        super("No se ha podido generar la distribucion por un error desconocido");
    }
}
