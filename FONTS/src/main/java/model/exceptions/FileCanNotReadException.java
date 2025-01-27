package model.exceptions;

public class FileCanNotReadException extends RuntimeException {
    public FileCanNotReadException(String path) {
        super("The file in " + path + " does not have read permissions.");
    }
}
