package persistence.JSON;

import persistence.FileManager;
import model.exceptions.IncorrectPath;

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
     * @throws IncorrectPath si la ruta no es correcta
     */
    @Override
    public JsonObject importFromFile(String path) throws IncorrectPath {
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Gson gson = new Gson();
            return gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            throw new IncorrectPath(path);
        }
    }

    /**
     * Exporta un JsonObject a un fichero JSON
     * @param path ruta donde se creara el fichero
     * @param jsonObject contenido a exportar
     * @throws IncorrectPath si la ruta no es correcta
     */
    @Override
    public void exportToFile(String path, JsonObject jsonObject) throws IncorrectPath {
        File file = new File(path);

        // Si el path es directorio se crea un fichero output.json
        if(file.isDirectory()){
            file = new File(file, "output.json");
        }

        // Si el path es un directorio que no existe se crea
        if(!file.exists()){
            file.getParentFile().mkdirs();
        }


        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            Gson gson = new Gson();
            writer.write(gson.toJson(jsonObject));
        } catch (IOException e) {
            throw new IncorrectPath(path);
        }
    }
}


