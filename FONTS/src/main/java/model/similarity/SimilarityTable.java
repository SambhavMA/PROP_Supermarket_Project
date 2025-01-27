package model.similarity;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 * <p>Clase que representa una tabla de similitud</p>
 */
public class SimilarityTable {
    private int id;
    private HashMap<String, Integer> fastIndexes;
    private double[][] relationMatrix;

    /**
     * Constructor de la clase SimilarityTable
     * @param id Identificador de la tabla de similitud
     * @param fastIndexes Indice rapido de los elementos de la tabla de similitud
     * @param relationMatrix Matriz de similitud
     */
    public SimilarityTable(int id, HashMap<String, Integer> fastIndexes, double[][] relationMatrix) {
        this.id = id;
        this.fastIndexes = fastIndexes;
        this.relationMatrix = relationMatrix;
    }

    /**
     * Constructor de la clase SimilarityTable sin fastIndexes
     * @param id Identificador de la tabla de similitud
     * @param relationMatrix Matriz de similitud
     */
    public SimilarityTable(int id, double[][] relationMatrix) {
        this.id = id;
        this.fastIndexes = null;
        this.relationMatrix = relationMatrix;
    }

    /**
     * Constructor de la clase SimilarityTable sin relationMatrix ni fastIndexes
     * @param id Identificador de la tabla de similitud
     */
    public SimilarityTable(int id) {
        this.id = id;
        this.fastIndexes = null;
        this.relationMatrix = null;
    }

    public int getId() {
        return id;
    }

    public HashMap<String, Integer> getFastIndexes() {
        return fastIndexes;
    }

    public double[][] getRelationMatrix() {
        return relationMatrix;
    }

    public void setRelationMatrix(double[][] relationMatrix) {
        this.relationMatrix = relationMatrix;
    }

    /**
     * Metodo que modifica la matriz de similitud
     * @param a producto 1
     * @param b producto 2
     * @param value valor de similitud entre a y b
     */
    public void modifyRelationMatrix(String a, String b, Double value) {
        int indexA = fastIndexes.get(a);
        int indexB = fastIndexes.get(b);
        relationMatrix[indexA][indexB] = value;
        relationMatrix[indexB][indexA] = value;
    }
}
