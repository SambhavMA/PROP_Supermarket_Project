package persistence.JSON;

import persistence.FileManager;
import model.exceptions.IncorrectPathException;
import model.exceptions.IOErrorException;
import model.exceptions.FileCanNotReadException;
import model.exceptions.FileCanNotWriteException;

import java.io.*;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

import javax.swing.*;

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
     * @return contenido del fichero
     * @throws IncorrectPathException si la ruta no es correcta
     * @throws IOErrorException si hay un error de lectura
     * @throws FileCanNotReadException si no se puede leer el fichero
     */
    @Override
    public JsonObject importFromFile() throws IncorrectPathException, IOErrorException, FileCanNotReadException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo JSON");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos JSON", "json"));
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            if (!file.exists()) {
                throw new IncorrectPathException(file.getAbsolutePath());
            }

            if (!file.canRead()) {
                throw new FileCanNotReadException(file.getAbsolutePath());
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                Gson gson = new Gson();
                return gson.fromJson(reader, JsonObject.class);
            } catch (IOException e) {
                throw new IOErrorException(file.getAbsolutePath(), "reading");
            }
        } else {
            throw new IncorrectPathException("El usuario canceló la selección del archivo.");
        }
    }


    /**
     * Exporta un JsonObject a un fichero JSON
     * @param jsonObject contenido a exportar
     *
     * @throws IncorrectPathException si la ruta no es correcta
     * @throws IOErrorException si hay un error de escritura
     * @throws FileCanNotWriteException si no se puede escribir en el fichero
     */
    @Override
    public void exportToFile(JsonObject jsonObject) throws IncorrectPathException, IOErrorException, FileCanNotWriteException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo JSON");
        fileChooser.setSelectedFile(new File("output.json")); // Nombre de archivo sugerido
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

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

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                Gson gson = new Gson();
                writer.write(gson.toJson(jsonObject));
            } catch (IOException e) {
                throw new IOErrorException(file.getAbsolutePath(), "writing");
            }
        } else {
            throw new IncorrectPathException("El usuario canceló la selección del archivo.");
        }
    }

}


