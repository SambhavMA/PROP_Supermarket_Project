package controller;

import persistence.FileManager;
import persistence.JSON.JsonManager;

import model.exceptions.IncorrectPath;

/**
 * @author David Calvo Espases (david.calvo.espases@estudiantat.upc.edu)
 * <p>Se encarga de gestionar las operaciones de persistencia.
 * Estas operaciones son importar o exportar productos y tablas de similitud.
 * Tiene la interficie de FileManager como atributo.</p>
 */

public class ControllerPersistencia {

    private FileManager fileManager;
    public ControllerPersistencia() {
        fileManager = new JsonManager();
    }

    /**
     * Crea un fichero en la ruta especificada
     * @param path ruta donde se creara el fichero
     * @throws IncorrectPath si la ruta no es correcta
     */
    public void generateTestFile(String path) throws IncorrectPath{
        fileManager.generateTestFile(path);
    }
}
