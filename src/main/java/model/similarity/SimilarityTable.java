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

    public SimilarityTable(int id, Vector<Vector<Double>> relationMatrix) {
        this.id = id;
        this.fastIndexes = null;
        this.relationMatrix = relationMatrix;
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
//    public void setFastIndexes(Map<String, Integer> fastIndexes) {
//        this.fastIndexes = fastIndexes;
//    }

    public void setRelationMatrix(Vector<Vector<Double>> relationMatrix) {
        this.relationMatrix = relationMatrix;
    }

    public void modifyRelationMatrix(String a, String b, Double value) {
        int indexA = fastIndexes.get(a);
        int indexB = fastIndexes.get(b);
        relationMatrix.get(indexA).set(indexB, value);
        relationMatrix.get(indexB).set(indexA, value);
    }
    // METHODS
}
