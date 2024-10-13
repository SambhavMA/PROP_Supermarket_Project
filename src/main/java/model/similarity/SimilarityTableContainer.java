package model.similarity;

import java.util.HashMap;

public class SimilarityTableContainer {
    private HashMap<Integer, SimilarityTable> similarityTables = new HashMap<>();

    public SimilarityTable getSimilarityTableById(int id) {
        return similarityTables.get(id);
    }
}