package persistence.JSON;

import persistence.FileManager;
import model.exceptions.IncorrectPathException;
import model.exceptions.IOErrorException;
import model.exceptions.FileCanNotReadException;
import model.exceptions.FileCanNotWriteException;

import java.io.*;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

/**
 * @author David Calvo Espases (david.calvo.espases@estudiantat.upc.edu)
 * <p>Clase para la gestion de ficheros JSON</p>
 */

public class JsonManager implements FileManager{
    public JsonManager() {
        ;
    }

    /**
     * Importa el contenido de un fichero JSON y retorna un JsonObject
     * @param path ruta donde se encuentra el fichero JSON
     * @throws IncorrectPathException si la ruta no es correcta
     */
    @Override
    public JsonObject importFromFile(String path) throws IncorrectPathException, IOErrorException, FileCanNotReadException {
        if (path.contains("..") || path.contains("%")) {
            throw new IncorrectPathException("Invalid path: " + path);
        }

        File file = new File(path);

        if(!file.exists()){
            throw new IncorrectPathException(path);
        }

        if(file.exists() && !file.canRead()){
            throw new FileCanNotReadException(path);
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Gson gson = new Gson();
            return gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            throw new IOErrorException(path, "reading");
        }
    }

    /**
     * Exporta un JsonObject a un fichero JSON
     * @param path ruta donde se creara el fichero
     * @param jsonObject contenido a exportar
     * @throws IncorrectPathException si la ruta no es correcta
     */
    @Override
    public void exportToFile(String path, JsonObject jsonObject) throws IncorrectPathException, IOErrorException, FileCanNotWriteException {
        if (path.contains("..") || path.contains("%")) {
            throw new IncorrectPathException("Invalid path: " + path);
        }

        File file = new File(path);

        // Si el path es directorio se crea un fichero output.json
        if(file.isDirectory()){
            file = new File(file, "output.json");
        }

        if(file.exists() && !file.canWrite()){
            throw new FileCanNotWriteException(path);
        }

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new IOErrorException(path, "creating");
            }
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            Gson gson = new Gson();
            writer.write(gson.toJson(jsonObject));
        } catch (IOException e) {
            throw new IOErrorException(path, "writing");
        }
    }
}


