package model.similarity;

import java.util.Map;
import java.util.Vector;

public class SimilarityTable {
    private int id;
    private Map<String, Integer> fastIndexes;
    private double[][] relationMatrix;

    public int getId() {
        return id;
    }

    public Map<String, Integer> getFastIndexes() {
        return fastIndexes;
    }

    public double[][] getRelationMatrix() {
        return relationMatrix;
    }
}
