package model.similarity;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SimilarityTableTest {

    private SimilarityTable similarityTableWithFI;
    private SimilarityTable similarityTableWithoutFI;
    private SimilarityTable emptySimilarityTable;
    private HashMap<String, Integer> fastIndexes;
    private double[][] relationMatrix;

    @Before
    public void setUp() {
        fastIndexes = new HashMap<>();
        fastIndexes.put("A", 0);
        fastIndexes.put("B", 1);

        relationMatrix = new double[2][2];
        relationMatrix[0][0] = 1.0;
        relationMatrix[0][1] = 0.5;
        relationMatrix[1][0] = 0.5;
        relationMatrix[1][1] = 1.0;

        similarityTableWithFI = new SimilarityTable(1, fastIndexes, relationMatrix);
        similarityTableWithoutFI = new SimilarityTable(2, relationMatrix);
        emptySimilarityTable = new SimilarityTable(3);
    }


    @Test
    public void testConstructorWithFastIndexes() {
        assertEquals(1, similarityTableWithFI.getId());
        assertEquals(fastIndexes, similarityTableWithFI.getFastIndexes());
        assertTrue(Arrays.deepEquals(relationMatrix, similarityTableWithFI.getRelationMatrix()));
    }


    @Test
    public void testConstructorWithoutFastIndexes() {
        assertEquals(2, similarityTableWithoutFI.getId());
        assertNull(similarityTableWithoutFI.getFastIndexes());
        assertTrue(Arrays.deepEquals(relationMatrix, similarityTableWithoutFI.getRelationMatrix()));
    }


    @Test
    public void testConstructorWithIdOnly() {
        assertEquals(3, emptySimilarityTable.getId());
        assertNull(emptySimilarityTable.getFastIndexes());
        double[][] emptyMatrix = emptySimilarityTable.getRelationMatrix();
        assertTrue(emptyMatrix == null || emptyMatrix.length == 0);
    }

    @Test
    public void testSetRelationMatrix() {
        double[][] newRelationMatrix = new double[2][2];
        newRelationMatrix[0][0] = 0.0;
        newRelationMatrix[0][1] = 0.0;
        newRelationMatrix[1][0] = 0.0;
        newRelationMatrix[1][1] = 0.0;

        similarityTableWithFI.setRelationMatrix(newRelationMatrix);
        assertTrue(Arrays.deepEquals(newRelationMatrix, similarityTableWithFI.getRelationMatrix()));
    }

    @Test
    public void testModifyRelationMatrix() {
        similarityTableWithFI.modifyRelationMatrix("A", "B", 0.5);
        assertEquals(0.5, similarityTableWithFI.getRelationMatrix()[0][1], 0.0);
    }
}
