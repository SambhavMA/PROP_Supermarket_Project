package model.similarity;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static org.junit.Assert.*;

public class SimilarityTableTest {

    private SimilarityTable similarityTableWithMatrix;
    private SimilarityTable similarityTableWithoutMatrix;
    private SimilarityTable emptySimilarityTable;
    private Map<String, Integer> fastIndexes;
    private Vector<Vector<Double>> relationMatrix;

    // @Before
    // public void setUp() {
    // fastIndexes = new HashMap<>();
    // fastIndexes.put("A", 0);
    // fastIndexes.put("B", 1);

    // relationMatrix = new Vector<>();
    // Vector<Double> row1 = new Vector<>();
    // row1.add(1.0);
    // row1.add(0.5);
    // Vector<Double> row2 = new Vector<>();
    // row2.add(0.5);
    // row2.add(1.0);
    // relationMatrix.add(row1);
    // relationMatrix.add(row2);

    // // Inicializamos las instancias de SimilarityTable con diferentes
    // constructores
    // similarityTableWithMatrix = new SimilarityTable(1, fastIndexes,
    // relationMatrix);
    // similarityTableWithoutMatrix = new SimilarityTable(2, fastIndexes);
    // emptySimilarityTable = new SimilarityTable(3);
    // }

    // Test del constructor con id, fastIndexes y relationMatrix
    // @Test
    // public void testConstructorWithMatrix() {
    // assertEquals(1, similarityTableWithMatrix.getId());
    // assertEquals(fastIndexes, similarityTableWithMatrix.getFastIndexes());
    // assertEquals(relationMatrix, similarityTableWithMatrix.getRelationMatrix());
    // }

    // Test del constructor con id y fastIndexes sin relationMatrix
    // @Test
    // public void testConstructorWithoutMatrix() {
    // assertEquals(2, similarityTableWithoutMatrix.getId());
    // assertEquals(fastIndexes, similarityTableWithoutMatrix.getFastIndexes());
    // assertTrue(similarityTableWithoutMatrix.getRelationMatrix().isEmpty());
    // }

    // // Test del constructor con solo id
    // @Test
    // public void testConstructorWithIdOnly() {
    // assertEquals(3, emptySimilarityTable.getId());
    // assertNull(emptySimilarityTable.getFastIndexes());
    // assertTrue(emptySimilarityTable.getRelationMatrix().isEmpty());
    // }

    // // Test de los getters
    // @Test
    // public void testGetId() {
    // assertEquals(1, similarityTableWithMatrix.getId());
    // }

    // @Test
    // public void testGetFastIndexes() {
    // assertEquals(fastIndexes, similarityTableWithMatrix.getFastIndexes());
    // }

    // @Test
    // public void testGetRelationMatrix() {
    // assertEquals(relationMatrix, similarityTableWithMatrix.getRelationMatrix());
    // }

    // // Test de los setters
    // // @Test
    // // public void testSetFastIndexes() {
    // // Map<String, Integer> newFastIndexes = new HashMap<>();
    // // newFastIndexes.put("C", 2);
    // // similarityTableWithMatrix.setFastIndexes(newFastIndexes);
    // // assertEquals(newFastIndexes, similarityTableWithMatrix.getFastIndexes());
    // // }

    // @Test
    // public void testSetRelationMatrix() {
    // Vector<Vector<Double>> newMatrix = new Vector<>();
    // Vector<Double> newRow = new Vector<>();
    // newRow.add(0.7);
    // newRow.add(0.3);
    // newMatrix.add(newRow);
    // similarityTableWithMatrix.setRelationMatrix(newMatrix);
    // assertEquals(newMatrix, similarityTableWithMatrix.getRelationMatrix());
    // }
}
