package persistence;

import model.exceptions.FileCanNotReadException;
import model.exceptions.FileCanNotWriteException;
import model.exceptions.IOErrorException;
import model.exceptions.IncorrectPathException;
import com.google.gson.JsonObject;





/**
 * @author David Calvo Espases (david.calvo.espases@estudiantat.upc.edu)
 * <p>Interficie para la gestion de ficheros</p>
 */

public interface FileManager {
    /**
     * Importa un fichero
     * @return contenido del fichero
     * @throws IncorrectPathException si la ruta no es correcta
     * @throws IOErrorException si hay un error de lectura
     * @throws FileCanNotReadException si no se puede leer el fichero
     */
    JsonObject importFromFile() throws IncorrectPathException, IOErrorException, FileCanNotReadException;

    /**
     * Exporta un fichero
     * @param jsonObject contenido a exportar
     *
     * @throws IncorrectPathException si la ruta no es correcta
     * @throws IOErrorException si hay un error de escritura
     * @throws FileCanNotWriteException si no se puede escribir en el fichero
     */
    void exportToFile(JsonObject jsonObject) throws IncorrectPathException, IOErrorException, FileCanNotWriteException;
}
