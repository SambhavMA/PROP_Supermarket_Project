package model.similarity;

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

    public SimilarityTable getSimilarityTableById(int id) {
        return similarityTables.get(id);
    }

    // SETTERS
    public void addSimilarityTable(int id, SimilarityTable similarityTable) {
        similarityTables.put(id, similarityTable);
    }

    public void modifySimilarityTable(int id, SimilarityTable similarityTable) {
        similarityTables.put(id, similarityTable);
    }

    public void deleteSimilarityTableById(int id) {
        similarityTables.remove(id);
    }

    // METHODS
    public int newId() {
        return ++idCounter;
    }
}