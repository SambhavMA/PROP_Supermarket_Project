package model.exceptions;

public class FileCanNotWriteException extends RuntimeException {
    public FileCanNotWriteException(String path) {
        super("The file in " + path + " does not have write permissions.");
    }
}
