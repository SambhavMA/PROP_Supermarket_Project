package model.similarity;

import model.exceptions.SimilarityTableNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class SimilarityTableContainerTest {

    private SimilarityTableContainer similarityTableContainer;
    private SimilarityTable similarityTable;

    @Before
    public void setUp() {

        similarityTable = new SimilarityTable(1);
        similarityTableContainer = new SimilarityTableContainer();
        similarityTableContainer.addSimilarityTable(1, similarityTable);
    }

    @Test
    public void testGetSimilarityTables() {
        HashMap<Integer, SimilarityTable> similarityTables = similarityTableContainer.getSimilarityTables();
        assertNotNull(similarityTables);
    }

    @Test
    public void testGetSimilarityTableById() {
        try {
            SimilarityTable st = similarityTableContainer.getSimilarityTableById(1);
            assertEquals(similarityTable, st);
        } catch (SimilarityTableNotFoundException e) {
            fail("Unexpected SimilarityTableNotFoundException");
        }
    }

    @Test
    public void testGetSimilarityTableByIdWithoutST() {
        try {
            similarityTableContainer.getSimilarityTableById(2);
            fail("Expected SimilarityTableNotFoundException");
        } catch (SimilarityTableNotFoundException e) {
            assertEquals("The similarity table with id 2 was not found in the system.", e.getMessage());
        }
    }

    @Test
    public void testAddSimilarityTable() {
        SimilarityTable newST = new SimilarityTable(2);
        similarityTableContainer.addSimilarityTable(2, newST);
        try {
            SimilarityTable st = similarityTableContainer.getSimilarityTableById(2);
            assertEquals(newST, st);
        } catch (SimilarityTableNotFoundException e) {
            fail("Unexpected SimilarityTableNotFoundException");
        }
    }

    @Test
    public void testModifySimilarityTable() {
        SimilarityTable newST = new SimilarityTable(2);
        similarityTableContainer.addSimilarityTable(2, newST);
        try {
            similarityTableContainer.modifySimilarityTable(2, similarityTable);
            SimilarityTable st = similarityTableContainer.getSimilarityTableById(2);
            assertEquals(similarityTable, st);
        } catch (SimilarityTableNotFoundException e) {
            fail("Unexpected SimilarityTableNotFoundException");
        }
    }

    @Test
    public void testModifySimilarityTableWithoutST() {
        try {
            similarityTableContainer.modifySimilarityTable(2, similarityTable);
            fail("Expected SimilarityTableNotFoundException");
        } catch (SimilarityTableNotFoundException e) {
            assertEquals("The similarity table with id 2 was not found in the system.", e.getMessage());
        }
    }

    @Test
    public void testDeleteSimilarityTableById() {
        similarityTableContainer.addSimilarityTable(2, new SimilarityTable(2));
        try {
            similarityTableContainer.deleteSimilarityTableById(2);
            similarityTableContainer.getSimilarityTableById(2);
            fail("Expected SimilarityTableNotFoundException");
        } catch (SimilarityTableNotFoundException e) {
            assertEquals("The similarity table with id 2 was not found in the system.", e.getMessage());
        }
    }

    @Test
    public void testDeleteSimilarityTableByIdWithoutST() {
        try {
            similarityTableContainer.deleteSimilarityTableById(2);
            fail("Expected SimilarityTableNotFoundException");
        } catch (SimilarityTableNotFoundException e) {
            assertEquals("The similarity table with id 2 was not found in the system.", e.getMessage());
        }
    }

}
