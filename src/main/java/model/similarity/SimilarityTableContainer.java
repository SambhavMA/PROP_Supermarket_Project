package model.similarity;

import java.util.HashMap;

// TODO: Implement SimilarityTableContainer
public class SimilarityTableContainer {
    private HashMap<Integer, SimilarityTable> similarityTables = new HashMap<>();

    // GETTERS
    public SimilarityTable getSimilarityTableById(int id) {
        return similarityTables.get(id);
    }
}