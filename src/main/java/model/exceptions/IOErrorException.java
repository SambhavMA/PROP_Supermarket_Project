package model.exceptions;

public class IOErrorException extends RuntimeException {
    public IOErrorException(String path, String type) {
        super("Error " + type + " the file: " + path);
    }
}
