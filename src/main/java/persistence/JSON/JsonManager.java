package persistence.JSON;

import persistence.FileManager;
import model.exceptions.IncorrectPath;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author David Calvo Espases (david.calvo.espases@estudiantat.upc.edu)
 * <p>Clase para la gestion de ficheros JSON</p>
 */

public class JsonManager implements FileManager{
    public JsonManager() {
        ;
    }

    /**
     * Crea un fichero en la ruta especificada
     * @param path ruta donde se creara el fichero
     * @throws IncorrectPath si la ruta no es correcta
     */

    public void generateTestFile(String path) throws IncorrectPath{
        try (FileWriter file = new FileWriter(path)) {
            file.write("Fichero creado correctamente.\n");
            file.write("Este es un archivo de prueba para verificar el funcionamiento del sistema.");
        } catch (IOException e) {
            throw new IncorrectPath(path);
        }
    }
}


