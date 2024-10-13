package model.similarity;

import java.util.Map;
import java.util.Vector;

public class SimilarityTable {
    private int id;
    private Map<String, Integer> fastIndexes;
    private Vector<Vector<Double>> relationMatrix;
}
