package model.similarity;

import model.exceptions.SimilarityTableNotFoundException;

import java.util.Collection;
import java.util.HashMap;

public class SimilarityTableContainer {
    private HashMap<Integer, SimilarityTable> similarityTables = new HashMap<>();
    private int idCounter;

    // CONSTRUCTORS
    public SimilarityTableContainer() {
        this.similarityTables = new HashMap<>();
        this.idCounter = 0;
    }

    // GETTERS
    public HashMap<Integer, SimilarityTable> getSimilarityTables() {
        return similarityTables;
    }

    public SimilarityTable getSimilarityTableById(int id) throws SimilarityTableNotFoundException {
        if (!similarityTables.containsKey(id)) {
            throw new SimilarityTableNotFoundException(id);
        }
        return similarityTables.get(id);
    }

    // SETTERS
    public void addSimilarityTable(int id, SimilarityTable similarityTable) {
        similarityTables.put(id, similarityTable);
    }

    public void modifySimilarityTable(int id, SimilarityTable similarityTable) throws SimilarityTableNotFoundException {
        if (!similarityTables.containsKey(id)) {
            throw new SimilarityTableNotFoundException(id);
        }
        similarityTables.put(id, similarityTable);
    }

    public void deleteSimilarityTableById(int id) throws SimilarityTableNotFoundException {
        if (!similarityTables.containsKey(id)) {
            throw new SimilarityTableNotFoundException(id);
        }
        similarityTables.remove(id);
    }

    // METHODS
    public int newId() {
        return ++idCounter;
    }
}