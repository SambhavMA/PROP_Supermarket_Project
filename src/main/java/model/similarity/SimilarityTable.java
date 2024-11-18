package model.similarity;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 */
public class SimilarityTable {
    private int id;
    private HashMap<String, Integer> fastIndexes;
    private double[][] relationMatrix;

    // CONSTRUCTORS
    public SimilarityTable(int id, HashMap<String, Integer> fastIndexes, double[][] relationMatrix) {
        this.id = id;
        this.fastIndexes = fastIndexes;
        this.relationMatrix = relationMatrix;
    }

    public SimilarityTable(int id, double[][] relationMatrix) {
        this.id = id;
        this.fastIndexes = null;
        this.relationMatrix = relationMatrix;
    }

    public SimilarityTable(int id) {
        this.id = id;
        this.fastIndexes = null;
        this.relationMatrix = null;
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public HashMap<String, Integer> getFastIndexes() {
        return fastIndexes;
    }

    public double[][] getRelationMatrix() {
        return relationMatrix;
    }

    // SETTERS
    // public void setFastIndexes(Map<String, Integer> fastIndexes) {
    // this.fastIndexes = fastIndexes;
    // }

    public void setRelationMatrix(double[][] relationMatrix) {
        this.relationMatrix = relationMatrix;
    }

    public void modifyRelationMatrix(String a, String b, Double value) {
        int indexA = fastIndexes.get(a);
        int indexB = fastIndexes.get(b);
        relationMatrix[indexA][indexB] = value;
        relationMatrix[indexB][indexA] = value;
    }
    // METHODS
}
