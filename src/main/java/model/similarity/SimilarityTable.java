package model.similarity;

import java.util.Map;
import java.util.Vector;

public class SimilarityTable {
    private int id;
    private Map<String, Integer> fastIndexes;
    private Vector<Vector<Double>> relationMatrix;

    // CONSTRUCTORS
    public SimilarityTable(int id, Map<String, Integer> fastIndexes, Vector<Vector<Double>> relationMatrix) {
        this.id = id;
        this.fastIndexes = fastIndexes;
        this.relationMatrix = relationMatrix;
    }

    public SimilarityTable(int id, Map<String, Integer> fastIndexes) {
        this.id = id;
        this.fastIndexes = fastIndexes;
        this.relationMatrix = new Vector<>();
    }

    public SimilarityTable(int id) {
        this.id = id;
        this.fastIndexes = null;
        this.relationMatrix = new Vector<>();
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public Map<String, Integer> getFastIndexes() {
        return fastIndexes;
    }

    public Vector<Vector<Double>> getRelationMatrix() {
        return relationMatrix;
    }

    // SETTERS
    public void setFastIndexes(Map<String, Integer> fastIndexes) {
        this.fastIndexes = fastIndexes;
    }

    public void setRelationMatrix(Vector<Vector<Double>> relationMatrix) {
        this.relationMatrix = relationMatrix;
    }

    // METHODS
}
