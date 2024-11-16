package model.similarity;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static org.junit.Assert.*;

public class SimilarityTableTest {

    private SimilarityTable similarityTableWithFI;
    private SimilarityTable similarityTableWithoutFI;
    private SimilarityTable emptySimilarityTable;
    private HashMap<String, Integer> fastIndexes;
    private Vector<Vector<Double>> relationMatrix;

    @Before
    public void setUp() {
        fastIndexes = new HashMap<>();
        fastIndexes.put("A", 0);
        fastIndexes.put("B", 1);

        relationMatrix = new Vector<>();
        Vector<Double> row1 = new Vector<>();
        row1.add(1.0);
        row1.add(0.5);
        Vector<Double> row2 = new Vector<>();
        row2.add(0.5);
        row2.add(1.0);
        relationMatrix.add(row1);
        relationMatrix.add(row2);

        similarityTableWithFI = new SimilarityTable(1, fastIndexes, relationMatrix);
        similarityTableWithoutFI = new SimilarityTable(2, relationMatrix);
        emptySimilarityTable = new SimilarityTable(3);
    }

    // Test del constructor con id, fastIndexes y relationMatrix
    @Test
    public void testConstructorWithFastIndexes() {
        assertEquals(1, similarityTableWithFI.getId());
        assertEquals(fastIndexes, similarityTableWithFI.getFastIndexes());
        assertEquals(relationMatrix, similarityTableWithFI.getRelationMatrix());
    }

    // Test del constructor con id y relationMatrix sin fastIndexes
    @Test
    public void testConstructorWithoutFastIndexes() {
        assertEquals(2, similarityTableWithoutFI.getId());
        assertNull(similarityTableWithoutFI.getFastIndexes());
        assertEquals(relationMatrix ,similarityTableWithoutFI.getRelationMatrix());
    }

    // Test del constructor con solo id
    @Test
    public void testConstructorWithIdOnly() {
        assertEquals(3, emptySimilarityTable.getId());
        assertNull(emptySimilarityTable.getFastIndexes());
        assertTrue(emptySimilarityTable.getRelationMatrix().isEmpty());
    }

    // Test de los getters
    @Test
    public void testGetId() {
        assertEquals(1, similarityTableWithFI.getId());
    }

    @Test
    public void testGetFastIndexes() {
        assertEquals(fastIndexes, similarityTableWithFI.getFastIndexes());
    }

    @Test
    public void testGetRelationMatrix() {
        assertEquals(relationMatrix, similarityTableWithFI.getRelationMatrix());
    }

    // Test de los setters
    @Test
    public void testSetRelationMatrix() {
        Vector<Vector<Double>> newMatrix = new Vector<>();
        Vector<Double> newRow = new Vector<>();
        newRow.add(0.7);
        newRow.add(0.3);
        newMatrix.add(newRow);
        similarityTableWithFI.setRelationMatrix(newMatrix);
        assertEquals(newMatrix, similarityTableWithFI.getRelationMatrix());
    }
}
