package persistence;

import model.exceptions.FileCanNotReadException;
import model.exceptions.FileCanNotWriteException;
import model.exceptions.IOErrorException;
import model.exceptions.IncorrectPathException;
import com.google.gson.JsonObject;

import java.io.File;

/**
 * Interfaz para la gestión de ficheros.
 */
public interface FileManager {

    /**
     * Importa el contenido de un fichero.
     * @param file Archivo a importar.
     * @return Contenido del fichero en formato JsonObject.
     * @throws IncorrectPathException Si la ruta no es correcta.
     * @throws IOErrorException Si hay un error de lectura.
     * @throws FileCanNotReadException Si no se puede leer el fichero.
     */
    JsonObject importFromFile(File file) throws IncorrectPathException, IOErrorException, FileCanNotReadException;

    /**
     * Exporta contenido a un fichero.
     * @param jsonObject Contenido a exportar.
     * @param file Archivo de destino.
     * @throws IncorrectPathException Si la ruta no es correcta.
     * @throws IOErrorException Si hay un error de escritura.
     * @throws FileCanNotWriteException Si no se puede escribir en el fichero.
     */
    void exportToFile(JsonObject jsonObject, File file) throws IncorrectPathException, IOErrorException, FileCanNotWriteException;
}
