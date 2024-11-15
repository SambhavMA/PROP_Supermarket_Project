package model.exceptions;

public class NoTypeWithName extends Exception {
    public NoTypeWithName(String type) {
        super("The type " + type + " is not a valid type.");
    }
}
