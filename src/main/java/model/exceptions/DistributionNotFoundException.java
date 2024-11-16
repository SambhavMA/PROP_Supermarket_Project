package model.exceptions;

public class DistributionNotFoundException extends Exception {
    public DistributionNotFoundException(int id) {
        super("The distribution with id " + id + " was not found in the system.");
    }
}
