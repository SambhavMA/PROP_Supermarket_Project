package model.exceptions;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 */
public class DistributionNotFoundException extends Exception {
    public DistributionNotFoundException(int id) {
        super("La distribucion con id: " + id + " no se ha encontrado en el sistema.");
    }
}
