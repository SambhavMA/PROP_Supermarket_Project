package model.similarity;

import model.exceptions.SimilarityTableNotFoundException;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 * <p>Clase que representa un contenedor de tablas de similitud</p>
 */
public class SimilarityTableContainer {

    private static SimilarityTableContainer instance;

    public static SimilarityTableContainer getInstance() {
        if (instance == null) {
            instance = new SimilarityTableContainer();
        }
        return instance;
    }

    private HashMap<Integer, SimilarityTable> similarityTables = new HashMap<>();
    private int idCounter;

    /**
     * Constructor de la clase SimilarityTableContainer
     */
    private SimilarityTableContainer() {
        this.similarityTables = new HashMap<>();
        this.idCounter = 0;
    }

    public HashMap<Integer, SimilarityTable> getSimilarityTables() {
        return similarityTables;
    }

    public SimilarityTable getSimilarityTableById(int id) throws SimilarityTableNotFoundException {
        if (!similarityTables.containsKey(id)) {
            throw new SimilarityTableNotFoundException(id);
        }
        return similarityTables.get(id);
    }

    /**
     * Añade una tabla de similitud al contenedor
     * @param id Identificador de la tabla de similitud
     * @param similarityTable Tabla de similitud
     */
    public void addSimilarityTable(int id, SimilarityTable similarityTable) {
        similarityTables.put(id, similarityTable);
    }

    /**
     * Modifica una tabla de similitud del contenedor
     * @param id Identificador de la tabla de similitud
     * @param similarityTable Tabla de similitud
     * @throws SimilarityTableNotFoundException Excepcion lanzada si no se encuentra la tabla de similitud
     */
    public void modifySimilarityTable(int id, SimilarityTable similarityTable) throws SimilarityTableNotFoundException {
        if (!similarityTables.containsKey(id)) {
            throw new SimilarityTableNotFoundException(id);
        }
        similarityTables.put(id, similarityTable);
    }

    /**
     * Elimina una tabla de similitud del contenedor
     * @param id Identificador de la tabla de similitud
     * @throws SimilarityTableNotFoundException Excepcion lanzada si no se encuentra la tabla de similitud
     */
    public void deleteSimilarityTableById(int id) throws SimilarityTableNotFoundException {
        if (!similarityTables.containsKey(id)) {
            throw new SimilarityTableNotFoundException(id);
        }
        similarityTables.remove(id);
    }

    /**
     * Devuelve el nuevo identificador de tabla de similitud
     * @return Identificador de tabla de similitud
     */
    public int newId() {
        return ++idCounter;
    }

    /**
     * Devuelve el siguiente identificador de tabla de similitud
     * @return Identificador de tabla de similitud
     */
    public int nextId() {
        return idCounter + 1;
    }
}