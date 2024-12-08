package persistence.JSON.persistence;

import com.google.gson.JsonObject;
import model.exceptions.IncorrectPathException;





/**
 * @author David Calvo Espases (david.calvo.espases@estudiantat.upc.edu)
 * <p>Interficie para la gestion de ficheros</p>
 */

public interface FileManager {
    /**
     * Importa un fichero
     * @param path ruta donde se encuentra el fichero
     * @throws IncorrectPathException si la ruta no es correcta
     */
    JsonObject importFromFile(String path) throws IncorrectPathException;

    /**
     * Exporta un fichero
     * @param path ruta ddonde se exporta el fichero
     * @param jsonObject contenido a exportar
     * @throws IncorrectPathException si la ruta no es correcta
     */
    void exportToFile(String path, JsonObject jsonObject) throws IncorrectPathException;
}
