package persistence.JSON;

import com.google.gson.JsonObject;
import com.google.gson.Gson;
import persistence.FileManager;
import model.exceptions.IncorrectPathException;
import model.exceptions.IOErrorException;
import model.exceptions.FileCanNotReadException;
import model.exceptions.FileCanNotWriteException;

import java.io.*;

/**
 * Implementación de FileManager para gestionar archivos JSON.
 */
public class JsonManager implements FileManager {
    private final Gson gson;

    public JsonManager() {
        this.gson = new Gson();
    }

    @Override
    public JsonObject importFromFile(File file) throws IncorrectPathException, IOErrorException, FileCanNotReadException {
        validateFileForRead(file);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            throw new IOErrorException(file.getAbsolutePath(), "reading");
        }
    }

    @Override
    public void exportToFile(JsonObject jsonObject, File file) throws IncorrectPathException, IOErrorException, FileCanNotWriteException {
        validateFileForWrite(file);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(gson.toJson(jsonObject));
        } catch (IOException e) {
            throw new IOErrorException(file.getAbsolutePath(), "writing");
        }
    }

    // Métodos privados reutilizables
    private void validateFileForRead(File file) throws IncorrectPathException, FileCanNotReadException {
        if (!file.exists()) {
            throw new IncorrectPathException(file.getAbsolutePath());
        }
        if (!file.canRead()) {
            throw new FileCanNotReadException(file.getAbsolutePath());
        }
    }

    private void validateFileForWrite(File file) throws FileCanNotWriteException, IOErrorException {
        if (file.exists() && !file.canWrite()) {
            throw new FileCanNotWriteException(file.getAbsolutePath());
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new IOErrorException(file.getAbsolutePath(), "creating");
            }
        }
    }
}
